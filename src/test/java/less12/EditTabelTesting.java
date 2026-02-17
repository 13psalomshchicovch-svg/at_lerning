package less12;

import com.codeborne.selenide.Configuration;
import less12.page.ObjectPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

    public class EditTabelTesting {

        @BeforeEach
        void setUp(){
            open("https://demoqa.com/");
            $(".category-cards").$("a",0).click();
            //$(".row").shouldHave(text("Please select an item from left to start practice."));
            $(".menu-list #item-3 a").click();
            Configuration.timeout = 50000;

        }

        @CsvSource({"Ivan, Kuntashov, 13Kent@ya.ru, 25, 15000, CI",
               "Jeorg, Nastarov, JNas@gm.com, 41, 7000, IT"})
        @ParameterizedTest(name = "enter values {0} into the table")
        void registrationInTableWithCSV(
                String name,
                String lastName,
                String email,
                int age,
                int salary,
                String department){

            ObjectPage objectPage = new ObjectPage(name, lastName, email, age, salary, department);
            objectPage.setForm();
            objectPage.checkForm();
        }

        @ValueSource(strings = {"Kirill"," Sergey"})
        @ParameterizedTest
        void registrationInTableWithVS(String name){
            ObjectPage.editForm(name);
            ObjectPage.checkFormAfterEdit(name);
        }
    }
