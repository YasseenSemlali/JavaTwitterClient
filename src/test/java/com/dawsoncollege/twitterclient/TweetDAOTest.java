/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dawsoncollege.twitterclient;

import com.dawsoncollege.twitterclient.business.TweetInfo;
import com.dawsoncollege.twitterclient.business.TweetInfoGeneric;
import com.dawsoncollege.twitterclient.io.SQLPropertiesManager;
import com.dawsoncollege.twitterclient.sql.TweetDAO;
import com.dawsoncollege.twitterclient.sql.TweetDAOImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yasseen
 */
@RunWith(Enclosed.class)
public class TweetDAOTest {

    private final static Logger LOG = LoggerFactory.getLogger(TweetDAOTest.class);
    private static final String propDir = "src/test/resources";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    @RunWith(Parameterized.class)
    public static class TweetInfoTests {

        @Parameters
        public static Collection<Object[]> data() throws ParseException {
            return Arrays.asList(new Object[][]{
                {new TweetInfoGeneric(0, "Ken Fogel", "@static", "First tweet in DB", "url.com", dateFormat.parse("20191107000000"), false, false, false, false, 0, 0, 0), 4},
                {new TweetInfoGeneric(1, "Ken Fogel", "@static", "Second Tweet In DB", "url.com", dateFormat.parse("20191108100000"), true, true, true, true, 1, 2, 3), 5},
                {new TweetInfoGeneric(2, "Yasseen Semlali", "@asdf", "Python <3", "example.com", dateFormat.parse("20200101000000"), true, false, true, false, 500, 8000, 9999), 6},
                {new TweetInfoGeneric(3, "Okuyasu Nijimura", "@ZaHando", "Oi Josuke", "", dateFormat.parse("19990101000000"), false, true, false, false, 0, 3, 0), 2},
                {new TweetInfoGeneric(4, "Dio Brando", "@ZaWarudo", "WRYYYYYYYY (1/2)", "roadroller.com", dateFormat.parse("19890101000000"), false, false, false, false, 0, 0, 0), 1},
                {new TweetInfoGeneric(5, "Dio Brando", "@ZaWarudo", "YYYYYYYYYY (2/2)", "roadroller.com", dateFormat.parse("19890101000000"), false, false, false, false, 0, 0, 0), 0},
                {new TweetInfoGeneric(6, "", "", "", "", dateFormat.parse("20191107000000"), false, false, false, false, 0, 0, 0), 3}
            });
        }
        private final TweetInfo info;
        private final int index;

        public TweetInfoTests(TweetInfo info, int index) {
            this.info = info;
            this.index = index;
        }

        @BeforeClass
        public static void setUp() {
            runSqlScript("sql/createTweetsTestTable.sql");
        }

        @Test
        public void testContents() {
            TweetDAO dao = new TweetDAOImpl(propDir);
            TweetInfo retrievedInfo = dao.getTweets(1).get(index);

            assertEquals(retrievedInfo, this.info);
        }

        @Test
        public void testGetTweet() {
            TweetDAO dao = new TweetDAOImpl(propDir);
            TweetInfo retrievedInfo = dao.getTweet(info.getStatusId());

            assertEquals(retrievedInfo, this.info);
        }
    }

    public static class NonParametrizedTests {

        @Before
        public void setUp() {
            runSqlScript("sql/createTweetsTestTable.sql");
        }

        @Test
        public void testGetTweets() {
            TweetDAO dao = new TweetDAOImpl(propDir);

            List<TweetInfo> results = dao.getTweets(1);
            assertEquals(7, results.size());
        }

        public void testGetTweetsInvalidPage() {
            TweetDAO dao = new TweetDAOImpl(propDir);

            List<TweetInfo> results = dao.getTweets(0);
            assertEquals(0, results.size());
        }

        @Test
        public void testGetTweetsWithTweetsPerPage() {
            TweetDAO dao = new TweetDAOImpl(propDir);

            List<TweetInfo> results = dao.getTweets(1, 2);
            assertEquals(2, results.size());
        }

        @Test
        public void testIsSaved() throws ParseException {
            TweetDAO dao = new TweetDAOImpl(propDir);

            TweetInfo info = new TweetInfoGeneric(0, "Ken Fogel", "@static", "First tweet in DB", "url.com", dateFormat.parse("20191107000000"), false, false, false, false, 0, 0, 0);
            boolean isSaved = dao.isSaved(0);
            assertTrue(isSaved);
        }

        @Test
        public void testIsNotSaves() throws ParseException {
            TweetDAO dao = new TweetDAOImpl(propDir);

            boolean isSaved = dao.isSaved(8);
            assertFalse(isSaved);
        }

        @Test
        public void testSaveTweet() throws ParseException, SQLException {
            TweetDAO dao = new TweetDAOImpl(propDir);

            dao.saveTweet(new TweetInfoGeneric(7, "", "", "", "", dateFormat.parse("20191107000000"), false, false, false, false, 0, 0, 0));
            assertEquals(8, dao.getTweets(1).size());
        }

        @Test
        public void testSaveInvalidTweet() throws ParseException, SQLException {
            TweetDAO dao = new TweetDAOImpl(propDir);

            dao.saveTweet(new TweetInfoGeneric(0, "", "", "", "", dateFormat.parse("20191107000000"), false, false, false, false, 0, 0, 0));
            assertEquals(7, dao.getTweets(1).size());
        }

        @Test
        public void testUnsaveTweet() throws SQLException {
            TweetDAO dao = new TweetDAOImpl(propDir);

            dao.unsaveTweet(0);

            assertEquals(6, dao.getTweets(1).size());
        }

        @Test
        public void testNonexistentTweet() throws SQLException {
            TweetDAO dao = new TweetDAOImpl(propDir);

            dao.unsaveTweet(0);

            assertEquals(6, dao.getTweets(1).size());
        }
    }

    private static void runSqlScript(String script) {
        LOG.info("Running script " + script);

        SQLPropertiesManager properties = new SQLPropertiesManager(propDir);

        final String seedDataScript = loadAsString(script);
        try ( Connection connection = DriverManager.getConnection(properties.getUrl(), properties.getUsername(), properties.getPassword());) {
            for (String statement : splitStatements(new StringReader(seedDataScript), ";")) {
                connection.prepareStatement(statement).execute();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Failed seeding database", e);
        }
    }

    /**
     * The following methods support the seedDatabse method
     */
    private static String loadAsString(final String path) {
        try ( InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);  Scanner scanner = new Scanner(inputStream)) {
            return scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close input stream.", e);
        }
    }

    private static List<String> splitStatements(Reader reader, String statementDelimiter) {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final StringBuilder sqlStatement = new StringBuilder();
        final List<String> statements = new LinkedList<>();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || isComment(line)) {
                    continue;
                }
                sqlStatement.append(line);
                if (line.endsWith(statementDelimiter)) {
                    statements.add(sqlStatement.toString());
                    sqlStatement.setLength(0);
                }
            }
            return statements;
        } catch (IOException e) {
            throw new RuntimeException("Failed parsing sql", e);
        }
    }

    private static boolean isComment(final String line) {
        return line.startsWith("--") || line.startsWith("//") || line.startsWith("/*");
    }
}
