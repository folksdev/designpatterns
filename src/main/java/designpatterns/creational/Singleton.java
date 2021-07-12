package designpatterns.creational;

/*
Singleton is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.
*/

public class Singleton {
    private static Singleton INSTANCE;
    private String info = "Initial value";

    private Singleton() { }

    public static Singleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

class SingletonMain {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton1.getInfo());

        Singleton singleton2 = Singleton.getInstance();
        singleton2.setInfo("Changed value");

        System.out.println(singleton2.getInfo());
        System.out.println(singleton1.getInfo());
    }
}
