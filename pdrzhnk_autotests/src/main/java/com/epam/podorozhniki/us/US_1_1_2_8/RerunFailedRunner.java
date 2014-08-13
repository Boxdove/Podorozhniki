package com.epam.podorozhniki.us.US_1_1_2_8;

import java.util.HashMap;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * ������������� ����������� ��������������� ����������� ������, �������������
 * ��������
 */
public class RerunFailedRunner extends BlockJUnit4ClassRunner {

	/**
	 * ��� ������������ ���������� ������� ���������� ������
	 */
	private HashMap<FrameworkMethod, Integer> rerunMethods = new HashMap<FrameworkMethod, Integer>();

	public RerunFailedRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		FailerListener listener = new FailerListener();
		// ���������� listener ��� ����������� ���������� �����
		notifier.addListener(listener);
		int retryCount = 2;
		for (int i = 1; i <= retryCount; i++) {
			rerunMethods.put(method, i);
			// ����� ������������ RunListener ��� �����������
			super.runChild(method, notifier);
			// ����� �����������
			if (!listener.isTestFailed()) {
				// ���� ���� ���������� �������, ������ �� ���������
				break;
			}
		}
		notifier.removeListener(listener);
	}

	/**
	 * �������� ��� ������ ��� ����������� � ������
	 */
	@Override
	protected String testName(FrameworkMethod method) {
		Integer attempt = rerunMethods.get(method);
		if (attempt != null && attempt > 1) {
			return method.getName() + attempt;
		} else {
			return method.getName();
		}
	}

	/**
	 * ��������� ���������� �����, ����������� ��� ��������� ����������
	 */
	private class FailerListener extends RunListener {
		private boolean isFailed = false;

		@Override
		public void testFailure(Failure failure) throws Exception {
			isFailed = true;
		}

		public boolean isTestFailed() {
			return isFailed;
		}
	}
}

