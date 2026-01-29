package com.bdg.ui.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Esto busca en src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.bdg.ui.stepdefinitions")
public class SauceDemoRunner {
}