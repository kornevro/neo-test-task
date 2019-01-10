package com.neotech.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageElements {

    public static ElementsCollection matchTableRows = $$(By.xpath("//div[@class='c-events__item c-events__item_game']"));

    public static SelenideElement matchNameByIndex(int index) {
        return $(By.xpath("(//span[@class='c-events__teams'])[" + index + "]"));
    }

    public static SelenideElement eventNameByIndex(int index) {
        return $(By.xpath("((//div[@class='c-events__item c-events__item_game'])[" + index + "])/ancestor::div[@data-name='dashboard-champ-content']//div[@class='c-events__name']"));
    }

    public static SelenideElement matchDateTimeByIndex(int index) {
        return $(By.xpath("((//div[@class='c-events__item c-events__item_game'])[" + index + "])//div//span"));
    }

}
