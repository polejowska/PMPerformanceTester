public class Main {

    private static final AppStartFrame appStartFrame = new AppStartFrame();

    private static String name;
    private static String surname;
    private static float points1Test, points2Test, points3Test;

    public static void main(String[] args) {
        appStartFrame.setVisible(true);
    }

    public static void setVisibileStartFrame() {
        appStartFrame.setVisible(true);
    }

    /* Updating points */

    public static void setPoints1(float points) {
        Main.points1Test = points;
    }

    public static float getPoints1() {
        return points1Test;
    }
    public static void setPoints2(float points) {
        Main.points2Test += points;
    }

    public static float getPoints2() {
        return points2Test;
    }

    public static void setPoints3(float points) {
        Main.points3Test += points;
    }

    public static float getPoints3() {
        return points3Test;
    }

    /* End of Updating points */

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


