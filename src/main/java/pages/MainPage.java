package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage {

    private WebDriver driver;
    //Кнопка "да все привыкли" для принятия cookies
    private By coociesClick = By.id("rcc-confirm-button");
    //Верхняя кнопка "Заказать" находящаяся на хедере.
    private By buttonOrderUp = By.xpath("//button[@class='Button_Button__ra12g']");
    //Нижняя кнопка "Заказать" находящаяся внизу страницы.
    private By buttonOrderDown = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    //Принять cookies
    public void clickToButtonCookies(){
        driver.findElement(coociesClick).click();
    }

    // Клик по заголовку FAQ по его индексу (0,1,2…) */
    public void clickDropdown(int index) {
        By question = By.id("accordion__heading-" + index);
        driver.findElement(question).click();
    }

    // Проверить, скрыт ли ответ (есть атрибут hidden) */
    public boolean isAnswerHidden(int index) {
        By answer = By.id("accordion__panel-" + index);
        return driver.findElement(answer).getAttribute("hidden") != null;
    }

    // Получить текст ответа FAQ */
    public String getAnswerText(int index) {
        By answer = By.id("accordion__panel-" + index);
        return driver.findElement(answer).getText();
    }

    public void clickToButtonOrderUp(){
        driver.findElement(buttonOrderUp).click();
    }

    public void clickToButtonOrderDown(){
        driver.findElement(buttonOrderDown).click();
    }
}