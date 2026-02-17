package less12;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFileTests {

//    static {
//        Configuration.fileDownload = FileDownloadMode.CDP;
//        Configuration.downloadsFolder = "";
//    }

    @Test
    void selenideDownloadTest() throws IOException {
        open("https://github.com/junit-team/junit-examples/blob/main/README.md");
        File downloadedFile = $("[data-testid = raw-button]").download();

        // InputStream is = new FileInputStream((downloadedFile));
        try (InputStream is = new FileInputStream((downloadedFile))) {


            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("Welcome to _JUnit Examples_, a collection of example applications and extensions");
        }
//        finally {
//            is.close();
//        }
    }

    @Test
    void selenideUploadTest() {
        open("https://formstone.dev/components/upload/demo/");
//        $("input[type = 'file']").uploadFile(new File("D:/Third Crisis/EULA.txt"));
        $("input[type = 'file']").uploadFromClasspath("pics.jpg");
        $(".content").shouldHave(text("pics.jpg"));
//        $(".content").shouldHave(Condition.attribute("type","pics.jpg"));

    }
}
