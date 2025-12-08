package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;
public class BrChrome {
    public static void main(String[] args) throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://testng.org/");
        System.out.println("Заголовок сайта testng.org: " + driver.getTitle());
        List<WebElement> toc_El = driver.findElements(By.cssSelector("ul.toc-list a.toc-link.node-name--H2 "));
        System.out.println("Список Table of Contents:");
        for (WebElement el : toc_El) {
            System.out.println(el.getText());
        }
        Thread.sleep(500);
        WebElement nazhatieCSS = driver.findElement(By.cssSelector("ul.toc-list a[href='#_download']"));
        nazhatieCSS.click();
        Thread.sleep(1000);
        WebElement nazhatieXpath = driver.findElement(By.xpath("//a[@href='#_testng_documentation']"));
        nazhatieXpath.click();
        Thread.sleep(1000);
        driver.quit();
    }
}
