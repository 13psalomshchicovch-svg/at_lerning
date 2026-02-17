package less12.page;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ObjectPage {

    String name;
    String lastName;
    String email;
    int age;
    int salary;
    String department;

    public ObjectPage(String name, String lastName, String email, int age, int salary, String department) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public void setForm(){

        $("#addNewRecordButton").click();
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#age").setValue(String.valueOf(age));
        $("#salary").setValue(String.valueOf(salary));
        $("#department").setValue(department);
        $("#submit").click();

    }

    public void checkForm(){

        String[] employer = new String[]{name, lastName, email, Integer.toString(age),Integer.toString(salary) , department};

        for (String empl : employer){
            $$("table tbody tr").last().shouldHave(text(empl));
        }
    }

    public static void editForm(String nameout){

        $$("table tbody tr td").last().$("span").click();
        $("#firstName").setValue(nameout);

        $("#submit").click();

    }

    public static void checkFormAfterEdit(String outname){

        $$("table tbody tr").last().shouldHave(text(outname));

    }
}
