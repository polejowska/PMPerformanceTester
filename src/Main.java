import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    private static final AppStartFrame appStartFrame = new AppStartFrame();

    private static String name;
    private static String surname;
    private static float points;

    public static void main(String[] args) {
        appStartFrame.setVisible(true);
    }

    public static void setVisibileStartFrame() {
        appStartFrame.setVisible(true);
    }

    public static void setPoints(float points) {
        Main.points += points;
    }

    public static float getPoints() {
        return points;
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

    public static String getSurname() {
        return surname;
    }



}


