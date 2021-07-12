package designpatterns.behavioral;

public class Strategy {

    interface MathOperation{
        int apply(int a, int b);
    }

    class Add implements MathOperation{
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    }

    class Subtract implements MathOperation {
        @Override
        public int apply(int a, int b) {
            return a - b;
        }
    }

    class Context {
        private MathOperation strategy;

        public MathOperation getStrategy() {
            return strategy;
        }

        public void setStrategy(MathOperation strategy) {
            this.strategy = strategy;
        }

        public Context(MathOperation strategy){
            this.strategy = strategy;
        }

        public int executeStrategy(int num1, int num2){
            return strategy.apply(num1, num2);
        }
    }

    void strategyDemo(){
        Context context = new Context(new Add());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new Subtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
    }

    public static void main(String[] args) {
        Strategy strategy = new Strategy();
        strategy.strategyDemo();
    }
}
