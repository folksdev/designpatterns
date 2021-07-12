package designpatterns.structural;

import java.util.Arrays;
import java.util.List;

public class Decorator {

    interface Priceable{
        Double getPrice();
    }

    abstract class Coffee implements Priceable{
        abstract String getName();
    }

    class Espresso extends Coffee{
        @Override
        String getName() {
            return "Espresso";
        }

        @Override
        public Double getPrice() {
            return 5.0;
        }
    }

    abstract class ExtraCoffee extends Coffee {
        Coffee base;
        List<Priceable> extras;

        @Override
        public Double getPrice() {
            return base.getPrice() + extras.stream().map(Priceable::getPrice).reduce(0.0, Double::sum);
        }
    }

    abstract class PremiumCoffee extends ExtraCoffee {
        List<Priceable> sides;

        @Override
        public Double getPrice() {
            return super.getPrice() + sides.stream().map(Priceable::getPrice).reduce(0.0, Double::sum);
        }
    }

    class Latte extends ExtraCoffee {

        public Latte() {
            this.base = new Espresso();
            this.extras = Arrays.asList(() -> 1.25, () -> 0.75);
        }

        @Override
        String getName() {
            return "Latte";
        }
    }

    class ColombianCoffee extends PremiumCoffee {
        public ColombianCoffee() {
            this.base = new Latte();
            this.extras = Arrays.asList(() -> 1.25, () -> 0.75);
            this.sides = Arrays.asList(() -> 2.25, () -> 0.25, () -> 1.50);
        }

        @Override
        String getName() {
            return "Colombian";
        }
    }

    public void decoratorDemo(){

        Coffee latte = new Latte();
        Coffee colombian = new ColombianCoffee();

        System.out.println(String.format("Order is ready\n- %s: %.2f\n- %s: %.2f\nTotal: %.2f", latte.getName(), latte.getPrice(), colombian.getName(), colombian.getPrice(), latte.getPrice() + colombian.getPrice()));

    }

    public static void main(String[] args) {
        Decorator decorator = new Decorator();
        decorator.decoratorDemo();
    }
}
