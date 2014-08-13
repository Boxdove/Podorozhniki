package com.epam.podorozhniki.core;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SeleniumRunListener extends RunListener {

	private ReadingDatafile rd;
	private boolean isFailed = false;

	@Override
	public void testRunStarted(Description description) throws Exception {
		System.out.println("BEFORE TESTS RUN: " + description);
	}

	@Override
	public void testRunFinished(Result result) throws Exception {
		System.out.println("RESULT OF THE TEST RUN:");
		System.out.println("RUN TIME: " + result.getRunTime() + " ms");
		System.out.println("RUN COUNT: " + result.getRunCount());
		System.out.println("FAILURE COUNT: " + result.getFailureCount());
		System.out.println("IGNORED COUNT: " + result.getIgnoreCount());
		System.out.println(" (\\\\___//)");
		System.out.println("  (='.'=)");
		System.out.println(" ('')_('')");
	}

	@Override
	public void testStarted(Description description) throws Exception {
		System.out
				.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("TEST STARTS: " + description);
	}

	@Override
	public void testFinished(Description description) throws Exception {
		File screenshot = ((TakesScreenshot) Driver.getInstance())
				.getScreenshotAs(OutputType.FILE);
		String path = "./target/screenshots/" + screenshot.getName();
		FileUtils.copyFile(screenshot, new File(path));
		System.out.println("TEST FINISHED: " + description);
		System.out
				.println("---------------------------------------------------------------------------------------------------------");
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		rd = new ReadingDatafile();
		rd.readingDataFile();
		System.out.println("TEST FAILED WITH: " + failure.getException());
		isFailed = true;
	}

	public boolean isTestFailed() {
		return isFailed;
	}

	@Override
	public void testAssumptionFailure(Failure failure) {
		System.out.println("TEST ASSUMES: " + failure.getException());
	}

	@Override
	public void testIgnored(Description description) throws Exception {
		System.out.println("TEST IGNORED: " + description);
		System.out
				.println("---------------------------------------------------------------------------------------------------------");
	}

}