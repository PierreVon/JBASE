package zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class watcher {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = zkCli.getClient();
        zk.getChildren("/test", true, new callback(), null);
        Thread.sleep(5000);
        System.out.println("creating node");
        zk.create("/test/t", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("created");
        Thread.sleep(5000);
    }

    static class callback implements AsyncCallback.Children2Callback {

        @Override
        public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
            System.out.println("*********************");
            System.out.println(i);
            System.out.println(s);
            System.out.println(o);
            list.forEach((l) -> System.out.printf("%s, ", l));
            System.out.println();
            System.out.println(stat);
            System.out.println("*********************");
        }
    }
}
