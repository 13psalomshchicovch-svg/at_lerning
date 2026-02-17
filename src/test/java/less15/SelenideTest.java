package less15;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    void serchingAllWorkFlowsInEroshenkoamAllureExampleActions(){

        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.timeout = 10000;
        //открыть главную страницу
        open("https://github.com/");

        //ввести в поле поиска Selenide и нажать enter
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        //кликнуть на первый репозиторий из списка найденых
        $(linkText("eroshenkoam/allure-example")).click();
        $("#actions-tab").click();

        $(withText("Alll workflows")).should(appear);

    }

    @Test
    void serchingAllWorkFlowsInEroshenkoamAllureExampleActionsWithAnnotated(){

      WebSteps steps = new WebSteps();
      steps.openMainPage();
      steps.searchForRepository(REPOSITORY);
      steps.clickOnRepositoryLink(REPOSITORY);
      steps.openActions();
      steps.checkAllWorkFlows();
      steps.takeScreenShoot();
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

