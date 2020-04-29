import javax.xml.bind.DataBindingException;
import java.util.Scanner;

// Класс отвечает за работоспособность всего приложения в целом.
public class App {
    //
    private static final String ERROR_TOKEN_PARSE_PATTERN = "Ошибка! Некорректно введенное значение: %s";
    private static final String MESSAGE_TRY_AGAIN = "Попробуйте снова: ";

    // Сканер считывает то, что вводит пользователь с консоли.
    private Scanner scan;

    public App() {
        scan = new Scanner(System.in);
    }

    //
    public void run() {
        System.out.println("**********\tКалькулятор целых чисел\t**********");
        System.out.println(StateManager.getState());    // С помощью StateManager получаем начальное состояние.

        String input;
        boolean continueInput = true;    // переменная отвечает за окончание работы программы.

        while (continueInput) {
            System.out.print("Введите оператор (+ или *): ");
            input = scan.next();    // Считывание того, что ввел пользователь.

            // Проверка правильности ввода оператора. Если вводит некорректен, то выводим сообщение, и с помощью оператора continue вернемся к началу цикла while.
            if (!checkOperator(input)) {
                System.out.println("Неправильный оператор! Выберете + или *! ");
                continue;
            }

            try {
                State stat;
                if ("*".equals(input)) {
                    System.out.print("Введите множитель альфа:\nальфа: ");
                    int alpha = readInt();
                    stat = StateManager.getState().multiply(alpha); // В переменную stat мы записываем результат умножения текущего состояния на альфа
                }
                else {
                    System.out.print("Введите вектор (a, b):\na: ");
                    int a = readInt();
                    System.out.print("b: ");
                    int b = readInt();
                    stat = StateManager.getState().add(a, b);   // К текущему состоянию, полученному с помощью StateManager, добавляем вектор (a, b).
                }
                System.out.print("Текущее состояние: ");
                System.out.println(stat);
                StateManager.setState(stat);    // Устанавливаем новое текущее состояние.
            }

            // Эта ошибка может появиться во время работы технологии JAXP. Тогда мы с помощью оператора return выйдем из метода run.
            catch (DataBindingException e) {
                System.out.println("Ошибка! Сбой в файле состояния XML.");
                return;
            }

            // Арифметическая ошибка, например, ошибка переполнения, но вряд ли она будет. Если она случится, то мы перейдем в начало цикла while.
            catch (ArithmeticException e) {
                System.out.println(String.format("Арифметическая ошибка: %s", e.getMessage()));
                continue;
            }

            // Спрашиваем, хочет ли пользователь продолжать работу с программой.
            continueInput = continueRead();
        }
    }

    // Метод проверяет, является ли строка + или *.
    private boolean checkOperator(String str){
        return "*".equals(str) || "+".equals(str);
    }

    // Метод для считывания строки из консоли.
    private int readInt() {
        while (true) {
            String token = scan.next(); // С помощью сканера считываем строку из консоли.
            try {
                return Integer.parseInt(token); // Парсим интовое число. Если что-то пошло не так, то в блоке catch выведем сообщение о неправильном токене.
            }
            catch (NumberFormatException e) {
                System.out.println(String.format(ERROR_TOKEN_PARSE_PATTERN, token));
                System.out.print(MESSAGE_TRY_AGAIN);
            }
        }
    }

    // Ожидаем решения пользователя о продолжении работы. Регистр букв не учитывается. Аналогично выводятся сообщения в случае неправильного ввода.
    private boolean continueRead() {
        System.out.print("Продолжить работу? (y/n): ");
        while (true) {
            String token = scan.next();
            if ("y".equalsIgnoreCase(token)) {
                return true;
            }
            if ("n".equalsIgnoreCase(token)) {
                return false;
            }
            System.out.println(String.format(ERROR_TOKEN_PARSE_PATTERN, token));
            System.out.print(MESSAGE_TRY_AGAIN);
        }
    }
}
