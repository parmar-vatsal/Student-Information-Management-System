public class Authentication {
    private static boolean isTeacher = false;

    public static void setTeacher(boolean isTeacher) {
        Authentication.isTeacher = isTeacher;
    }

    public static boolean isTeacher() {
        return isTeacher;
    }
}
