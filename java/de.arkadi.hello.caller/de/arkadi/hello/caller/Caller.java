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
        System.out.println("======Modules of Good Layer========");
        GoodDog good = new GoodDog();
        System.out.println("=======" + good.getDog() + " loaded=======\n");

        System.out.println("======Modules of Bad Layer========");
        BadDog bad = new BadDog();
        System.out.println("=======" + bad.getDog() + " loaded=======\n");

        System.out.println("======Modules of this Layer========");
        this.getClass().getModule().getLayer().modules().stream().forEach(System.out::println);

        System.out.println("\n======Provider for service found========");
        ServiceLoader<ServiceInterface> plugins = ServiceLoader.load(l, ServiceInterface.class);
        System.out.println(plugins.findFirst().get().getName());
        System.out.println("======Provider module name========");
        System.out.println(plugins.findFirst().get().getClass().getModule().getName());

        System.out.println("\n======Entry Point========");
        System.out.println(ModuleLayer.boot().findModule("de.arkadi.hello.starter").get().getDescriptor().mainClass().get());
        System.out.println("=======Location of entry point module=======");
        System.out.println(ModuleLayer.boot().configuration().findModule("de.arkadi.hello.starter")
                .get().reference().location());
    }
}

