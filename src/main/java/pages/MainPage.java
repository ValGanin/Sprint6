package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage {

    private WebDriver driver;
    //Кнопка "да все привыкли" для принятия cookies
    private By coociesClick = By.id("rcc-confirm-button");
    //Выпадающий список с текстом "Сколько это стоит? И как оплатить?".
    private By dropdown0 = By.id("accordion__heading-0");
    //Можно ли продлить заказ или вернуть самокат раньше?".
    private By dropdown4 = By.id("accordion__heading-4");
    //Верхняя кнопка "Заказать" находящаяся на хедере.
    private By buttonOrderUp = By.xpath("//button[@class='Button_Button__ra12g']");
    //Нижняя кнопка "Заказать" находящаяся внизу страницы.
    private By buttonOrderDown = By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");


    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    //Принять cookies
    public void clickToButtonCookies(){
        driver.findElement(coociesClick).click();
    }

    //Раскрыть выпадающий список с текстом "Сколько это стоит? И как оплатить?"
    public void clickToDropdown0(){
        driver.findElement(dropdown0).click();

    }
    //Можно ли продлить заказ или вернуть самокат раньше?"
    public void clickToDropdown4(){
        driver.findElement(dropdown4).click();
    }
    //Шаг, который объединяет в себе сразу 2-ва методы описанных выше
    public void clickToAllDropdown(){
        clickToDropdown0();
        clickToDropdown4();
    }

    public void clickToButtonOrderUp(){
        driver.findElement(buttonOrderUp).click();
    }

    public void clickToButtonOrderDown(){
        driver.findElement(buttonOrderDown).click();
    }
}