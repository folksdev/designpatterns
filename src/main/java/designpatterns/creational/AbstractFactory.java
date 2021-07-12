package designpatterns.creational;

import java.util.ArrayList;
import java.util.List;

interface Ingredient{
    String name();
}

interface Cheese extends Ingredient{ }

class GoatCheese implements Cheese{
    @Override
    public String name() {
        return "Goat Cheese";
    }
}

class FetaCheese implements Cheese{
    @Override
    public String name() {
        return "Feta Cheese";
    }
}

interface Sauce extends Ingredient{ }

class MexicanSauce implements Sauce{
    @Override
    public String name() {
        return "Mexican Sauce";
    }
}

class TurkishSauce implements Sauce{
    @Override
    public String name() {
        return "Turkish Sauce";
    }
}

abstract class ToppingFactory {
    abstract Cheese getCheese();
    abstract Sauce getSauce();
    List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(getCheese());
        ingredients.add(getSauce());
        return ingredients;
    }
}

class StandardToppingFactory extends ToppingFactory{
    @Override
    Cheese getCheese() {
        return null;
    }

    @Override
    Sauce getSauce() {
        return null;
    }

    @Override
    List<Ingredient> getIngredients() {
        return null;
    }
}

class MexicanToppingFactory extends ToppingFactory{
    @Override
    Cheese getCheese() {
        return new GoatCheese();
    }

    @Override
    Sauce getSauce() {
        return new MexicanSauce();
    }
}

class TurkishToppingFactory extends ToppingFactory{
    @Override
    Cheese getCheese() {
        return new FetaCheese();
    }

    @Override
    Sauce getSauce() {
        return new TurkishSauce();
    }
}

abstract class Pizza{
    List<Ingredient> extraIngredients;

    public Pizza(List<Ingredient> extraIngredients) {
        this.extraIngredients = extraIngredients;
    }

    public Pizza() {
        this.extraIngredients = null;
    }

    abstract void bake();
}

class CheesePizza extends Pizza{

    public CheesePizza(List<Ingredient> extraIngredients) {
        super(extraIngredients);
    }

    public CheesePizza() {
    }

    @Override
    public void bake() {
        if(extraIngredients != null){
            extraIngredients.stream().map(Ingredient::name).forEach(System.out::println);
        }

        System.out.println("I am a cheese pizza");
    }
}

class PepperoniPizza extends Pizza{

    public PepperoniPizza(List<Ingredient> extraIngredients) {
        super(extraIngredients);
    }

    public PepperoniPizza() {
    }

    @Override
    public void bake() {
        if(extraIngredients != null){
            extraIngredients.stream().map(Ingredient::name).forEach(System.out::println);
        }

        System.out.println("I am a pepperoni pizza");
    }
}

class VegetarianPizza extends Pizza{

    public VegetarianPizza(List<Ingredient> extraIngredients) {
        super(extraIngredients);
    }

    public VegetarianPizza() {
    }

    @Override
    public void bake() {
        if(extraIngredients != null){
            extraIngredients.stream().map(Ingredient::name).forEach(System.out::println);
        }
        System.out.println("I am a vegetarian pizza");

    }
}


class PizzaFactory{
    static Pizza getPizza(String type, ToppingFactory toppingFactory) {
        List<Ingredient> ingredients = toppingFactory.getIngredients();
        Pizza pizza;
        switch (type.toLowerCase())
        {
            case "cheese":
                pizza = new CheesePizza(ingredients);
                break;
            case "pepperoni":
                pizza = new PepperoniPizza(ingredients);
                break;
            case "veggie":
                pizza = new VegetarianPizza(ingredients);
                break;
            default: throw new IllegalArgumentException("No such pizza.");
        }
        pizza.bake();
        return pizza;
    }
}

class ToppingFactoryFactory{
    static ToppingFactory getToppingFactory(String type) {

            switch (type.toLowerCase())
            {
                case "standard":
                    return new StandardToppingFactory();
                case "mexican":
                   return new MexicanToppingFactory();
                case "turkish":
                    return new TurkishToppingFactory();
                default: throw new IllegalArgumentException("No such pizza.");
            }

    }
}


public class AbstractFactory {
    public static void main(String[] args) {
        ToppingFactory standardToppingFactory = ToppingFactoryFactory.getToppingFactory("standard");
        ToppingFactory mexicanToppingFactory = ToppingFactoryFactory.getToppingFactory("mexican");
        ToppingFactory turkishToppingFactory = ToppingFactoryFactory.getToppingFactory("turkish");

        System.out.println("=======");
        Pizza p1 = PizzaFactory.getPizza("veggie", standardToppingFactory);
        System.out.println("=======");
        Pizza p2 = PizzaFactory.getPizza("cheese", mexicanToppingFactory);
        System.out.println("=======");
        Pizza p3 = PizzaFactory.getPizza("pepperoni", turkishToppingFactory);
        System.out.println("=======");

    }
}
