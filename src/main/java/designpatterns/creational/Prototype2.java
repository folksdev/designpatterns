package designpatterns.creational;

interface Copyable<T>{
    T copy();
}

public class Prototype2 implements Copyable<Prototype2>{
    private String field1;
    private String field2;

    public Prototype2(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Override
    public Prototype2 copy() {
        return new Prototype2(field1, field2);
    }
}

class Prototype2Main {
    public static void main(String[] args)  {
        Prototype2 prototype1 = new Prototype2("Original value 1", "Original value 2");
        System.out.println(String.format("Original instance field1: %s - field2: %s", prototype1.getField1(), prototype1.getField2()));

        Prototype2 prototype2 = prototype1.copy();
        prototype2.setField1("Changed value 1");
        prototype2.setField2("Changed value 2");

        System.out.println(String.format("Changed instance field1: %s - field2: %s", prototype2.getField1(), prototype2.getField2()));
        System.out.println(String.format("Original instance field1: %s - field2: %s", prototype1.getField1(), prototype1.getField2()));
    }
}
