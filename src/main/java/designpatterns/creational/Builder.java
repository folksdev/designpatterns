package designpatterns.creational;

class Employee{
    private final String firstName;     //required
    private final String lastName;      //required
    private final int age;              //required
    private final int personalId;       // required
    private final String phone;         //optional
    private final String address;       //optional
    private final String mail;          //optional

    public Employee(String firstName, String lastName, int age, int personalId) {
        this(firstName, lastName, age, personalId, "", "");
    }

    public Employee(String firstName, String lastName, int age, int personalId, String phone) {
        this(firstName, lastName, age, personalId, phone, "");
    }

    public Employee(String firstName, String lastName, int age, int personalId, String phone, String address) {
        this(firstName, lastName, age, personalId, phone, address, "");
    }

    public Employee(String firstName, String lastName, int age, int personalId, String phone, String address, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.personalId = personalId;
        this.phone = phone;
        this.address = address;
        this.mail = mail;
    }

    // Getters and setters ...
}

class EmployeeWithBuilder {
    private final String firstName;    //required
    private final String lastName;    //required
    private final int age;    //required
    private final int personalId; // required
    private final String phone;    //optional
    private final String address;    //optional
    private final String mail;    //optional

    static class EmployeeBuilder {
        private final String firstName;    //required
        private final String lastName;    //required
        private final int age;    //required
        private final int personalId; // required
        private String phone;    //optional
        private String address;    //optional
        private String mail;    //optional

        public EmployeeBuilder(String firstName, String lastName, int age, int personalId) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.personalId = personalId;
        }

        public EmployeeBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public EmployeeWithBuilder build() {
            return new EmployeeWithBuilder(this);
        }
    }

    private EmployeeWithBuilder(EmployeeBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.personalId = builder.personalId;
        this.phone = builder.phone;
        this.address = builder.address;
        this.mail = builder.mail;
    }

    @Override
    public String toString() {
        return "EmployeeWithBuilder{\n" +
                "firstName='" + firstName + '\'' +
                ",\nlastName='" + lastName + '\'' +
                ",\nage=" + age +
                ",\npersonalId=" + personalId +
                ",\nphone='" + phone + '\'' +
                ",\naddress='" + address + '\'' +
                ",\nmail='" + mail + '\'' + "\n" +
                '}';
    }
}

public class Builder {
    public static void main(String[] args) {
        EmployeeWithBuilder employeeWithBuilder = new EmployeeWithBuilder
                .EmployeeBuilder("Muratcan", "Celayir", 30, 123)
                .setAddress("Hollanda")
                .setMail("muratcancelayir@gmail.com")
                .setPhone("2132131")
                .build();

        System.out.println(employeeWithBuilder.toString());
    }
}
