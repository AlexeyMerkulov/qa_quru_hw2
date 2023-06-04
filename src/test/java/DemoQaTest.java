import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.text;


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
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9999999999");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select [value='2']").click();
        $(".react-datepicker__year-select [value='1993']").click();
        $(".react-datepicker__day--030").click();
        $("#subjectsInput").setValue("English").sendKeys(Keys.ENTER);
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/selenide.jpg"));
        $("#currentAddress").setValue("Bay Area,100");
        $("#state").click();
        $(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(byText("Agra")).click();
        $("#submit").click();

        $x("//div[@class='table-responsive']//td[text()='Student Name']/following-sibling::td").shouldHave(text("Alexey Merkulov"));
        $x("//div[@class='table-responsive']//td[text()='Student Email']/following-sibling::td").shouldHave(text("mail@mail.ru"));
        $x("//div[@class='table-responsive']//td[text()='Gender']/following-sibling::td").shouldHave(text("Male"));
        $x("//div[@class='table-responsive']//td[text()='Mobile']/following-sibling::td").shouldHave(text("9999999999"));
        $x("//div[@class='table-responsive']//td[text()='Date of Birth']/following-sibling::td").shouldHave(text("30 March,1993"));
        $x("//div[@class='table-responsive']//td[text()='Subjects']/following-sibling::td").shouldHave(text("English"));
        $x("//div[@class='table-responsive']//td[text()='Hobbies']/following-sibling::td").shouldHave(text("Sports"));
        $x("//div[@class='table-responsive']//td[text()='Picture']/following-sibling::td").shouldHave(text("selenide.jpg"));
        $x("//div[@class='table-responsive']//td[text()='Address']/following-sibling::td").shouldHave(text("Bay Area,100"));
        $x("//div[@class='table-responsive']//td[text()='State and City']/following-sibling::td").shouldHave(text("Uttar Pradesh Agra"));
    }
}
