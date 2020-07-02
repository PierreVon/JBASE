package struct;

public class MyTree {

    static class TreeNode {
        int val;
        TreeNode r;
        TreeNode l;

        TreeNode(int val, TreeNode l, TreeNode r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }

    TreeNode root;

    public enum Direct {
        Left,
        Right
    }

    public void add(TreeNode node, Direct d, TreeNode newNode) {
        if (root == null) root = newNode;
        if (node == null) return;
        if (d == Direct.Left) node.l = newNode;
        else node.r = newNode;
    }

    public void preTravel(TreeNode node) {
        if (node != null) {
            System.out.printf("%d ", node.val);
            preTravel(node.l);
            preTravel(node.r);
        }
    }

    public void midTravel(TreeNode node) {
        if (node != null) {
            midTravel(node.l);
            System.out.printf("%d ", node.val);
            midTravel(node.r);
        }
    }

    public void backTravel(TreeNode node) {
        if (node != null) {
            backTravel(node.l);
            backTravel(node.r);
            System.out.printf("%d ", node.val);
        }
    }

    public static void main(String[] args) {
        MyTree tree = new MyTree();
        tree.add(tree.root, Direct.Left, new TreeNode(4, null, null));
        tree.add(tree.root, Direct.Left, new TreeNode(6, null, null));
        tree.add(tree.root, Direct.Right, new TreeNode(8, null, null));
        tree.add(tree.root.l, Direct.Left, new TreeNode(2, null, null));
        tree.add(tree.root.l.l, Direct.Right, new TreeNode(3, null, null));
        tree.add(tree.root.l.l.r, Direct.Left, new TreeNode(9, null, null));
        tree.add(tree.root.r, Direct.Left, new TreeNode(12, null, null));
        tree.add(tree.root.r, Direct.Right, new TreeNode(18, null, null));
        tree.add(tree.root.r.l, Direct.Left, new TreeNode(14, null, null));
        tree.add(tree.root.r.l.l, Direct.Right, new TreeNode(15, null, null));

        tree.preTravel(tree.root);
        System.out.println();
        tree.midTravel(tree.root);
        System.out.println();
        tree.backTravel(tree.root);
    }
}
