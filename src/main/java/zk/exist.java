package zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class exist {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = zkCli.getClient();
        System.out.println(zk.exists("/test", false));
        System.out.println(zk.exists("/no-exist", false));
    }
}
