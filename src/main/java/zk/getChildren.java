package zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class getChildren {
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        ZooKeeper zk = zkCli.getClient();
        if (zk != null) {
            for (String p : zk.getChildren("/test", false)) {
                System.out.println(p);
            }
        }
    }
}
