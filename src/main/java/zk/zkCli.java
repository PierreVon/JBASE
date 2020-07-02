package zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class zkCli {
    private static volatile ZooKeeper client = null;
    public static final String ADDRESS = "localhost:2181";

    public static ZooKeeper getClient() throws IOException {
        if (client == null) {
            synchronized (zkCli.class) {
                if (client == null)
                    client = new ZooKeeper(ADDRESS, 5000, new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            printEvent(watchedEvent);
                        }
                    });
            }
        }
        return client;
    }

    public static <T> void printList(List<T> list) {
        for (T l : list) {
            System.out.println(l);
        }
    }

    public static void rmr(ZooKeeper zk, String path) throws Exception {
        List<String> children = zk.getChildren(path, false);
        for (String pathCd : children) {
            String newPath = "";
            if (path.equals("/")) {
                newPath = "/" + pathCd;
            } else {
                newPath = path + "/" + pathCd;
            }
            rmr(zk, newPath);
        }
        if (path != null && !path.trim().startsWith("/zookeeper") && !path.trim().equals("/")) {
            zk.delete(path, -1);
            System.out.println("被删除的节点为：" + path);
        }
    }

    public static void printEvent(WatchedEvent watchedEvent) {
        System.out.println("================================");
        System.out.println(watchedEvent.getState());
        System.out.println(watchedEvent.getPath());
        System.out.println(watchedEvent.getType());
        System.out.println(watchedEvent.getWrapper());
        System.out.println("================================");
    }
}
