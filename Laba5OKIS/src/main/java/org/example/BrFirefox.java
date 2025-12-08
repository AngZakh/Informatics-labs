package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import java.util.List;
public class BrFirefox {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        boolean testLime = true;
        boolean testSpeshilove = false;
        boolean testAlendvic = false;
        if (testLime) {
            testLime(driver);
        }
        if (testSpeshilove) {
            testSpeshilove(driver);
        }
        if (testAlendvic) {
            testAlendvic(driver);
        }
        driver.quit();
    }
    private static void testLime(WebDriver driver) throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(1500);
        driver.get("https://limestore.com/ru_ru");
        Thread.sleep(4500);
        System.out.println("Заголовок сайта limestore.com: " + driver.getTitle());
        WebElement ok = driver.findElement(By.cssSelector("button.l-btn[data-ok]"));
        ok.click();
        Thread.sleep(1000);
        WebElement menu = driver.findElement(By.cssSelector("div.hamburger-menu.burger"));
        menu.click();
        Thread.sleep(2000);
        List<WebElement> list1_El = driver.findElements(By.cssSelector("ul.mainmenu__list li.mainmenu__item"));
        System.out.println("Каталог женщины:");
        for (WebElement el : list1_El) {
            System.out.println(el.getText());
        }
        WebElement trend = driver.findElement(By.xpath("//a[@href='/ru_ru/catalog/bestsellers?menu=4929'" +
                " and contains(@class,'mainmenu__link')]")
        );
        trend.click();
        Thread.sleep(2000);
        List<WebElement> vid = driver.findElements(By.cssSelector("div.layout-selectors__item"));
        vid.get(1).click();
        Thread.sleep(2000);
        WebElement tovar = driver.findElement(By.xpath("(//div[contains(@class,'CatalogProduct') and contains(@class,'isLight')])[1]"));
        tovar.click();
        Thread.sleep(1500);
        driver.navigate().back();
        Thread.sleep(2000);
    }
    private static void testSpeshilove(WebDriver driver) throws InterruptedException {
        driver.get("https://speshilove.comfortkino.ru");
        System.out.println("Заголовок сайта speshilove.comfortkino.ru: " + driver.getTitle());
        WebElement vspl_okno = driver.findElement(By.cssSelector("button.banner-mobileapp__close"));
        vspl_okno.click();
        Thread.sleep(1000);
        List<WebElement> list2_El = driver.findElements(By.cssSelector("div.container nav.top-nav"));
        System.out.println("\nСодержание верхней панели:");
        for (WebElement el : list2_El) {
            System.out.println(el.getText());
        }
        WebElement films = driver.findElement(By.xpath("//a[@href='/films/' and contains(@class,'top-nav__item')]"));
        films.click();
        Thread.sleep(300);
        WebElement prokrutka = driver.findElement(By.cssSelector("body"));
        prokrutka.sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(500);
        WebElement film = driver.findElement(By.xpath("//a[@href='/film/zveropolis-2']"));
        film.click();
        Thread.sleep(1000);
    }
    private static void testAlendvic(WebDriver driver) throws InterruptedException {
        driver.get("https://dostavka-alendvic.ru");
        System.out.println("\nЗаголовок сайта dostavka-alendvic.ru: " + driver.getTitle());
        List<WebElement> list3_El = driver.findElements(By.cssSelector("nav a.navigation__link"));
        System.out.println("\nСодержание верхней панели:");
        for (WebElement el : list3_El) {
            System.out.println(el.getText());
        }
        WebElement menu = driver.findElement(By.cssSelector("a[href='/catalog/']"));
        menu.click();
        Thread.sleep(500);
        WebElement tortili = driver.findElement(By.xpath("//a[.//img[@title='Тортильи и бургеры']]"));
        tortili.click();
        Thread.sleep(500);
        WebElement kombo = driver.findElement(By.xpath("//a[@data-detail-link='/catalog/tortili-i-burgery/kombo-alendvik-darit/']"));
        kombo.click();
        Thread.sleep(4000);
        WebElement close_button = driver.findElement(By.xpath("//button[contains(@class, 'uk-modal-close-full')]"));
        close_button.click();
        Thread.sleep(1000);
    }
}