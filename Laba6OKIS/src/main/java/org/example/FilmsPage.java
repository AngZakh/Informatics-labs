package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FilmsPage {
    private WebDriver driver;

    public By VsplOknoClose = By.cssSelector("button.banner-mobileapp__close");
    private By filmTitle = By.xpath("//div/h1"); // вывод названия фильма
    private By nowFilmsList = By.xpath("//div/h5[contains(@class, 'poster-heading')]"); // Все постеры фильмов Сейчас в кино"
    private By soonFilmsList = By.xpath("//div[contains(@class, 'poster')]//h5[contains(@class, 'poster-heading')]"); // Все постеры фильмов "Скоро"
    private By raspisanyeButton = By.cssSelector(".posters__btn a.btn.btn--accent"); // Кнопка "Расписание сеансов" в конце страницы
    private By soonButton = By.xpath("//a[text()='Скоро']"); // скоро

    public FilmsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод 1: Открыть страницу
    public void Open() {
        driver.get("https://speshilove.comfortkino.ru/films/");
    }

    // Метод 2: закрытие всплывающего окна
    public void closeOkno() throws InterruptedException{
        WebElement vpsl_okno = driver.findElement(VsplOknoClose);
        vpsl_okno.click();
    }

    // Метод 3: Нажатие на кнопку "Расписание сеансов" в конце страницы
    public void ClickRaspisanye() {
        WebElement button = driver.findElement(raspisanyeButton);
        // 1. Сначала прокручиваем до элемента
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    // Метод 4: Нажатие на вкладку "Скоро"
    public void ClickSoon() {
        driver.findElement(soonButton).click();
    }

    // Метод 5: Получение списка фильмов "Сейчас в кино"
    public List<WebElement> getNowFilmsList() {
        return driver.findElements(nowFilmsList);
    }

    // Метод 6: Открытие 4 фильма в списке "Сейчас в кино"
    public void FourFilm() {
        List<WebElement> films = driver.findElements(nowFilmsList);
        if (films.size() >= 4) {
            driver.findElements(nowFilmsList).get(3).click(); // индекс 3, но клик по 4 фильму
        } else {
            System.out.println("В прокате сейчас меньше 4-х фильмов.");
        }
    }
    // Метод 7: Выводит название фильма
    public String getFilmTitle() {
        return driver.findElement(filmTitle).getText();
    }
    // Метод 8: Получение списка фильмов "Скоро"
    public List<WebElement> getSoonFilmsList() {
        driver.findElement(soonButton).click();
        return driver.findElements(soonFilmsList);
    }
}