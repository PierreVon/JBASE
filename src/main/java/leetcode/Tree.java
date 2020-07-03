package leetcode;

import java.util.*;

public class Tree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 236. 二叉树的最近公共祖先
    // 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    // 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
    // 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度
    // 尽可能大（一个节点也可以是它自己的祖先）。”
    // 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (p == null || q == null) return null;
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }
        Map<TreeNode, TreeNode> one = new HashMap<>();
        while (q != null) {
            one.put(q, parent.get(q));
            q = parent.getOrDefault(q, null);
        }
        while (p != null) {
            if (one.containsKey(p)) return p;
            p = parent.getOrDefault(p, null);
        }
        return null;
    }

    // 树序列化和反序列化
    public static String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode tmp;
        while (!q.isEmpty()) {
            tmp = q.poll();
            if (tmp != null) {
                sb.append(tmp.val);
                sb.append(",");
                q.add(tmp.left);
                q.add(tmp.right);
            } else {
                sb.append("null,");
            }
        }
        int last = sb.length() - 1;
        sb.replace(last, last + 1, "]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        TreeNode head = null;
        String real = data.substring(1, data.length() - 1);
        String[] reals = real.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        for (String s : reals) {
            if (!s.equals("null")) {
                q.add(new TreeNode(Integer.parseInt(s)));
            } else {
                q.add(null);
            }
        }
        Queue<TreeNode> next = new LinkedList<>();
        TreeNode tmp;
        while (!q.isEmpty()) {
            if (head == null) {
                head = q.poll();
                next.add(head);
            } else {
                tmp = next.poll();
                tmp.left = q.poll();
                tmp.right = q.poll();
                if (tmp.left != null) next.add(tmp.left);
                if (tmp.right != null) next.add(tmp.right);
            }
        }
        return head;
    }

    // 124. 二叉树中的最大路径和
    // 给定一个非空二叉树，返回其最大路径和。
    // 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
    public static int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        return maxSub(root)[1];
    }

    public static int[] maxSub(TreeNode node) {
        if (node.left == null && node.right == null) return new int[]{node.val, node.val};
        else {
            int maxPath, maxChild;
            int[] left, right;
            if (node.left == null) {
                right = maxSub(node.right);
                maxChild = Math.max(right[0] + node.val, node.val);
                maxPath = Math.max(right[1], maxChild);
            } else if (node.right == null) {
                left = maxSub(node.left);
                maxChild = Math.max(left[0] + node.val, node.val);
                maxPath = Math.max(left[1], maxChild);
            } else {
                left = maxSub(node.left);
                right = maxSub(node.right);
                maxChild = Math.max(Math.max(left[0], right[0]) + node.val, node.val);
                maxPath = Math.max(left[1], Math.max(right[1], Math.max(maxChild, left[0] + right[0] + node.val)));
            }
            System.out.printf("%d, %d\n", maxChild, maxPath);
            return new int[]{maxChild, maxPath};
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int mid = nums.length / 2;
        TreeNode head = new TreeNode(nums[mid]);
        createChild(0, mid, nums.length - 1, nums, head);
        return head;
    }

    private static void createChild(int left, int mid, int right, int[] nums, TreeNode father) {
        if (left < mid) {
            int midl = (left + mid) / 2;
            father.left = new TreeNode(nums[midl]);
            createChild(left, midl, mid - 1, nums, father.left);
        }
        if (mid < right) {
            int midr = (int) Math.ceil((double) ((right + mid)) / 2);
            father.right = new TreeNode(nums[midr]);
            createChild(mid + 1, midr, right, nums, father.right);
        }
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(-1);
        System.out.println(serialize(null));
        System.out.println(serialize(head));
        String s = serialize(head);
        System.out.println(serialize(deserialize(s)));
        System.out.println(serialize(deserialize("[null]")));


        System.out.println(maxPathSum(deserialize("[1,-2,-3,1,3,-2,null,-1]")));

        System.out.println(serialize(sortedArrayToBST(new int[]{0, 1, 2})));

    }

}
