import org.junit.jupiter.api.BeforeEach;
import pages.MainPage;
import pages.OrderPage;
import pages.ScooterPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

public class OrderScooterTest {

    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER = System.getProperty("browser","chrome");
    private WebDriver driver;


    //Поставляем пары: браузер + локатор кнопки "Заказать"
    static Stream<Arguments> provideBrowserAndButton() {
        return Stream.of(
                Arguments.of("Иван", "Петров", "ул. Ленина", "Сокол", "+79990001111"),
                Arguments.of("Анна", "Смирнова", "пр. Мира",  "Арбат", "+79990002222")
                );
    }

    @BeforeEach
    void setUp() {
        // инициализация драйвера из системного свойства -Dbrowser=chrome|firefox
        if (BROWSER.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @ParameterizedTest(name = "{0} {1}: заказ через верхнюю кнопку")
    @MethodSource("provideBrowserAndButton")
    @DisplayName("Позитивный флоу заказа через верхнюю кнопку")
    void orderViaTopButton(String firstName,
                           String lastName,
                           String address,
                           String metro,
                           String phone) {
        // 1) Главная страница
        MainPage main = new MainPage(driver);
        main.clickToButtonCookies();
        main.clickToButtonOrderUp();

        // 2) Первый шаг: личные данные + Далее
        OrderPage step1 = new OrderPage(driver);
        step1.fieldName(firstName);
        step1.fieldSurname(lastName);
        step1.fieldAdress(address);
        step1.fieldPhoneNumber(phone);
        step1.fieldMetroStation(metro);
        step1.clickButton();

        // 3) Второй шаг: детали самоката + Заказать
        ScooterPage step2 = new ScooterPage(driver);
        step2.fieldDate();
        step2.fieldRent();
        step2.fieldСolor();
        step2.fieldComment();
        step2.clickButtonOrder();
        step2.clickButtonYes();
        step2.clickButtonSeeStatus();
    }

    @ParameterizedTest(name = "{0} {1}: заказ через нижнюю кнопку")
    @MethodSource("provideBrowserAndButton")
    @DisplayName("Позитивный флоу заказа через нижнюю кнопку")
    void orderViaBottomButton(String firstName,
                              String lastName,
                              String address,
                              String metro,
                              String phone) {
        // 1) Главная страница
        MainPage main = new MainPage(driver);
        main.clickToButtonCookies();
        main.clickToButtonOrderDown();

        // 2) Первый шаг: личные данные + Далее
        OrderPage step1 = new OrderPage(driver);
        step1.fieldName(firstName);
        step1.fieldSurname(lastName);
        step1.fieldAdress(address);
        step1.fieldPhoneNumber(phone);
        step1.fieldMetroStation(metro);
        step1.clickButton();

        // 3) Второй шаг: детали самоката + Заказать
        ScooterPage step2 = new ScooterPage(driver);
        step2.fieldDate();
        step2.fieldRent();
        step2.fieldСolor();
        step2.fieldComment();
        step2.clickButtonOrder();
        step2.clickButtonYes();
        step2.clickButtonSeeStatus();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}