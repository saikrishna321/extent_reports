package com.test.sample;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Properties;

public class ExtentManager {
	public static Properties prop = new Properties();

	private static ThreadLocal<ExtentReports> extent;
	private static String filePath = System.getProperty("user.dir") + "/target/ExtentReports.html";

	public synchronized static ExtentReports getExtent() {
		if (extent == null || extent.get() == null) {
			extent = new ThreadLocal<ExtentReports>();

			extent.get().attachReporter(getHtmlReporter());

			if (System.getenv("ExtentX").equalsIgnoreCase("true")) {
				extent.get().attachReporter(getExtentXReporter());
			}

			extent.get().setSystemInfo("Selenium Java Version", "2.53.0");
			extent.get().setSystemInfo("Environment", "Prod");
			extent.get().setSystemInfo("AppiumVersion", "4.0.0");
			extent.get().setSystemInfo("RunnerMode", prop.getProperty("RUNNER").toUpperCase());
		}

		return extent.get();
	}

	private static ExtentHtmlReporter getHtmlReporter() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);

		// make the charts visible on report open
		htmlReporter.config().setChartVisibilityOnOpen(true);

		// report title
		String documentTitle = prop.getProperty("documentTitle", "aventstack - Extent");
		htmlReporter.config().setDocumentTitle(documentTitle);

		// encoding, default = UTF-8
		String encoding = prop.getProperty("encoding", "UTF-8");
		htmlReporter.config().setEncoding(encoding);

		// protocol (http, https)
		String protocol = prop.getProperty("protocol", "https").toUpperCase();
		htmlReporter.config().setProtocol(Enum.valueOf(Protocol.class, protocol));

		// report or build name
		String reportName = prop.getProperty("reportName", "ExtentReports");
		htmlReporter.config().setReportName(reportName);

		// chart location - top, bottom
		String chartLocation = prop.getProperty("chartLocation", "top").toUpperCase();
		htmlReporter.config().setTestViewChartLocation(Enum.valueOf(ChartLocation.class, chartLocation));

		// theme - standard, dark
		String theme = prop.getProperty("theme", "standard").toUpperCase();
		htmlReporter.config().setTheme(Enum.valueOf(Theme.class, theme));

		return htmlReporter;
	}

	private static ExtentXReporter getExtentXReporter() {
		String host = prop.getProperty("MONGODB_SERVER");
		Integer port = Integer.parseInt(prop.getProperty("MONGODB_PORT"));
		ExtentXReporter extentx = new ExtentXReporter(host, port);

		// project name
		String projectName = prop.getProperty("projectName", "ExtentReports");
		extentx.config().setProjectName(projectName);

		// report or build name
		String reportName = prop.getProperty("reportName", "ExtentReports");
		extentx.config().setReportName(reportName);

		// server URL
		// ! must provide this to be able to upload snapshots
		String url = prop.getProperty("ExtentXUrl", "");
		if (!url.isEmpty())
			extentx.config().setServerUrl(url);

		return extentx;
	}

}
