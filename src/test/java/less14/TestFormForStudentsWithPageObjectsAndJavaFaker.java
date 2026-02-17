package less14;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import less14.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static less14.utils.RandomUtils.*;

public class TestFormForStudentsWithPageObjectsAndJavaFaker {
    String name,
            lastname,
            email,
            phnumber,
            address;
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void config(){
        Configuration.browserSize ="1920x1080";

    }

    @BeforeEach
    void setUP(){
        Faker fake = new Faker(Locale.ENGLISH);
        name = fake.name().firstName();
        lastname =  fake.name().lastName();
        email = fake.internet().emailAddress();
        phnumber = fake.phoneNumber().phoneNumber();
        address = fake.address().fullAddress();
    }


    @Test
    void setForm() {

        new RegistrationPage().openPage();

        registrationPage.setFirst(name);
        registrationPage
                .setLast(lastname)
                .setEmail(email)
                .selectGender("Male")
                .setPhnumber(phnumber)
                .setBirth("2002", "April", "14")
                .selectSubjects("Ch")
                .selectSubjects("Ph")
                .selectHobbies("Sports")
                .selectHobbies("Reading")
                .uploadPick()
                .setAddress(address)
                .setState("NCR")
                .setCity("gurgaon")
                .endRegistration();

        registrationPage.verifyTableOpen()
                .verifyData("Student Name",name+" "+lastname)
                .verifyData("Student Email",email)
                .verifyData("Gender","Male")
                .verifyData("Mobile",phnumber)
                .verifyData("Date of Birth","14 April,2002")
                .verifyData("Subjects","Chemistry, Physics")
                .verifyData("Picture","pics.jpg")
                .verifyData("Address",address)
                .verifyData("State and City","NCR Gurgaon");

        //Configuration.holdBrowserOpen = true;
    }

}
