package less14.hellper;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {

    @Attachment(value = "{attachName}", type ="image/jpg")
    public static byte[] sreenshotAs(String attachName){
        return ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type ="text/plan")
    public static byte[] pageSource(){
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type ="text/plan")
    public static String attachAsText(String attachName, String message){
        return message;
    }

    public static void browserConsoleLogs () {
        attachAsText("Browser console logs",
                String.join("\n",Selenide.getWebDriverLogs(BROWSER))
        );

    }

    @Attachment(value = "Video", type ="text/plan", fileExtension = ".html")
    public static String addVideo(){
        return "<html><body><video width='100%' heigth='100%' controls autoplay><sourse src='"
                +getVideoUrl()
                +"' type='video/mp4'></video></body></html>";
    }

    public static URL getVideoUrl(){
        String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId() + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
}
