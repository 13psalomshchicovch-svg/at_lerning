package less14.pages.component;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DatePicker {
    public void setDate(String year, String month, String day){

            $(".react-datepicker__year-select").selectOption( year);
            $(".react-datepicker__month-select").selectOption(month);
            $(".react-datepicker__month").$(byText(day)).click();

        }
}
