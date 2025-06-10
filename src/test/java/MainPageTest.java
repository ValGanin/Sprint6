import pages.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {

    private WebDriver driver;

    @ParameterizedTest(name = "{0} — раскрытие FAQ #0")
    @ValueSource(strings = {"chrome", "firefox"})
    @DisplayName("Раскрытие пункта FAQ №0")
    public void testClickToDropdown0(String browser) {
        // 1) Инициализируем драйвер
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // 2) Работаем с POM
        MainPage main = new MainPage(driver);
        main.clickToButtonCookies();

        By panel0 = By.xpath("//div[@class='accordion__panel' and @id='accordion__panel-0']");
        //Проверяем, что текст изначально скрыт (атрибут hidden)
        assertTrue(driver.findElement(panel0).getAttribute("hidden") != null,
                "Панель №0 должна быть скрыта при загрузке в " + browser);

        // кликаем и проверяем, что текст появился
        main.clickToDropdown0();
        assertFalse(driver.findElement(panel0).getAttribute("hidden") != null,
                "Панель №0 не появилась после клика в " + browser);

        // проверяем сам текст
        assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                driver.findElement(panel0).getText());
    }

    @ParameterizedTest(name = "{0} — раскрытие FAQ #4")
    @ValueSource(strings = {"chrome", "firefox"})
    @DisplayName("Раскрытие пункта FAQ №4")
    public void testClickToDropdown4(String browser) {
        // 1) Инициализируем драйвер
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // 2) Работаем с POM
        MainPage main = new MainPage(driver);
        main.clickToButtonCookies();

        By panel4 = By.xpath("//div[@class='accordion__panel' and @id='accordion__panel-4']");
        //Проверяем, что текст изначально скрыт (атрибут hidden)
        assertTrue(driver.findElement(panel4).getAttribute("hidden") != null,
                "Панель №4 должна быть скрыта при загрузке в " + browser);

        main.clickToDropdown4();
        // кликаем и проверяем, что текст появился
        assertFalse(driver.findElement(panel4).getAttribute("hidden") != null,
                "Панель №4 не появилась после клика в " + browser);

        // проверяем сам текст
        assertEquals("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                driver.findElement(panel4).getText());
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}