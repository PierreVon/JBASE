package zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class benchmark {
    static String addr = "localhost:2182,localhost:2183,localhost:2184";
    static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(addr, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                zkCli.printEvent(watchedEvent);
            }
        });
        for (int i = 0; i < 20; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; ; j++) {
                        try {
                            zk.create("/a/" + j, null, ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
