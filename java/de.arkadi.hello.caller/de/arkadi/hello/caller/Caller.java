package de.arkadi.hello.caller;

import de.arkadi.hello.bad.BadDog;
import de.arkadi.hello.good.GoodDog;


public class Caller {

    public static void main(String[] args) {
        Caller caller = new Caller();
        caller.printIt();


    }

    public void printIt() {
        System.out.println("==============");
        GoodDog good = new GoodDog();
        System.out.println(good.getDog());
        System.out.println("==============");
        BadDog bad = new BadDog();
        System.out.println(bad.getDog());
        System.out.println("==============");
        this.getClass().getModule().getLayer().modules().stream().forEach(System.out::println);


    }
}

