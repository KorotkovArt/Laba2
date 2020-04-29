// Класс просто запускает приложение и выводит ошибки при работе.
public class Main {
    public static void main(String[] args) {
        try {
            new App().run();
        }
        catch (Exception exc) {
            System.err.println();
            System.err.println("Произошла ошибка! ");
            System.err.println();
            System.err.println(exc.getMessage());
            System.err.println();
        }
    }
}
