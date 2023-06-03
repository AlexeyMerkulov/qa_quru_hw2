import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class DemoQaTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void testSuccessFillPracticeForm() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#close-fixedban').remove()");
        $("#firstName").setValue("Alexey");
        $("#lastName").setValue("Merkulov");
        $("#userEmail").setValue("mail@mail.ru");
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue("9999999999");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select [value='2']").click();
        $(".react-datepicker__year-select [value='1993']").click();
        $(".react-datepicker__month [aria-label='Choose Tuesday, March 30th, 1993']").click();
        $("#subjectsInput").setValue("English").sendKeys(Keys.ENTER);
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/selenide.jpg"));
        $("#currentAddress").setValue("Bay Area,100");
        $("#state").click();
        $(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(byText("Agra")).click();
        $("#submit").click();
        List<WebElement> tableRows = new ArrayList<>($$("tbody tr"));
        Map<String, String> tableContent =  tableRows
                .stream()
                .collect(Collectors.toMap(
                        key -> key.findElements(By.cssSelector("td")).get(0).getText(),
                        value -> value.findElements(By.cssSelector("td")).get(1).getText()));

        Assertions.assertEquals("Alexey Merkulov", tableContent.get("Student Name"));
        Assertions.assertEquals("mail@mail.ru", tableContent.get("Student Email"));
        Assertions.assertEquals("Male", tableContent.get("Gender"));
        Assertions.assertEquals("9999999999", tableContent.get("Mobile"));
        Assertions.assertEquals("30 March,1993", tableContent.get("Date of Birth"));
        Assertions.assertEquals("English", tableContent.get("Subjects"));
        Assertions.assertEquals("Sports", tableContent.get("Hobbies"));
        Assertions.assertEquals("selenide.jpg", tableContent.get("Picture"));
        Assertions.assertEquals("Bay Area,100", tableContent.get("Address"));
        Assertions.assertEquals("Uttar Pradesh Agra", tableContent.get("State and City"));
    }
}
