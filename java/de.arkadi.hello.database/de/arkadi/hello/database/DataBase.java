package de.arkadi.hello.database;

import java.util.Random;

public class DataBase {

    public void printLoader() {
        System.out.println("DataBaseCL: " + this.getClass().getClassLoader());
    }

    public void printLayer() {
        System.out.println("DataBaseLA: " + this.getClass().getModule().getLayer());
    }

    String city[] = {"Hamburg", "Paris", "Moscow"};

    public String getCity() {
        Random random = new Random();
        int i = random.nextInt(3 - 1 + 1) + 1;
        return city[i - 1];
    }
}
