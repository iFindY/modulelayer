import de.arkadi.hello.serviceinterface.ServiceInterface;
// module
open module de.arkadi.hello.ui {
    requires de.arkadi.hello.right;
    requires de.arkadi.hello.left;
    requires de.arkadi.hello.serviceinterface;
    requires de.arkadi.hello.starter;
    uses ServiceInterface;
}
