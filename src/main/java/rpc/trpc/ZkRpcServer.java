package rpc.trpc;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZkRpcServer {
    public static final int SERVER_PORT = 7800;

    public void startServer() {
        try {
            System.out.println("HelloWorld TSimpleServer start ....");

            //在这里调用了 HelloWorldImpl 规定了接受的方法和返回的参数
            TProcessor tprocessor = new MyHelloService.Processor<MyHelloService.Iface>(new HelloService());

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());

            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }

    public void register() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper("localhost:2181", 5000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (zk != null) {
            try {
                zkCreate(zk, new String[]{"/server", "/server/hello"});
                zk.create("/server/hello/1", "localhost:7800".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void zkCreate(ZooKeeper zk, String[] paths) throws KeeperException, InterruptedException {
        for (String path : paths) {
            Stat stat = zk.exists(path, false);
            if (null == stat) {
                zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }
    }

    public static void main(String[] args) {
        ZkRpcServer server = new ZkRpcServer();
        server.register();
        server.startServer();
    }
}
