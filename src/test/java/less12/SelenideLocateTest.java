package less12;

import less12.Data.Locale;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLocateTest {
    static Stream<Arguments> selenideSiteShouldContainAllOfButtonsForGivenLocale(){
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы","Мы говорим спасибо"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для языка {0} отображаються кнопки")
    @Tag("BLOCKER")
    void selenideSiteShouldContainAllOfButtonsForGivenLocale(
            Locale locale,
            List<String> buttons
    ){
        open("https://ru.selenide.org/");
        Configuration.timeout = 10000;
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a").shouldHave(CollectionCondition.texts(buttons));
    }
}
