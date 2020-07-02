package rpc.trpc;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class ZkRpcClient {
    public static String SERVER_IP;
    public static int SERVER_PORT;
    public static final int TIMEOUT = 30000;

    /**
     * @param userName
     */
    public void startClient(String userName) {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            MyHelloService.Client client = new MyHelloService.Client(protocol);
            transport.open();
            String result = client.sayHello(userName);
            System.out.println("Thrify client result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    private void getIpAndPort() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper("localhost:2181", 5000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != zk) {
            try {
                if (null != zk.exists("/server/hello", false)) {
                    List<String> addrs = zk.getChildren("/server/hello", false);
                    String addr = new String(zk.getData("/server/hello/" + addrs.get(0), null, new Stat()));
                    String[] ip_port = addr.split(":");
                    SERVER_IP = ip_port[0];
                    SERVER_PORT = Integer.parseInt(ip_port[1]);
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    zk.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ZkRpcClient client = new ZkRpcClient();
        client.getIpAndPort();
        client.startClient("china");

    }
}
