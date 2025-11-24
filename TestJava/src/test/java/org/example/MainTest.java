package org.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.example.Main.Task;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.List;

import static org.example.Main.*;

public class MainTest {
    // Группа: Основные операции (добавление, удаление)
    //Добавление задачи, положительный
    @Test(groups = {"osnovnye"})
    public void testDobavit() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        final String TEXT = "";
        final String prioritet = "Высокий";
        final String DATE = "31.12.2024";
        final boolean expectedResult = false;
        boolean actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = dobavit(TEXT, prioritet, DATE);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertEquals(actualResult, expectedResult);
    }

    //Удаление одной задачи положительный
    @Test(groups = {"osnovnye"})
    public void testUdalit() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        dobavit("Тестовая задача", "Средний", "25.12.2024");
        final boolean expectedResult = true;
        boolean actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = udalit(0);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertEquals(actualResult, expectedResult);
    }

    //смена статуса задачи, положительный
    @Test(groups = {"osnovnye"})
    public void testSmenastatus() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        dobavit("Задача для теста", "Низкий", "30.12.2024");
        final boolean expectedResult = true;
        boolean actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = smenastatus(-1);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertEquals(actualResult, expectedResult);
    }

    //Поиск по тексту
    //Негативный, текст ожидается в задаче с индексом 1, но он в задаче с индексом 2
    @Test(groups = {"osnovnye"})
    public void testPoiskpotext() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        dobavit("Купить продукты", "Средний", "20.12.2024");
        dobavit("Сделать уроки", "Высокий", "18.12.2024");
        final int expectedResult = 0;
        int actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = poiskpotext("продукты");
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertEquals(actualResult, expectedResult);
    }

    //Негативный, поменяли статус у задачи 1 и 2, а ожидаемый результат - 1 и 3
    @Test(groups = {"osnovnye"})
    public void testFiltraciaVypolnennyh() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        dobavit("Задача 1", "Высокий", "31.12.2024");
        dobavit("Задача 2", "Средний", "25.12.2024");
        dobavit("Задача 3", "Низкий", "20.12.2024");
        smenastatus(0);
        smenastatus(2);
        // ACT - фильтруем выполненные задачи
        List<Task> completedTasks = Filtracia(true);
        List<Task> notCompletedTasks = Filtracia(false);
        // ASSERT
        //Проверка, что конкретные задачи помечены как выполненные или нет
        Assert.assertTrue(completedTasks.get(0).completed);
        Assert.assertTrue(completedTasks.get(1).completed);
        Assert.assertFalse(notCompletedTasks.get(0).completed);
        //Проверка текста задач
        Assert.assertEquals(completedTasks.get(0).text, "Задача 1");
        Assert.assertEquals(completedTasks.get(1).text, "Задача 3");
        Assert.assertEquals(notCompletedTasks.get(0).text, "Задача 2");
    }

    //удаление всех задач
    @Test(groups = {"osnovnye"})
    public void testUdalitVse() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        clearAll();
        dobavit("Купить продукты", "Средний", "20.12.2024");
        dobavit("Сделать уроки", "Высокий", "18.12.2024");
        final int expectedResult = 2;
        int actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = udalitVse("Нет");
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(chislozadach(), 0);
    }

    // Группа: Отображение
    // отображения задачи по индексу
    @Test(groups = {"otobrazhenie"})
    public void testOtobrazhenieVseh() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        dobavit("Показать задачу", "Высокий", "15.12.2024");
        String actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = otobrazhenie(0);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.contains("Показать задачу"));
    }

    // Положительный, отображение деталей задачи
    @Test(groups = {"otobrazhenie"})
    public void testDetali() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        dobavit("Детали задачи", "Средний", "20.12.2024");
        String actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = detali(0);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.contains("Детали задачи"));
    }
    @Test(groups = {"otobrazhenie"})
    public void testDetali3() {
        // ARRANGE - ПОДГОТОВКА
        // ДОБАВЛЯЕМ ОДНУ ЗАДАЧУ
        dobavit("Тест задача", "Низкий", "10.12.2024");

        // ACT - ДЕЙСТВИЕ
        String actualDetails = detali(-1);
        // ASSERT - ПРОВЕРКА
        // ПРОВЕРЯЕМ, ЧТО ВОЗВРАЩАЕТСЯ NULL ДЛЯ ОТРИЦАТЕЛЬНОГО ИНДЕКСА
        Assert.assertNull(actualDetails, "Для отрицательного индекса должен возвращаться null");
    }
    // Группа: Исключения
    // Удаление несуществующей задачи
    @Test(groups = {"exceptions"})
    public void testUdalitNesush() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        final boolean expectedResult = false;
        boolean actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = udalit(5);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertEquals(actualResult, expectedResult);
    }

    // Получение отображение задачи по индексу, превышающему размер списка
    @Test(groups = {"exceptions"})
    public void testOtobrazhenieNesush() {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        clearAll();
        String actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = otobrazhenie(0);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertNull(actualResult);
    }

    // DataProvider для параметризованного тестирования
    @DataProvider(name = "taskData")
    public Object[][] provideTaskData() {
        return new Object[][]{
                {"Задача 1", "Высокий", "31.12.2024", true},
                {"", "Средний", "20.12.2024", false},
                {"  ", "Низкий", "25.12.2024", false},
                {"Задача 4", "Высокий", "неправильная дата", true}
        };
    }

    // Параметризованный тест с DataProvider
    @Test(dataProvider = "taskData", groups = {"data"})
    public void testDobavitDataProvider(String text, String prioritet, String srokZadachi, boolean expectedResult) {
        //ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
        // СОЗДАНИЕ ВСЕХ ОБЪЕКТОВ, КОТОРЫЕ ПОНАДОБЯТСЯ В ТЕСТЕ
        boolean actualResult;
        //ACT - ДЕЙСТВИЕ
        actualResult = dobavit(text, prioritet, srokZadachi);
        //ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
        Assert.assertEquals(actualResult, expectedResult);
    }

}