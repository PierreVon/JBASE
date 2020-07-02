package rpc.trpc;

import org.apache.thrift.TException;

public class HelloService implements MyHelloService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "hello: ".concat(username);
    }
}
