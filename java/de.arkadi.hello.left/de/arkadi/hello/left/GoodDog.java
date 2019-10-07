package de.arkadi.hello.left;

//import com.google.gson.Gson;

public class GoodDog {

    public GoodDog() {
        this.getClass().getModule().getLayer().modules().stream().forEach(System.out::println);
    }

    public String getDog() {
        return "good dog";
    }
}
