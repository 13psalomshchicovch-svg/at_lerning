package less15;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTestWhithLabels {

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue в репозитории")
    @Owner("13ko31")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    void staticLabels(){



    }

    @Test
    void dynamicLabels(){
        Allure.getLifecycle().updateTestCase(
                t-> t.setName("Создание Issue для авторизованного пользователя"));
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue в репозитории");
        Allure.label("owner","13ko31");
        Allure.label("severity",SeverityLevel.CRITICAL.value());
        Allure.link("Testing", "https://testing.github.com");


    }

    private static final String REPOSITORY = "eroshenkoam/allure-example";

    @Test
    void serchingAllWorkFlowsInEroshenkoamAllureExampleActionsUsingLambda(){

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", ()-> {
            open("https://github.com/");
        });
        step("Ищем репозиторий "+ REPOSITORY,()->{

            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, ()->{
           $(linkText("eroshenkoam/allure-example")).click();
       });
        step("Переходим в action", ()->{
           $("#actions-tab").click();
       });
        step("Проверяем есть надпись All workflows", ()->{
           $(withText("All workflows")).should(appear);
       });
//        step("Что-то делаем", new Allure.ThrowableContextRunnableVoid<Allure.StepContext>() {
//            @Override
//            public void run(Allure.StepContext context) throws Throwable {
//
//            }
//        }); bad practics
        }

}

