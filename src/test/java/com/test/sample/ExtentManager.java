package com.test.sample;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentManager {
    public static Properties prop = new Properties();

    public static ExtentReports extent;
    public static String filePath = System.getProperty("user.dir") + "/target/ExtentReports.html";

    public ExtentManager() throws IOException {
        prop.load(new FileInputStream("config.properties"));
    }

    public synchronized static ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();
            extent.attachReporter(getHtmlReporter(),klovReporter());
/*			if (System.getenv("ExtentX").equalsIgnoreCase("true")) {
        extent.attachReporter(getExtentXReporter());
			}*/
            extent.setSystemInfo("Selenium Java Version", "2.53.0");
            extent.setSystemInfo("Environment", "Prod");
            extent.setSystemInfo("AppiumVersion", "4.0.0");

        }

        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);

        // make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);

        // report title
        //String documentTitle = prop.getProperty("documentTitle", "aventstack - Extent");
        htmlReporter.config().setDocumentTitle("AppiumTestDistribution");
        htmlReporter.config().setReportName("ExtentReports");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        return htmlReporter;
    }

    private static ExtentXReporter getExtentXReporter() {
        //String host = prop.getProperty("MONGODB_SERVER");
        //Integer port = Integer.parseInt(prop.getProperty("MONGODB_PORT"));
        ExtentXReporter extentx = new ExtentXReporter("localhost", 27017);

        // project name
        String projectName = prop.getProperty("projectName", "ExtentReports");
        extentx.config().setProjectName(projectName);

        // report or build name
        String reportName = prop.getProperty("reportName", "ExtentReports");
        extentx.config().setReportName(reportName);

        // server URL
        // ! must provide this to be able to upload snapshots

        return extentx;
    }

    private static KlovReporter klovReporter() {
        KlovReporter klov = new KlovReporter();

// specify mongoDb connection
        klov.initMongoDbConnection("localhost", 27017);

// specify project
// ! you must specify a project, other a "Default project will be used"
        klov.setProjectName("Java");

// you must specify a reportName otherwise a default timestamp will be used
        klov.setReportName("AppBuild");

// URL of the KLOV server
// you must specify the served URL to ensure all your runtime media is uploaded
// to the server
        klov.setKlovUrl("http://localhost");
        return klov;
    }

}
