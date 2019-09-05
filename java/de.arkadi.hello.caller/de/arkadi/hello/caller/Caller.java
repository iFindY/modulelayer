package de.arkadi.hello.caller;
import com.google.gson.Gson;
import de.arkadi.hello.bad.BadDog;
import de.arkadi.hello.good.GoodDog;

public class Caller {

    public static void main(String[] args) {
        GoodDog good = new GoodDog();
        System.out.println(good.getDog());

        BadDog bad = new BadDog();
        System.out.println(bad.getDog());
    }
}

