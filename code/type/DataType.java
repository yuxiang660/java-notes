package code.type;

public class DataType {
    public static void main(String[] args) {
        System.out.println("Size of types");
        System.out.println("Integer:" + Integer.SIZE/8); // Integer:4
        System.out.println("Short:" + Short.SIZE/8); // Short:2
        System.out.println("Long:" + Long.SIZE/8); // Long:8
        System.out.println("Byte:" + Byte.SIZE/8); // Byte:1
        System.out.println("Character:" + Character.SIZE/8); // Character:2
        System.out.println("Float:" + Float.SIZE/8); // Float:4
        System.out.println("Double:" + Double.SIZE/8); // Double:8
    }
}