import java.math.BigInteger;
import javax.xml.bind.annotation.XmlRootElement;

// Класс State хранит состояние. Аннотация @XmlRootElement означает, что класс может приводиться к XML-виду.
@XmlRootElement
public class State {
    // 2 целочисленные координаты по заданию.
    private int x;
    private int y;

    public State(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public State() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Метод для приведения состояния к текстовому виду и печати, чему равны x и y. Аннотация @Override позволяет переопределить метод.
    @Override
    public String toString() {
        return String.format("{x = %d, y = %d}", x, y);
    }

    // Методы умножения и сложения. Методы возвращают State. Класс BigInteger для избежания переполнения. Оперируем значениями BigInteger и в конце извлекаем просто int.
    State multiply(int a) {
        BigInteger xValue = BigInteger.valueOf(x);
        BigInteger yValue = BigInteger.valueOf(y);
        BigInteger aValue = BigInteger.valueOf(a);

        x = xValue.multiply(aValue).intValueExact();
        y = yValue.multiply(aValue).intValueExact();
        return this;
    }

    State add(int a, int b) {
        BigInteger xValue = BigInteger.valueOf(x);
        BigInteger yValue = BigInteger.valueOf(y);
        BigInteger aValue = BigInteger.valueOf(a);
        BigInteger bValue = BigInteger.valueOf(b);

        x = xValue.add(aValue).intValueExact();
        y = yValue.add(bValue).intValueExact();
        return this;
    }
}
