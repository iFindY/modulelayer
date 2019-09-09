package de.arkadi.hello.caller;

import de.arkadi.hello.bad.BadDog;
import de.arkadi.hello.good.GoodDog;
import de.arkadi.hello.serviceinterface.ServiceInterface;
import de.arkadi.hello.starter.Main;

import java.util.List;
import java.util.ServiceLoader;


public class Caller {

    public static void main(String[] args) {
        Caller caller = new Caller();
        List<ModuleLayer> layers = Main.getLayers();
        caller.printIt(layers.get(2));

    }

    public void printIt(ModuleLayer l) {
        System.out.println("==============");
        GoodDog good = new GoodDog();
        System.out.println("=======" + good.getDog() + " ready=======");
        BadDog bad = new BadDog();
        System.out.println("=======" + bad.getDog() + " ready=======");
        this.getClass().getModule().getLayer().modules().stream().forEach(System.out::println);
        System.out.println("==============");
        ServiceLoader<ServiceInterface> plugins = ServiceLoader.load(l, ServiceInterface.class);
        System.out.println(plugins.findFirst().get().getName());

    }
}

