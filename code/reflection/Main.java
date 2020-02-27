package code.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // printClassInfo("".getClass());
        // printClassInfo(Runnable.class);
        // printClassInfo(java.time.Month.class);
        // printClassInfo(String[].class);
        // printClassInfo(int.class);

        Student st = new Student("Jame", 11, 1);

        Class<? extends Student> stdClass = st.getClass();
        try {
            System.out.println(stdClass.getSuperclass());
            Field scoreField = stdClass.getField("score");
            System.out.println(scoreField.getName());
            System.out.println(scoreField.getType());
            System.out.println(scoreField.get(st));
            System.out.println();

            Field nameField = stdClass.getField("name");
            System.out.println(nameField.getName());
            System.out.println(nameField.getType());
            System.out.println(nameField.get(st));
            System.out.println();

            Field gradeField = stdClass.getDeclaredField("grade");
            gradeField.setAccessible(true);
            System.out.println(gradeField.getName());
            System.out.println(gradeField.getType());
            System.out.println(gradeField.get(st));
            System.out.println();

            Method getScoreMethod = stdClass.getMethod("getScore");
            System.out.println(getScoreMethod.getName());
            System.out.println(getScoreMethod.getReturnType());
            System.out.println(getScoreMethod.getParameterTypes());
            int score = (int)getScoreMethod.invoke(st);
            System.out.println("score = " + score);
            System.out.println();

            Method getGradeMethod = stdClass.getDeclaredMethod("getGrade");
            System.out.println(getGradeMethod.getName());
            System.out.println(getGradeMethod.getReturnType());
            System.out.println(getGradeMethod.getParameterTypes());
            getGradeMethod.setAccessible(true);
            int grade = (int)getGradeMethod.invoke(st);
            System.out.println("grade = " + grade);
            System.out.println();

            Method h = Person.class.getMethod("hello");
            h.invoke(st);
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void printClassInfo(Class<?> cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }
}

class Student extends Person {
    public int score;
    private int grade;
    Student(String name, int score, int grade) {
        super(name);
        this.score = score;
        this.grade = grade;
    }
    public int getScore() {
        return score;
    }
    private int getGrade() {
        return grade;
    }
    public void hello() {
        System.out.println("Student:hello");
    }
}

class Person {
    public String name;
    Person(String name) {
        this.name = name;
    }
    public void hello() {
        System.out.println("Person::hello");
    }
}
