package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== МЕНЕДЖЕР ЗАДАЧ ===");

        while (true) {
            Menu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> VseZadachi();
                case 2 -> newZadacha();
                case 3 -> udalenie();
                case 4 -> smenaStatusa();
                case 5 -> informaciaZadacha();
                case 6 -> poisk();
                case 7 -> alludalenie();
                case 8 -> filtracia(true);  // показать выполненные
                case 9 -> filtracia(false); // показать невыполненные
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    // Показывает главное меню программы
    private static void Menu() {
        System.out.println("\nМеню");
        System.out.println("1. Показать все задачи");
        System.out.println("2. Добавить новую задачу");
        System.out.println("3. Удалить задачу");
        System.out.println("4. Изменить статус задачи");
        System.out.println("5. Показать детали задачи");
        System.out.println("6. Найти задачу");
        System.out.println("7. Удалить все задачи");
        System.out.println("8. Показать только выполненные задачи");
        System.out.println("9. Показать только невыполненные задачи");
        System.out.println("0. Выход");
        System.out.print("Выберите: ");
    }

    // Показывает все задачи
    private static void VseZadachi() {
        System.out.println("\nВсе задачи");
        if (tasks.isEmpty()) {
            System.out.println("Задач нет.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + otobrazhenie(i));
        }
    }

    // Добавляет новую задачу
    private static void newZadacha() {
        System.out.print("Введите текст задачи: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Ошибка: текст задачи не может быть пустым");
            return;
        }

        System.out.print("Введите приоритет (Высокий/Средний/Низкий или 1/2/3): ");
        String prioritet = scanner.nextLine().trim();

        if (prioritet.equals("1")) prioritet = "Высокий";
        else if (prioritet.equals("2")) prioritet = "Средний";
        else if (prioritet.equals("3")) prioritet = "Низкий";
        else if (!prioritet.equals("Высокий") && !prioritet.equals("Средний") && !prioritet.equals("Низкий")) {
            System.out.println("Ошибка: некорректный приоритет");
            return;
        }

        System.out.print("Введите срок (ДД.ММ.ГГГГ или Enter для пропуска): ");
        String srokZadachi = scanner.nextLine().trim();
        if (srokZadachi.isEmpty()) srokZadachi = null;
        else if (!srokZadachi.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            System.out.println("Ошибка: введена некорректная дата!");
            srokZadachi = null;
        }

        if (dobavit(text, prioritet, srokZadachi)) System.out.println("Задача добавлена!");
        else System.out.println("Ошибка: не удалось добавить задачу.");
    }

    // Удаление задачи
    private static void udalenie() {
        VseZadachi();
        if (tasks.isEmpty()) return;

        System.out.print("Введите номер задачи для удаления: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (udalit(index)) System.out.println("Задача удалена!");
        else System.out.println("Ошибка: неверный номер задачи.");
    }

    // Смена статуса
    private static void smenaStatusa() {
        VseZadachi();
        if (tasks.isEmpty()) return;

        System.out.print("Введите номер задачи для изменения статуса: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (smenastatus(index)) System.out.println("Статус задачи изменен!");
        else System.out.println("Ошибка: неверный номер задачи.");
    }

    // Показ деталей
    private static void informaciaZadacha() {
        VseZadachi();
        if (tasks.isEmpty()) return;

        System.out.print("Введите номер задачи для просмотра деталей: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        String details = detali(index);
        if (details != null) {
            System.out.println("\nДетали задачи");
            System.out.println(details);
        } else System.out.println("Ошибка: неверный номер задачи.");
    }

    // Поиск задачи по тексту
    private static void poisk() {
        System.out.print("Введите текст для поиска: ");
        String searchText = scanner.nextLine();

        int index = poiskpotext(searchText);
        if (index != -1) {
            System.out.println("Задача найдена под номером: " + (index + 1));
            System.out.println(otobrazhenie(index));
        } else System.out.println("Задача не найдена.");
    }

    // Удаление всех задач
    private static void alludalenie() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач уже пуст.");
            return;
        }

        System.out.print("Вы уверены, что хотите удалить все задачи? (Да/Нет): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Да")) {
            int count = clearAll();
            System.out.println("Удалено задач: " + count);
        }
    }

    // Показывает задачи по статусу
    private static void filtracia(boolean completedStatus) {
        List<Task> filteredTasks = Filtracia(completedStatus);
        if (filteredTasks.isEmpty()) {
            System.out.println(completedStatus ? "Выполненных задач нет." : "Невыполненных задач нет.");
            return;
        }
        System.out.println(completedStatus ? "\nВыполненные задачи" : "\nНевыполненные задачи");
        for (int i = 0; i < filteredTasks.size(); i++) {
            Task task = filteredTasks.get(i);
            System.out.println((i + 1) + ". " + otobrazhenie(tasks.indexOf(task)));
        }
    }

    // Методы для тестирования и работы с задачами
    public static boolean dobavit(String text, String prioritet, String srokZadachi) {
        if (text == null || text.trim().isEmpty()) return false;
        Task task = new Task(text.trim(), prioritet, srokZadachi);
        tasks.add(task);
        return true;
    }

    public static boolean udalit(int index) {
        if (index < 0 || index >= tasks.size()) return false;
        tasks.remove(index);
        return true;
    }

    public static boolean smenastatus(int index) {
        if (index < 0 || index >= tasks.size()) return false;
        Task task = tasks.get(index);
        task.completed = !task.completed;
        return true;
    }

    public static int chislozadach() {
        return tasks.size();
    }

    public static String otobrazhenie(int index) {
        if (index < 0 || index >= tasks.size()) return null;
        Task task = tasks.get(index);
        String status = task.completed ? "[x]" : "[ ]";
        String prioritet = task.prioritet.equals("Высокий") ? "В" :
                task.prioritet.equals("Средний") ? "С" : "Н";
        return status + " (" + prioritet + ") " + task.text + " [до: " + task.srokZadachi + "]";
    }

    public static String detali(int index) {
        if (index < 0 || index >= tasks.size()) return null;
        Task task = tasks.get(index);
        String status = task.completed ? "Выполнено" : "Не выполнено";
        return "Текст: " + task.text + "\n" +
                "Статус: " + status + "\n" +
                "Приоритет: " + task.prioritet + "\n" +
                "Срок: " + task.srokZadachi + "\n" +
                "Создано: " + task.created;
    }

    public static int poiskpotext(String searchText) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).text.contains(searchText)) return i;
        }
        return -1;
    }
    public static int udalitVse(String confirm) {
        if (tasks.isEmpty()) {
            System.out.println("Список задач уже пуст.");
            return 0;
        }

        if (confirm.equalsIgnoreCase("Да")) {
            int count = clearAll();
            return count;
        }
        return 0;
    }
    public static int clearAll() {
        int count = tasks.size();
        tasks.clear();
        return count;
    }
    public static List<Task> Filtracia(boolean completedStatus) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.completed == completedStatus) filteredTasks.add(task);
        }
        return filteredTasks;
    }
    // Класс задачи
    static class Task {
        String text;
        boolean completed;
        String prioritet;
        String srokZadachi;
        String created;

        Task(String text, String prioritet, String srokZadachi) {
            this.text = text;
            this.prioritet = prioritet;
            this.srokZadachi = srokZadachi != null ? srokZadachi : "-";
            this.created = java.time.LocalDate.now().toString();
            this.completed = false;
        }
    }
}
