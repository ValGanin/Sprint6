import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {

    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String BROWSER = System.getProperty("browser","chrome");
    private WebDriver driver;
    private MainPage main;

    static Stream<Arguments> faqData() {
        return Stream.of(
                Arguments.of(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
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
        // 3) Создаём POM
        main = new MainPage(driver);
        // 4) Закрываем баннер cookies
        main.clickToButtonCookies();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "FAQ #{0} → должен показывать текст ответа")
    @MethodSource("faqData")
    @DisplayName("Проверка FAQ-пунктов")
    void testFaq(int index, String expectedText) {
        // 1) Проверяем, что ответ скрыт
        assertTrue(main.isAnswerHidden(index),
                "Ответ FAQ#" + index + " должен быть скрыт до клика");
        // 2) Раскрываем нужный пункт
        main.clickDropdown(index);
        // 3) Проверяем, что ответ больше не скрыт
        assertFalse(main.isAnswerHidden(index),
                "Ответ FAQ#" + index + " не появился после клика");
        // 4) Сравниваем текст ответа
        assertEquals(expectedText,
                main.getAnswerText(index),
                "Неверный текст для FAQ#" + index);
    }
}