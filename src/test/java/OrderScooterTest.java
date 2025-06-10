import pages.MainPage;
import pages.OrderPage;
import pages.ScooterPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderScooterTest {

    private WebDriver driver;

    //Поставляем пары: браузер + локатор кнопки "Заказать"
    static Stream<Arguments> provideBrowserAndButton() {
        return Stream.of(
                Arguments.of("chrome", By.xpath("//button[@class='Button_Button__ra12g']")),
                Arguments.of("chrome", By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")),
                Arguments.of("firefox", By.xpath("//button[@class='Button_Button__ra12g']")),
                Arguments.of("firefox", By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"))
        );
    }

    @ParameterizedTest(name = "{0} – проверка кнопки заказа")
    @MethodSource("provideBrowserAndButton")
    @DisplayName("Позитивный флоу заказа через кнопку \"Заказать\"")
    void getOrder(String browser, By buttonLocator) throws InterruptedException {
        // Инициализируем нужный драйвер
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();

        // Открываем главную страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // 1) Закрыть баннер cookies
        MainPage mainPage = new MainPage(driver);
        mainPage.clickToButtonCookies();

        // 2) Клик по соответствующей кнопке "Заказать"
        assertTrue(driver.findElement(buttonLocator).isDisplayed(),
                "Кнопка должна быть видимой в " + browser);
        driver.findElement(buttonLocator).click();

        // 3) Должен произойти переход на страницу оформления
        assertTrue(driver.getCurrentUrl().contains("/order"),
                "Должен быть URL с \"/order\" в " + browser);

        // 4) Первый шаг: ввод личных данных и нажатие "Далее"
        OrderPage step1 = new OrderPage(driver);
        step1.fieldName();           // ввод имени
        step1.fieldSurname();        // ввод фамилии
        step1.fieldAdress();         // ввод адреса
        step1.fieldPhoneNumber();    // ввод телефона
        step1.fieldMetroStation();   // выбор станции метро
        step1.clickButton();         // нажать "Далее"

        // 5) Второй шаг: заполнение деталей заказа
        ScooterPage step2 = new ScooterPage(driver);
        step2.fieldDate();           // выбор даты
        step2.fieldRent();           // выбор срока
        step2.fieldСolor();          // выбор цвета
        step2.fieldComment();        // ввод комментария
        step2.clickButtonOrder();    // нажать "Заказать"
        step2.clickButtonYes();      // подтвердить "Да"
        step2.clickButtonSeeStatus();// нажать "Посмотреть статус"
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
