import java.awt.*;

public class Main {

    private static String name;
    private static String surname;
    private static final FirstTest firstTest = new FirstTest();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new StartFrame().setVisible(true));
        
    }

    public static void setVisibleFirstTest() {
        firstTest.setVisible(true);
    }

    public static void setName(String name) {
        Main.name = name;
    }

    public static String getName() {
        return name;
    }

    public static void setSurname(String surname) {
        Main.surname = surname;
    }

    public String getSurname() {
        return surname;
    }
}


