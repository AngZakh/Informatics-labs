from datetime import date
import re
#Хранение задач
zadachi = []
#Класс задачи
class Task:
    def __init__(zadacha, text, prioritet, srokZadachi):
        zadacha.text = text.strip()
        zadacha.prioritet = prioritet
        if srokZadachi:
            zadacha.srokZadachi = srokZadachi
        else:
            zadacha.srokZadachi = "-"
        zadacha.completed = False
        zadacha.created = str(date.today())

    def short_prioritet(zadacha):
        if zadacha.prioritet == "Высокий" :
            return "В"
        elif zadacha.prioritet == "Средний":
            return "С"
        else:
            return "Н"

#Консольная программа
def main():
    while True:
        print("\nМеню:")
        print("1 — Показать все задачи")
        print("2 — Добавить новую задачу")
        print("3 — Удалить задачу")
        print("4 — Изменить статус задачи")
        print("5 — Показать детали задачи")
        print("6 — Найти задачу")
        print("7 — Удалить все задачи")
        print("8 — Показать только выполненные задачи")
        print("9 — Показать только невыполненные задачи")
        print("0 — Выход")

        vvod = input("Введите команду: ").strip()

        if vvod == "1":
            if not zadachi:
                print("Список задач пуст.")
            for i in range(len(zadachi)):
                print(f"{i+1}.", otobrazhenie(i))

        elif vvod == "2":
            text = input("Введите текст задачи: ")
            if not text:
                print("Ошибка: текст задачи не может быть пустым")
                continue
            prioritet = input("Введите приоритет (Высокий/Средний/Низкий или 1/2/3): ")
            # преобразуем цифры в приоритеты
            if prioritet == "1":
                prioritet = "Высокий"
            elif prioritet == "2":
                prioritet = "Средний"
            elif prioritet == "3":
                prioritet = "Низкий"
            elif prioritet not in ["Высокий", "Средний", "Низкий"]:
                print("Ошибка: некорректный приоритет")
                continue

            srokZadachi = input("Введите крайний срок (ДД.ММ.ГГГГ или нажмите Enter для пропуска): ")

            if srokZadachi.strip() == "":
                srokZadachi = None
            elif not re.match(r"\d{2}\.\d{2}\.\d{4}", srokZadachi):
                print("Ошибка: введена некорректная дата")
                srokZadachi = None
            if dobavit(text, prioritet, srokZadachi):
                print("Задача добавлена!")
            else:
                print("Ошибка: не удалось добавить задачу.")
        elif vvod == "3":
            i = int(input("Индекс задачи для удаления: "))
            if udalit(i-1):
                print("Удалено")
            else:
                print("Ошибка")

        elif vvod == "4":
            i = int(input("Индекс задачи для изменения статуса: "))
            if smenastatus(i-1):
                print("Статус изменён")
            else:
                print("Ошибка")

        elif vvod == "5":
            i = int(input("Индекс задачи для просмотра деталей: "))
            if detali(i-1):
                print(detali(i-1))
            else:
                print("Нет такой задачи")

        elif vvod == "6":
            text = input("Введите текст для поиска: ")
            nomer = poiskpotext(text)
            if nomer != -1:
                print(f"Задача найдена под индексом {nomer+1}: {otobrazhenie(nomer)}")
            else:
                print("Задача не найдена.")

        elif vvod == "7":
            result = allUdalenie()
            if result == 0:
                print("Список задач уже пуст.")
            elif result > 0:
                print(f"Удалено задач: {result}")
        elif vvod == "8":
            filtered = filterByStatus(True)
            if not filtered:
                print("Выполненных задач нет.")
            else:
                print("\n=== ВЫПОЛНЕННЫЕ ЗАДАЧИ ===")
                for i, z in enumerate(filtered):
                    print(f"{i + 1}. {otobrazhenie(zadachi.index(z))}")

        elif vvod == "9":
            filtered = filterByStatus(False)
            if not filtered:
                print("Невыполненных задач нет.")
            else:
                print("\n=== НЕВЫПОЛНЕННЫЕ ЗАДАЧИ ===")
                for i, z in enumerate(filtered):
                    print(f"{i + 1}. {otobrazhenie(zadachi.index(z))}")
        elif vvod == "0":
            print("До свидания")
            break

        else:
            print("Неизвестная команда")

#Основные функции
def dobavit(text, prioritet, srokZadachi):
    if text is None or text.strip() == "":
        return False
    else:
        zadachi.append(Task(text, prioritet, srokZadachi))
        return True

def udalit(nomer: int):
    if nomer < 0 or nomer >= len(zadachi):
        return False
    zadachi.pop(nomer)
    return True

def allUdalenie(podtverzhdenie):
    if len(zadachi) == 0:
        return 0
    if podtverzhdenie.lower() == 'да':
        count = clearAll()
        return count
    else:
        return 0

def smenastatus(nomer: int):
    if nomer < 0 or nomer >= len(zadachi):
        return False
    zadachi[nomer].completed = not zadachi[nomer].completed
    return True

def chislozadach():
    return len(zadachi)

def otobrazhenie(nomer: int):
    if nomer < 0 or nomer >= len(zadachi):
        return None
    t = zadachi[nomer]
    status = "[x]" if t.completed else "[ ]"
    return f"{status} ({t.short_prioritet()}) {t.text} [до: {t.srokZadachi}]"

def detali(nomer: int):
    if nomer < 0 or nomer >= len(zadachi):
        return None
    t = zadachi[nomer]
    status = "Выполнено" if t.completed else "Не выполнено"
    return (
        f"Текст: {t.text}\n"
        f"Статус: {status}\n"
        f"Приоритет: {t.prioritet}\n"
        f"Срок: {t.srokZadachi}\n"
        f"Создано: {t.created}"
    )

def poiskpotext(text):
    for i, t in enumerate(zadachi):
        if text in t.text:
            return i
    return -1

def clearAll():
    count = len(zadachi)
    zadachi.clear()
    return count
def filterByStatus(completedStatus: bool):
    return [z for z in zadachi if z.completed != completedStatus]
if __name__ == "__main__": #чтобы программа не запускалась сама в тестах
    main()

