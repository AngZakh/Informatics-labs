package test;

import org.example.FilmsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;

public class FilmsTest {

    private WebDriver driver;
    private FilmsPage filmsPage;

    // Метод инициализации браузера
    @BeforeMethod(alwaysRun = true)
    public void before() throws InterruptedException{
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        filmsPage = new FilmsPage(driver);
        filmsPage.Open();
        filmsPage.closeOkno();
    }

    // Метод завершения теста
    @AfterMethod(alwaysRun = true)
    public void after() {
        driver.quit();
    }

    // Тест 1
    // Метод 1: Open() и Метод 5: getNowFilmsList()
    @Test(groups = {"positive"}) // позитивный тест
    public void testOpenPage() { // проверяет, что страница успешно открыта и список фильмов "Сейчас в кино" не пуст
        // Arrange - подготовка данных
        String expectedUrlPart = "comfortkino.ru/films/";
        // Act - выполнение действий
        String actualUrl = driver.getCurrentUrl();
        // Получаем список фильмов "Сейчас в кино"
        List<WebElement> nowFilms = filmsPage.getNowFilmsList();
        // Assert - проверка результатов
        Assert.assertTrue(actualUrl.contains(expectedUrlPart),
                "Ссылка должна верно открыться: " + expectedUrlPart);
        // Проверяем что список фильмов не пустой
        Assert.assertFalse(nowFilms.isEmpty(),
                "Список фильмов 'Сейчас в кино' не должен быть пустым");
    }

    // Тест 2
    // Метод 4: ClickSoon() и Метод 8: getSoonFilmsList()
    @Test(groups = {"positive"}) // позитивный тест
    public void testSoonFilmsList() { // проверяет, что клик по вкладке "Скоро" открывает корректный список фильмов
        // Act - выполнение действий
        filmsPage.ClickSoon();
        // Получаем список фильмов "Скоро"
        List<WebElement> soonFilms = filmsPage.getSoonFilmsList();
        // Assert - проверка результатов
        // Проверяем что список фильмов "Скоро" не пустой
        Assert.assertFalse(soonFilms.isEmpty(), "Список фильмов 'Скоро' не должен быть после переключения вкладки");
    }

    // Тест 3
    // Метод 6: FourFilm() и Метод 7: getFilmTitle()
    @Test(groups = {"positive"}) // позитивный тест
    public void testFourFilmAndGetFilmTitle() { // проверяет, что клик по 4-му фильму приводит к переходу на его страницу и получению непустого заголовка
        // Arrange - подготовка данных
        List<WebElement> films = filmsPage.getNowFilmsList();
        // Проверяем что фильмов достаточно для теста
        if (films.size() < 4) {
            Assert.fail("В списке 'Сейчас в кино' меньше 4 фильмов для теста.");
        }
        // Act - выполнение действий
        String oldUrl = driver.getCurrentUrl();
        filmsPage.FourFilm();
        // Получаем новую ссылку после перехода
        String newUrl = driver.getCurrentUrl();
        String filmTitle = filmsPage.getFilmTitle();
        // Assert - проверка результатов
        Assert.assertNotEquals(oldUrl, newUrl, "URL должен измениться после перехода на страницу фильма");
        // Проверяем что название фильма не пустое
        Assert.assertFalse(filmTitle.trim().isEmpty(), "Заголовок открытого фильма не должен быть пустым");
    }

    // Тест 4
    // Метод 3: ClickRaspisanye()
    @Test(groups = {"positive"}) // позитивный тест
    public void testClickRaspisanye() { // проверяет, что кнопка "Расписание сеансов" корректно перенаправляет на страницу расписания
        // Arrange - подготовка данных
        String oldUrl = driver.getCurrentUrl();
        // Act - выполнение действий
        filmsPage.ClickRaspisanye();
        String newUrl = driver.getCurrentUrl();
        // Assert - проверка результатов
        Assert.assertNotEquals(oldUrl, newUrl, "URL должен измениться после клика на 'Расписание сеансов'");
        // Проверяем что произошел переход на главную страницу кинотеатра
        Assert.assertTrue(newUrl.contains("comfortkino.ru"), "Должен произойти переход на главную страницу кинотеатра");
    }

    // Тест 5
    // Метод 5: getNowFilmsList()
    @Test(groups = {"negative"}) // негативный тест
    public void testGetNowFilmsList() { // проверяет, что количество фильмов "Сейчас в кино" равен 10000
        // Arrange - подготовка данных
        final int expectedcount = 10000;
        // Act - выполнение действий
        List<WebElement> films = filmsPage.getNowFilmsList();
        int actualCount = films.size();
        // Assert - проверка результатов
        // Проверяем что фильмы равны ожидаемым 10000
        Assert.assertFalse(actualCount == expectedcount);
    }

    // Тест 6
    // Метод 7: getFilmTitle()
    @Test(groups = {"negative"}) // негативный тест
    public void testGetFilmTitle_NetTitle() { // проверяет, что метод getFilmTitle() не находит элемент на главной странице, где его быть не должно
        // Act - выполнение действий
        String actualTitle = filmsPage.getFilmTitle();
        // Assert - проверка результатов
        Assert.assertTrue(actualTitle == null || actualTitle.isEmpty(), "Метод getFilmTitle() должен возвращать пустое значение на главной странице, при этом получено: " + actualTitle);
    }

    // Тест 7
    // Метод 4: ClickSoon()
    @Test(groups = {"negative"}) // негативный тест
    public void testClickSoon_NetScoro() { //переходим на страницу без кнопки скоро
        // Arrange - подготовка данных
        driver.get("https://speshilove.comfortkino.ru/"); // страница без вкладки "Скоро"
        // Act - выполнение действия
        boolean iskluch = false;
        try {
            filmsPage.ClickSoon();
        } catch (Exception e) {
            iskluch = true; // фиксируем факт выброса исключения
        }
        // Assert - проверка результата
        Assert.assertTrue(iskluch, "Ожидается ошибка при клике на вкладку 'Скоро', отсутствующую на странице");
    }

    // Тест 8
    // Метод 8: getSoonFilmsList()
    @Test(groups = {"negative"}) // негативный тест
    public void testGetSoonFilmsList_NetSpiskaScoro() {
        // Arrange - переходим на страницу без вкладки "Скоро"
        driver.get("https://speshilove.comfortkino.ru/"); // главная страница, вкладка отсутствует
        // Act - пытаемся получить список фильмов и отлавливаем исключение
        boolean iskluch = false;
        try {
            filmsPage.getSoonFilmsList();
        } catch (Exception e) {
            iskluch = true; // фиксируем факт выброса исключения
        }
        // Assert
        Assert.assertTrue(iskluch, "Ожидается, что метод getSoonFilmsList() выбросит исключение при отсутствии списка фильмов на вкладке 'Скоро'");
    }
}