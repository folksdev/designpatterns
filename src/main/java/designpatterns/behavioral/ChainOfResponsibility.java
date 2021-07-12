package designpatterns.behavioral;

public class ChainOfResponsibility {

    abstract class Approver {
        Approver chief;

        public Approver(Approver chief) {
            this.chief = chief;
        }

        abstract boolean approveLoan(int amount);
    }

    class Officer extends Approver {

        public Officer(Approver chief) {
            super(chief);
        }

        @Override
        boolean approveLoan(int amount) {
            if (amount < 100) {
                System.out.println("Officer approves");
                return true;
            } else if (chief != null) {
                System.out.println("Amount is greater than officer can approve. Passing to chief");
                return chief.approveLoan(amount);
            }
            return false;
        }
    }

    class Manager extends Approver {

        public Manager(Approver chief) {
            super(chief);
        }

        @Override
        boolean approveLoan(int amount) {
            if (amount < 500) {
                System.out.println("Manager approves");
                return true;
            } else if (chief != null) {
                System.out.println("Amount is greater than manager can approve. Passing to chief");
                return chief.approveLoan(amount);
            }
            return false;
        }
    }

    class Executive extends Approver {

        public Executive() {
            super(null);
        }

        @Override
        boolean approveLoan(int amount) {
            System.out.println("Executive approves");
            return true;
        }
    }

    void chainOfResponsibilityDemo(){
        Approver executive = new Executive();
        Approver manager = new Manager(executive);
        Approver officer = new Officer(manager);

        officer.approveLoan(50);
        officer.approveLoan(450);
        officer.approveLoan(1050);
    }


    public static void main(String[] args) {
        ChainOfResponsibility cor = new ChainOfResponsibility();
        cor.chainOfResponsibilityDemo();
    }
}
