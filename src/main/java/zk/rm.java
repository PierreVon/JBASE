package zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class rm {
    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2182,localhost:2183,localhost:2184", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                zkCli.printEvent(watchedEvent);
            }
        });
        zkCli.rmr(zk, "/a");
    }
}
