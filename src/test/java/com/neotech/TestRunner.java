package com.neotech;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

// Параметры запуска тестов
@CucumberOptions(
        // путь к тестовым сценариям
        features = "src/test/java/features",

        // тест степы
        glue = {"com.neotech.testfunctional"},

        // формат результатов
        plugin = {"pretty", "html:target/cucumber"}
)

@RunWith(Cucumber.class)

public class TestRunner {}
