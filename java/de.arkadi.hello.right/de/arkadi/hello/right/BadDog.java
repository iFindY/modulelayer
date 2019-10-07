package de.arkadi.hello.right;

//import com.fasterxml.jackson.core.JsonFactory;

public class BadDog {

    public BadDog() {
        this.getClass().getModule().getLayer().modules().stream().forEach(System.out::println);

    }

    public String getDog() {
        return "bad dog";
    }
}
