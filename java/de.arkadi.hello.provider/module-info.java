import de.arkadi.hello.provider.Provider;
import de.arkadi.hello.serviceinterface.ServiceInterface;

module de.arkadi.hello.provider {
    requires de.arkadi.hello.left;
    requires de.arkadi.hello.serviceinterface;
    provides ServiceInterface with Provider;
}
