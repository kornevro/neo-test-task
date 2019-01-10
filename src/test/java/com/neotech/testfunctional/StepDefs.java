package com.neotech.testfunctional;

import com.codeborne.selenide.Configuration;
import com.neotech.context.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.neotech.context.ContextKey.MATCH_COLLECTION;
import static com.neotech.testfunctional.OtherHelpers.*;

public class StepDefs {

    @Given("^Launch browser and go to url: (.*)$")
    public void launchBrowserAndGoToUrl(String url) {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        open(url);
    }

    @Then("Collect data about matches")
    public void collectDataAboutMatches() throws ParseException {
        int matchRowsCount = getMatchRowsCount();
        createMatchDataCollection(matchRowsCount);
    }

    @Then("Get count of matches for each championship")
    public void getCountMatchesForEachChampionship() {
        List<HashMap<String, Object>> matchCollection = (List<HashMap<String, Object>>) TestContext.getContextVariable(MATCH_COLLECTION);
        Map<String, Integer> matchCountByEvent = getMatchCountByEvent(matchCollection);
        for (Map.Entry<String, Integer> entry : matchCountByEvent.entrySet()) {
            System.out.println(entry.getKey() + " has " + entry.getValue() + " matches.");
        }
    }

    @And("Sort matches by date")
    public void sortMatchesByDate() {
        List<HashMap<String, Object>> matchCollection = (List<HashMap<String, Object>>) TestContext.getContextVariable(MATCH_COLLECTION);
        matchCollection.sort(new HashMapComparator());
        for (HashMap<String, Object> stringObjectHashMap : matchCollection) {
            System.out.println(stringObjectHashMap);
        }
    }
}
