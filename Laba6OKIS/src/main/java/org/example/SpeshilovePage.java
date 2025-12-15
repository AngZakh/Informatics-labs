package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SpeshilovePage {
    private WebDriver driver;

    public By VsplOknoClose = By.cssSelector("button.banner-mobileapp__close");
    private By FilmsList = By.xpath("//a/h2");
    private By Izbrannoe = By.xpath("//button[contains(@class,'btn--fav')]");
    public By Carousel = By.cssSelector(".owl-item.active a.poster-slide__link");
    private By filmAgeRating = By.cssSelector("a span.age");
    private By filmName = By.xpath("//div/h1");
    private By loginButton = By.cssSelector("a.login");

    public SpeshilovePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод 1: открыть страницу
    public void Open() {
        driver.get("https://speshilove.comfortkino.ru");
    }

    // Метод 2: получить заголовок страницы
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Метод 3: закрытие всплывающего окна
    public void closeOkno() throws InterruptedException{
        WebElement vpsl_okno = driver.findElement(VsplOknoClose);
        vpsl_okno.click();
    }

    // Метод 4: получение списка фильмов в прокате
    public List<WebElement> getFilmsSpisok() {
        return driver.findElements(FilmsList);
    }

    // Метод 5: добавить в избранное
    public void AddIzbrannoe() {
        driver.findElement(Izbrannoe).click();
    }
    // Метод 6: нажать на элемент карусели
    public void Carousel() throws InterruptedException {
        driver.findElement(Carousel).click();
    }

    // Метод 7: получить название и возрастное ограничение фильма
    public String NameAgeFilma() throws InterruptedException {
        Carousel();
        String name = driver.findElement(filmName).getText();
        String age = driver.findElement(filmAgeRating).getText();
        return name + age;
    }
    // Метод 8: кликнуть на вход
    public void ClickLoginButton() {
        driver.findElement(loginButton).click();
    }
}


