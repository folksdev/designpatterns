package designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class Command {

    interface Order {
        void apply();
    }

    class BuyOrder implements Order {
        String stock;
        int amount;

        public BuyOrder(String stock, int amount) {
            this.stock = stock;
            this.amount = amount;
        }

        @Override
        public void apply() {
            System.out.println("Buying " + amount + " " + stock + " stock");
        }
    }

    class SellOrder implements Order {
        String stock;
        int amount;

        public SellOrder(String stock, int amount) {
            this.stock = stock;
            this.amount = amount;
        }

        @Override
        public void apply() {
            System.out.println("Selling " + amount + " " + stock + " stock");
        }
    }

    class Broker {
        private List<Order> orderList = new ArrayList<>();

        public void takeOrder(Order order) {
            orderList.add(order);
        }

        public void placeOrders() {
            for (Order order : orderList) {
                order.apply();
            }
            orderList.clear();
        }

    }

    void commandDemo(){
        Order buy1 = new BuyOrder("ABC", 10);
        Order buy2 = new BuyOrder("ABC", 20);
        Order sell1 = new SellOrder("ABC", 10);
        Order sell2 = new SellOrder("ABC", 10);

        Broker broker = new Broker();
        broker.takeOrder(buy1);
        broker.takeOrder(buy2);
        broker.takeOrder(sell1);
        broker.takeOrder(sell2);

        broker.placeOrders();
    }

    public static void main(String[] args) {
        Command command = new Command();
        command.commandDemo();
    }
}
