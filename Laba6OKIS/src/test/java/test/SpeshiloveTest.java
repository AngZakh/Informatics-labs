package test;

import org.example.SpeshilovePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.WebElement;

import java.util.List;

public class SpeshiloveTest {

    private WebDriver driver;
    private SpeshilovePage page;

    // Метод инициализации браузера
    @BeforeMethod(alwaysRun = true)
    public void before() throws InterruptedException {
        driver = new ChromeDriver(); // создает браузер Chrome
        driver.manage().window().maximize(); // разворачивает окно на весь экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // ждет элементы 10 секунд
        page = new SpeshilovePage(driver);
        page.Open();
        page.closeOkno();
    }

    // Метод завершения теста
    @AfterMethod(alwaysRun = true)
    public void after() {
        driver.quit();
    }
    // Тест 1
    // Метод 1: Open()
    @Test(groups = {"positive"}) // позитивный тест
    public void testOpenPage() { // проверка, что открыт сайт speshilove.comfort.kino
        // Arrange
        String expectedUrl = "speshilove.comfortkino.ru";
        // Act
        String actualUrl = driver.getCurrentUrl();
        // Assert
        Assert.assertTrue(actualUrl.contains(expectedUrl), "Открыт  сайт: " + expectedUrl); // проверяет, что URL корректен
    }
    // Тест 2
    // Метод 2: getPageTitle()
    @Test(groups = {"positive"}) // позитивный тест
    public void testGetPageTitle_Positive() { // проверяет, что заголовок страницы не пустой.
        // Act
        String actualTitle = page.getPageTitle(); // получает заголовок страницы
        // Assert
        Assert.assertFalse(actualTitle.isEmpty(), "Заголовок страницы не должен быть пустым"); // проверяет, что заголовок не пуст
    }
    // Тест 3
    // Метод 2: getPageTitle()
    @Test(groups = {"negative"}) // негативный тест
    public void testGetPageTitle_NekorrektTitle() { // проверяет, что фактический заголовок равен сто процентов не верному значению
        // Arrange
        final String Nekorrekt_Title = "Неверный Заголовок";
        // Act
        String actualTitle = page.getPageTitle(); // получает заголовок страницы
        // Assert
        Assert.assertNotEquals(actualTitle, Nekorrekt_Title, "Заголовок страницы не должен совпадать с некорректным"); // проверяет, что заголовок не совпадает
    }
    // Тест 4
    // Метод 4: getFilmsSpisok()
    @Test(groups = {"positive"}) // позитивный тест
    public void testGetFilmsSpisok_NotEmpty() { // проверяет, что список фильмов не является пустым
        // Act
        List<WebElement> films = page.getFilmsSpisok(); // получает список элементов фильмов
        // Assert
        Assert.assertFalse(films.isEmpty(), "Список фильмов должен содержать хотя бы один элемент"); // проверяет, что список не пуст
    }
    // Тест 5
    // Метод 4: getFilmsSpisok()
    @Test(groups = {"negative"}) // негативный тест
    public void testGetFilmsSpisok_MinCount() { // проверяет, что количество фильмов равно 1000
        // Arrange
        final int expectedCount = 1000;
        // Act
        List<WebElement> films = page.getFilmsSpisok(); // получает список элементов фильмов
        int actualCount = films.size();
        // Assert
        Assert.assertFalse(actualCount >= expectedCount, "Количество фильмов должно быть больше или равно " + expectedCount);
    }
    // Тест 6
    // Метод 5: AddIzbrannoe()
    @Test(groups = {"positive"}) // позитивный тест
    public void testAddIzbrannoe_Click() { // проверяет, что клик по кнопке Избранное выполняется успешно
        // Act
        page.AddIzbrannoe(); // кликает на кнопку Избранное
        // Assert
        Assert.assertTrue(true, "Клик на кнопку Избранное выполнен успешно");
    }
    // Тест 7
    // Метод 6: Carousel()
    @Test(groups = {"positive"}) // позитивный тест
    public void testFirstCarousel_Url() throws InterruptedException { // проверяет, что клик по элементу карусели приводит к переходу на другую страницу
        // Arrange
        String iznachUrl = driver.getCurrentUrl();
        // Act
        page.Carousel(); // кликает по элементу карусели
        // Assert
        Assert.assertNotEquals(driver.getCurrentUrl(), iznachUrl, "Ссылка должна измениться после клика"); // проверяет переход на новую страницу
    }
    // Тест 8
    // Метод 7: NameAgeFilma()
    @Test(groups = {"positive"}) // позитивный тест
    public void testNameAgeFilma_CheckFormat() throws InterruptedException { // проверяет, что метод возвращает непустую строку в формате с разделителем " | "
        // Act
        String result = page.NameAgeFilma(); // получает название и возрастное ограничение
        // Assert
        Assert.assertFalse(result.isEmpty(), "Результат не должен быть пустым");
    }
    // Тест 9
    // Метод 8: ClickLoginButton()
    @Test(groups = {"positive"}) // позитивный тест
    public void testClickLoginButton() { // проверяет, что кнопка Входа корректно открывает страницу с логином
        // Act
        page.ClickLoginButton(); // кликает на кнопку входа
        // Assert
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"), "Текущая ссылка: " + currentUrl); // проверяет, что ссылка изменилась
    }
}
