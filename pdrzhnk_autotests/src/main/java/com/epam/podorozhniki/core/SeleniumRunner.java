package com.epam.podorozhniki.core;

import java.util.HashMap;

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

	public void run(final RunNotifier notifier) {
		notifier.addListener(listener);
		super.run(notifier);
	}

}