package com.epam.podorozhniki.us.US_1_1_2_8;

import java.util.HashMap;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class RerunFailedRunner extends BlockJUnit4ClassRunner {

	private HashMap<FrameworkMethod, Integer> rerunMethods = new HashMap<FrameworkMethod, Integer>();

	public RerunFailedRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		FailerListener listener = new FailerListener();
		notifier.addListener(listener);
		int retryCount = 2;
		for (int i = 1; i <= retryCount; i++) {
			rerunMethods.put(method, i);
			super.runChild(method, notifier);
			if (!listener.isTestFailed()) {
				notifier.removeListener(listener);
				break;
			}
		}
		notifier.removeListener(listener);
	}

	@Override
	protected String testName(FrameworkMethod method) {
		Integer attempt = rerunMethods.get(method);
		if (attempt != null && attempt > 1) {
			return method.getName() + attempt;
		} else {
			return method.getName();
		}
	}


	private class FailerListener extends RunListener {
		private boolean isFailed = false;

		@Override
		public void testFailure(Failure failure) throws Exception {
			System.out.println("TEST FAILED WITH: " + failure.getException());
			isFailed = true;
		}

		public boolean isTestFailed() {
			return isFailed;
		}

		@Override
		public void testRunFinished(Result result) throws Exception {
			System.out
					.println("*********************************************************************************************************");
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
					.println("*********************************************************************************************************");
			System.out.println("TEST STARTS: " + description);
		}

		@Override
		public void testFinished(Description description) throws Exception {
			System.out.println("TEST FINISHED: " + description);
			System.out
					.println("*********************************************************************************************************");

		}

	}

}
