Feature: Test task for football matches table

  Scenario: Football matches sorting
    Given Launch browser and go to url: https://1xbet.com/line/Football/
    Then Collect data about matches
    Then Get count of matches for each championship
    And Sort matches by date