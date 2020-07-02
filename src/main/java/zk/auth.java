package zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/*
    mode: digest[name:pass], host[www.x.com], ip[x.x.x.x], world[all]
    acl: create, read, write, delete, admin[setAcl]
 */

public class auth {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {
        ZooKeeper zk = zkCli.getClient();
        System.out.println(zk.getACL("/test/1", null));

        List<ACL> acls = new ArrayList<ACL>();
        Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
        acls.add(acl1);
        Id id2 = new Id("world", "anyone");
        ACL acl2 = new ACL(ZooDefs.Perms.READ, id2);
        acls.add(acl2);
        String node = zk.create("/test/acl", null, acls, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(zk.getACL(node, null));
    }
}

