package code.inherit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inherit test");

        Person p = new Student();
        p.run();
    }
}

class Person {
    public void run() {
        System.out.println("Person.run");
    }
}


class Student extends Person {
    @Override
    public void run() {
        System.out.println("Student.run");
    }
}

