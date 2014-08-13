package com.epam.podorozhniki.us.US_1_1_2_8;

import java.util.HashMap;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class SeleniumRunner extends BlockJUnit4ClassRunner {

	private SeleniumRunListener listener;
	private HashMap<FrameworkMethod, Integer> rerunMethods = new HashMap<FrameworkMethod, Integer>();

	public SeleniumRunner(Class<?> klass) throws InitializationError {
		super(klass);
		listener = new SeleniumRunListener();
	}

	// public void run(final RunNotifier notifier) {
	// notifier.addListener(seleniumRunListener);
	// super.run(notifier);
	// }

	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		// SeleniumRunListener listener = new SeleniumRunListener();
		// подключаем listener для определения результата теста
		notifier.addListener(listener);
		super.run(notifier);
		int retryCount = 2;
		for (int i = 1; i <= retryCount; i++) {
			rerunMethods.put(method, i);
			// здесь подключаются RunListener для логирования
			super.runChild(method, notifier);
			// здесь отключаются
			if (!listener.isTestFailed()) {
				// если тест выполнился успешно, больше не запускаем
				break;
			}
		}
		notifier.removeListener(listener);
	}

	/**
	 * Изменяем имя метода для отображения в отчете
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
	 * Слушатель выполнения теста, фиксирующий его неудачное завершение
	 */
	// private class FailerListener extends RunListener {
	// private boolean isFailed = false;
	//
	// @Override
	// public void testFailure(Failure failure) throws Exception {
	// isFailed = true;
	// }
	//
	// public boolean isTestFailed() {
	// return isFailed;
	// }
	// }

}