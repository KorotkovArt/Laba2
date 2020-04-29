import java.io.File;
import javax.xml.bind.JAXB;

// Сохранение в XML и чтение оттуда.
public class StateManager {
    private static final String STATE_FILENAME = "state.xml";   // Название файла.
    private static final File file = new File(STATE_FILENAME);  // Ссылка на файл.

    // Метод получает на вход состояние и с помощью технологии JAXP записывает состояние в файл.
    public static State getState() {
        return JAXB.unmarshal(file, State.class);
    }

    // Аналогично метод устанавливает состояние и записывает его в файл.
    public static void setState(State state) {
        JAXB.marshal(state, file);
    }
}
