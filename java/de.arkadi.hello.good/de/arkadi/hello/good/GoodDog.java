package de.arkadi.hello.good;

//import com.google.gson.Gson;

public class GoodDog {

    public GoodDog() {
        this.getClass().getModule().getLayer().modules().stream().forEach(System.out::println);
    }

    public String getDog() {
        return "good dog";
    }
}
