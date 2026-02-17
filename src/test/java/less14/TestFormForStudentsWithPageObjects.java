package less14;

import com.codeborne.selenide.Configuration;

import less14.pages.RegistrationPage;
import less14.utils.RandomUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static less14.utils.RandomUtils.*;

public class  TestFormForStudentsWithPageObjects {
    String name,
            lastname,
            email,
            phnumber,
            address;
    RegistrationPage registrationPage = new less14.pages.RegistrationPage();

    @BeforeAll
    static void config(){
        Configuration.browserSize ="1920x1080";

    }

    @BeforeEach
    void setUP(){
        name = randomString(6);
        lastname =  randomString(8);
        email = randomEmail();
        phnumber = randomStringNumber(10);
        address = "My home";
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
