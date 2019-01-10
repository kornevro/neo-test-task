package com.neotech.testfunctional;

import com.neotech.context.TestContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.neotech.context.ContextKey.MATCH_COLLECTION;
import static com.neotech.pageobject.PageElements.*;

/*
*       Вспомогательные низкоуровневые методы, используются в тест-степах
* */

public class OtherHelpers {

    public static int getMatchRowsCount() {
        return matchTableRows.size();
    }

    public static void createMatchDataCollection(int count) throws ParseException {
        List<HashMap<String, Object>> matchCollection = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int index = i + 1;

            String matchName = matchNameByIndex(index).getText();
            String eventName = eventNameByIndex(index).getText();
            String dateTime = matchDateTimeByIndex(index).getText();

            Calendar now = Calendar.getInstance();
            String currentYear = String.valueOf(now.get(Calendar.YEAR));
            dateTime = currentYear + " " + dateTime;
            SimpleDateFormat df = new SimpleDateFormat("yyyy dd.mm HH:mm");
            Date parsedDate = df.parse(dateTime);

            HashMap<String, Object> matchData = new HashMap<>();
            matchData.put("eventName", eventName);
            matchData.put("matchName", matchName.replaceAll("\\r|\\n", " / "));
            matchData.put("dateTime", parsedDate);

            matchCollection.add(matchData);
            System.out.println(index + ") " + matchData);
        }
        TestContext.put(MATCH_COLLECTION, matchCollection);
    }

    public static Map<String, Integer> getMatchCountByEvent(List<HashMap<String, Object>> matchCollection) {
        Map<String, Integer> matchCounts = new HashMap<>();

        for (HashMap<String, Object> stringObjectHashMap : matchCollection) {
            String eventName = (String) stringObjectHashMap.get("eventName");

            if (!matchCounts.containsKey(eventName)) {
                matchCounts.put(eventName, 1);
            } else {
                matchCounts.put(eventName, matchCounts.get(eventName) + 1);
            }
        }

        return matchCounts;
    }

    public static class HashMapComparator implements Comparator {
        boolean flag = false;
        public int compare(Object object1, Object object2) {
            if (!flag) {
                Date obj1Value = (Date) ((HashMap) object1).get("dateTime");
                Date obj2Value = (Date) ((HashMap) object2).get("dateTime");

                return obj1Value.compareTo(obj2Value);
            } else {
                Date obj1Value = (Date) ((HashMap) object1).get("dateTime");
                Date obj2Value = (Date) ((HashMap) object2).get("dateTime");

                return obj2Value.compareTo(obj1Value);
            }
        }
    }

}
