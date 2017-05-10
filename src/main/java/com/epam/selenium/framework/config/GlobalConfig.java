package com.epam.selenium.framework.config;

import com.epam.selenium.framework.ui.BrowserType;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;
import org.testng.xml.XmlSuite;

import java.util.List;

public class GlobalConfig {
    private static GlobalConfig instance;

    @Option(name = "-bt", usage = "browser type: firefox or chrome")
    private BrowserType browserType = BrowserType.FIREFOX;

    @Option(name="-suites", usage = "list of pathes to suites", handler = StringArrayOptionHandler.class, required = true)
    private List<String> suites = null;

    @Option(name = "-pm", usage = "parallel mode: false or tests")
    private XmlSuite.ParallelMode parallelMode = XmlSuite.ParallelMode.FALSE;

    @Option(name="-tc", usage = "amount of threads for parallel execution, equal to 0 by default")
    private int threadCount = 0;

    @Option(name = "-hub", usage = "selenium hub")
    private String seleniumHub = "";

    @Option(name = "-result_dir", usage = "Directory to put results")
    private String resultDir = "results";

    public static GlobalConfig getInstance() {
        if(instance == null) {
            instance = new GlobalConfig();
        }
        return instance;
    }

    public List<String> getSuites() {
        return suites;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public XmlSuite.ParallelMode getParallelMode() {
        return parallelMode;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public String getSeleniumHub() {
        return seleniumHub;
    }

    public String getResultDir() {
        return resultDir;
    }

    @Override
    public String toString() {
        return "GlobalConfig{" +
                "browserType=" + browserType +
                ", suites=" + suites +
                ", parallelMode=" + parallelMode +
                ", threadCount=" + threadCount +
                ", seleniumHub='" + seleniumHub + '\'' +
                ", resultDir='" + resultDir + '\'' +
                '}';
    }
}
