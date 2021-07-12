package designpatterns.creational;

import java.util.UUID;

interface Coupon{
    String code();
    String message();
}

class FoodCoupon implements Coupon{
    @Override
    public String code() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String message() {
        return "I am a food coupon";
    }
}

class ElectronicsCoupon implements Coupon{
    @Override
    public String code() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String message() {
        return "I am an electronics coupon";
    }

}

class CouponFactory{
    public static Coupon getCoupon(int points){
        if(points < 50){
            return new FoodCoupon();
        }
        return new ElectronicsCoupon();
    }
}

public class Factory {
    public static void main(String[] args) {
        Coupon coupon1 = CouponFactory.getCoupon(30);
        System.out.println(String.format("Coupon code: %s, message: %s", coupon1.code(), coupon1.message()));

        Coupon coupon2 = CouponFactory.getCoupon(50);
        System.out.println(String.format("Coupon code: %s, message: %s", coupon2.code(), coupon2.message()));
    }
}
