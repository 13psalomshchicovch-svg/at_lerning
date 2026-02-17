package less13;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchYandexTests {

    @BeforeEach
    void setUp(){
        open("https://www.ya.ru/");
    }

    @CsvSource({
            "Selenide, ru.selenide.org",
            "Rimworld, rimworldgame.com"
    })
//    @CsvFileSource(resources = "/filename.txt")
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче яндексапо запросу {0}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    public void productSitUrlShouldBePresentInResultsOfSearchInYandexByProductQuery(
            String productName,
            String productUrl) {


        $("[name=text]").setValue(productName).pressEnter();
        $("#search-result").shouldHave(text(productUrl));
    }


    @ValueSource( strings = {"Selenide", "Rimworld"})
    @ParameterizedTest(name = "Адресов должено быть больше чем 5 в яндекса по запросу {0}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    public void searchResultCounter(
            String productName) {

        $("[name=text]").setValue(productName).pressEnter();
        $$("#search-result li .OrganicTitle")
                .shouldHave(CollectionCondition.sizeGreaterThan(5));
    }


}
