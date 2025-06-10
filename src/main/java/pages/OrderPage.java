package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;
    // Поле "Имя"
    private By name = By.xpath("//input[@placeholder='* Имя']");
    // Поле "Фамилия"
    private By surname = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле "Адрес: куда вести заказ"
    private By adress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "Станция метро"
    private By metroStation= By.xpath("//input[@placeholder='* Станция метро']");
    // Поле "Телефон"
    private By phoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private By button = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPage(WebDriver driver){
        this.driver=driver;
    }
    // Заполнить поле "Имя"
    public void fieldName(){
        driver.findElement(name).sendKeys("Иванов");
    }
    // Заполнить поле "Фамилия"
    public void fieldSurname(){
        driver.findElement(surname).sendKeys("Иван");
    }
    // Заполнить поле "Адрес"
    public void fieldAdress(){
        driver.findElement(adress).sendKeys("Ивановская 1");
    }
    // Заполнить поле "Станция метро"
    public void fieldMetroStation(){
        // 1. Находим поле ввода и кликаем по нему
        WebElement dropdownInput = driver.findElement(By.xpath("//input[@placeholder='* Станция метро']"));
        dropdownInput.click();
        // 2. Ждем появления выпадающего списка
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //3. Выбираем вариант. Использование клавиш.
        dropdownInput.sendKeys("Черкизовская");
        dropdownInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }
    // Заполнить поле "Телефон"
    public void fieldPhoneNumber(){
        driver.findElement(phoneNumber).sendKeys("89001112233");
    }
    // Нажать ЛКМ на кнопку "Далее"
    public void clickButton(){
        driver.findElement(button).click();
    }

}