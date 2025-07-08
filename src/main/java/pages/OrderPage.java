package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    public void fieldName(String firstName){

        driver.findElement(name).sendKeys(firstName);
    }
    // Заполнить поле "Фамилия"
    public void fieldSurname(String lastName){

        driver.findElement(surname).sendKeys(lastName);
    }
    // Заполнить поле "Адрес"
    public void fieldAdress(String address){

        driver.findElement(adress).sendKeys(address);
    }
    // Заполнить поле "Станция метро"
    public void fieldMetroStation(String metro){
        WebElement metroField = driver.findElement(metroStation);
        metroField.click();
        metroField.sendKeys(metro);
        metroField.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }
    // Заполнить поле "Телефон"
    public void fieldPhoneNumber(String phone){

        driver.findElement(phoneNumber).sendKeys(phone);
    }
    // Нажать ЛКМ на кнопку "Далее"
    public void clickButton(){

        driver.findElement(button).click();
    }

}