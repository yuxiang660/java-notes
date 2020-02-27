package code.exception;

public class Main {
    public static void main(String[] args) {
        try {
            Integer.parseInt("abc");
        } catch (Exception e) {
            System.out.println("catch");
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
        }
    }
}