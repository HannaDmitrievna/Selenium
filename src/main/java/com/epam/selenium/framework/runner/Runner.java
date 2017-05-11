package com.epam.selenium.framework.runner;

import com.epam.selenium.framework.listeners.ParallelSuitesExcecutionListener;
import com.epam.selenium.framework.reporting.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.epam.selenium.framework.config.GlobalConfig.getInstance;

public class Runner {
    public Runner(String[] args) {
        parseCli(args);
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        new Runner(args).runTests();
    }

    private void parseCli(String[] args) {
        Logger.info("Parse cli params...");
        CmdLineParser parser = new CmdLineParser(getInstance());
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            Logger.error("Failed to parse cli params: " + e.getMessage(), e);
            parser.printUsage(System.out);
            System.exit(1);
        }
    }

    private void runTests() throws IOException, SAXException, ParserConfigurationException {
        TestNG testNG = new TestNG();
        addListeners(testNG);
        configureSuites(testNG);
        Logger.info("Tests will be started");
        testNG.run();
    }

    private void configureSuites(TestNG testNG) throws ParserConfigurationException, SAXException, IOException {
        List<XmlSuite> suites = new ArrayList<>();
        for (String suitePath : getInstance().getSuites()) {
            InputStream suiteInClassPath = getSuiteInputStream(suitePath);
            if (suiteInClassPath != null) {
                suites.addAll(new Parser(suiteInClassPath).parse());
            } else {
                suites.addAll(new Parser(suitePath).parse());
            }
        }
        for(XmlSuite xmlSuite : suites) {
            testNG.setCommandLineSuite(xmlSuite);
        }
    }

    private void addListeners(TestNG testNG) {
        testNG.setUseDefaultListeners(false);
        List<ITestNGListener> listeners = new ArrayList<ITestNGListener>() {{
//            add(new ExtentReportListener());
            add(new ParallelSuitesExcecutionListener());
//            add(new TestNgLoggerListener());
//            add(new ParallelSuitesListener());
//            add(new TestPreconditionListener());
        }};
        for(ITestNGListener listener : listeners) {
            testNG.addListener(listener);
        }
    }

    private InputStream getSuiteInputStream(String suite) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(suite);
        return resourceAsStream;
    }
}
