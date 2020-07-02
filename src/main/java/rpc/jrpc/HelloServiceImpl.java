package rpc.jrpc;

public class HelloServiceImpl implements IHello {

    @Override
    public String sayHello(String name) {
        return "hello: ".concat(name);
    }
}
