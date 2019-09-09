package de.arkadi.hello.provider;

import de.arkadi.hello.serviceinterface.ServiceInterface;


public class Provider implements ServiceInterface {


    @Override
    public String getName() {
        return "iProvide a service";
    }
}
