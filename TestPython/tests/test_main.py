import pytest
from src.main import (dobavit, udalit, smenastatus, chislozadach,
                      otobrazhenie, detali, poiskpotext, clearAll, zadachi, allUdalenie, filterByStatus)
#Основные операции
@pytest.mark.osnovnye
def test_Dobavit():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    text = ""
    prioritet = "Высокий"
    srokZadachi = "31.12.2024"
    expectedresult = False
    #ACT - ДЕЙСТВИЕ
    actual_result = dobavit(text, prioritet, srokZadachi)
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult

@pytest.mark.osnovnye
def test_Udalit():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    dobavit("Тестовая задача", "2", "25.12.2025")
    expectedresult = False
    #ACT - ДЕЙСТВИЕ
    actual_result = udalit(0)
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult
    assert chislozadach() == 0

@pytest.mark.osnovnye
def test_smenastatus():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    dobavit("Задача для теста", "Низкий", "30.12.2024")
    expectedresult = True
    #ACT - ДЕЙСТВИЕ
    actual_result = smenastatus(0)
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult

@pytest.mark.osnovnye
def test_poiskpotext():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    dobavit("Купить продукты", "2", "20.12.2025")
    dobavit("Сделать уроки", "1", "18.12.2025")
    expectedresult = 1
    #ACT - ДЕЙСТВИЕ
    actual_result = poiskpotext("уроки")
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult

@pytest.mark.osnovnye
def test_filterzadachi():
    # ARRANGE
    clearAll()
    dobavit("Задача 1", "1", "31.12.2025")
    dobavit("Задача 2", "2", "25.12.2025")
    dobavit("Задача 3", "3", "20.12.2025")
    smenastatus(0)
    smenastatus(2)
    # ACT
    completed_zadachi = filterByStatus(True)
    not_completed_zadachi = filterByStatus(False)
    # ASSERT
    assert len(completed_zadachi) == 2
    assert len(not_completed_zadachi) == 1
    assert completed_zadachi[0].completed is True
    assert completed_zadachi[1].completed is True
    assert not_completed_zadachi[0].completed is False
    assert completed_zadachi[0].text == "Задача 1"
    assert completed_zadachi[1].text == "Задача 3"
    assert not_completed_zadachi[0].text == "Задача 2"

@pytest.mark.osnovnye
def test_udalitvse():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    dobavit("Купить продукты", "Средний", "20.12.2024")
    dobavit("Сделать уроки", "Высокий", "18.12.2024")
    expectedresult = 2
    #ACT - ДЕЙСТВИЕ
    actual_result = allUdalenie("Нет")
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult
    assert chislozadach() == 2

#Отображение
@pytest.mark.otobrazhenie
def test_otobrazhenie():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    dobavit("Показать задачу", "1", "15.12.2025")
    #ACT - ДЕЙСТВИЕ
    actual_result = otobrazhenie(-1)
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result is not None
    assert "Показать задачу" in actual_result

@pytest.mark.otobrazhenie
def test_detali():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    dobavit( "", "2", "20.12.2025")
    actual_result = detali(0)
    assert actual_result is None

@pytest.mark.otobrazhenie
def test_detali2():
    # ARRANGE - ПОДГОТОВКА
    dobavit("Тест задача", "Низкий", "10.12.2024")
    #ACT - ДЕЙСТВИЕ
    actual_details = detali(-1)
    expectedresult = None
    # ASSERT - ПРОВЕРКА
    assert actual_details == expectedresult

#Исключения
@pytest.mark.exceptions
def test_udalitnesush():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    expectedresult = False
    #ACT - ДЕЙСТВИЕ
    actual_result = udalit(5)
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult

@pytest.mark.exceptions
def test_otobrazhenienesush():
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    dobavit("Тест задача", "Низкий", "10.12.2024")
    expectedresult = True
    #ACT - ДЕЙСТВИЕ
    actual_result = otobrazhenie(5)
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult

#Параметризованные тесты
@pytest.mark.data
@pytest.mark.parametrize(
    "text, prioritet, srokZadachi, expectedresult",
    [
        ("Задача 1", "1", "31.12.2025", True),
        ("", "2", "20.12.2025", False),
        ("   ", "3", "25.12.2025", False),
        ("Задача 4", "1", "неправильная дата", True)
    ],
    ids=["Задача 1","Пустой текст","Только пробелы","Неправильная дата"]
)
def test_dobavit_data(text, prioritet, srokZadachi, expectedresult):
    #ARRANGE - ПОДГОТОВКА ТЕСТОВОГО ОКРУЖЕНИЯ
    clearAll()
    #ACT - ДЕЙСТВИЕ
    actual_result = dobavit(text, prioritet, srokZadachi)
    #ASSERT - СРАВНЕНИЕ ОЖИД. И ФАКТ. РЕЗУЛЬТАТОВ
    assert actual_result == expectedresult