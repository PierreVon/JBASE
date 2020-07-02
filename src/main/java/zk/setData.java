package zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class setData {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = zkCli.getClient();
        String path = "/test/1";
        Stat stat = zk.setData(path, "hello".getBytes(), -1);
        System.out.println(stat);
        Thread.sleep(1000);
        Stat stat2 = zk.setData(path, "hello2".getBytes(), stat.getVersion());
        System.out.println(stat2);
        Thread.sleep(1000);
        // wrong version
        zk.setData(path, "hello3".getBytes(), stat.getVersion());
        Thread.sleep(1000);
        // no permission
        zk.setData("/test/acl", "hello3".getBytes(), -1);
    }
}
