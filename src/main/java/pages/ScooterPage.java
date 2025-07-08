package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ScooterPage {

    private WebDriver driver;
    // Поле "Когда привезти самокат"
    private By date = By.xpath("//div[@class='react-datepicker__input-container']");
    // Выбор даты
    private By dateChoice = By.xpath("//div[@class='react-datepicker__day react-datepicker__day--010' and @aria-label='Choose вторник, 10-е июня 2025 г.']");
    // Поле "Срок аренды"
    private By rent = By.xpath("//div[@class='Dropdown-root']");
    // Выбор срока
    private By rentChoice = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");
    // Чек-бокс "Цвет самоката"
    private By color = By.xpath("//input[@id='grey']");
    // Поле "Комментарий для курьера"
    private By comment = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    // Кнопка "Заказать"
    private By buttonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // Кнопка "Да"
    private By buttonYes= By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    // Кнопка "Посмотреть статус"
    private By buttonSeeStatus= By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");

    public ScooterPage(WebDriver driver){
        this.driver=driver;
    }
    // Заполнить поле "Когда привезти самокат"
    public void fieldDate(){
        driver.findElement(date).click();
        driver.findElement(dateChoice).click();
    }
    // Заполнить поле "Срок аренды"
    public void fieldRent(){
        driver.findElement(rent).click();
        driver.findElement(rentChoice).click();
    }
    // Выбрать чек-бокс "Цвет самоката"
    public void fieldСolor(){
        driver.findElement(color).click();
    }
    // Заполнить поле "Комментарий для курьера"
    public void fieldComment(){
        driver.findElement(comment).sendKeys("Тестовый комментарий для курьера");
    }
    // Нажать на кнопку "Заказать"
    public void  clickButtonOrder(){
        driver.findElement(buttonOrder).click();
    }
    // Нажать на кнопку "Да"
    public void  clickButtonYes(){
        driver.findElement(buttonYes).click();
    }
    // Нажать на кнопку "Посмотреть статус"
    public void  clickButtonSeeStatus(){
        driver.findElement(buttonSeeStatus).click();
    }
}