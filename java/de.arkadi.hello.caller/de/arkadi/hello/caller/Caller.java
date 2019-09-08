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
        System.out.println("=======" + good.getDog() + " ready=======");
        BadDog bad = new BadDog();
        System.out.println("=======" + bad.getDog() + " ready=======");
        this.getClass().getModule().getLayer().modules().stream().forEach(System.out::println);


    }
}

