import de.arkadi.hello.serviceinterface.ServiceInterface;

open module de.arkadi.hello.caller {
    requires de.arkadi.hello.bad;
    requires de.arkadi.hello.good;
    requires de.arkadi.hello.serviceinterface;
    requires de.arkadi.hello.starter;
    uses ServiceInterface;
}
