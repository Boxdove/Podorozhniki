package com.epam.podorozhniki.us.US_04_2_1;

import com.epam.podorozhniki.us.US_04_1_1.usRunner0411;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.reporters.Format.*;

/**
 * Created by Viktoriia_Ishchuk on 8/1/2014.
 */
public class StartTestSuite0421 extends JUnitStories{

    @Override
    public Configuration configuration() {
        Class<? extends StartTestSuite0421> embeddableClass = this.getClass();
        ParameterConverters parameterConverters = new ParameterConverters();
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
                new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters);
        parameterConverters.addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ParameterConverters.ExamplesTableConverter(examplesTableFactory));
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                        .withDefaultFormats().withFormats(CONSOLE, TXT, HTML, XML))
                .useParameterConverters(parameterConverters);
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        try {
            return new InstanceStepsFactory(configuration(), new usRunner0421()).createCandidateSteps();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("US_04_2_1.story");
    }

    @Test
    public void run() throws Throwable {
        super.run();
    }
}
