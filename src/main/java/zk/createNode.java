package zk;

import org.apache.zookeeper.*;

import java.io.IOException;

public class createNode {
    public static void main(String[] args) throws IOException {
        ZooKeeper zk = zkCli.getClient();
        try {
            System.out.println(zk.create("/test/ja", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL));
            System.out.println(zk.create("/test/em", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL));
            System.out.println(zk.create("/server/a", "null".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
