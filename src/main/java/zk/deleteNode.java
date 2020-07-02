package zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class deleteNode {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = zkCli.getClient();
        System.out.println(zk.create("/test/tmp", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
        Thread.sleep(1000);
        zk.delete("/test/tmp", -1);
    }
}
