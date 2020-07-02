package leetcode;

import java.util.*;

public class Pointer {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode tmp = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tmp.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                tmp.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        if (l1 != null) tmp.next = l1;
        if (l2 != null) tmp.next = l2;
        return head.next;
    }

    // 反转一个单链表。
    public ListNode reverseList(ListNode head) {
        ListNode res = null, tmp;
        while (head != null) {
            tmp = head;
            head = head.next;
            tmp.next = res;
            res = tmp;
        }
        return res;
    }

    // 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    // 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    // 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(), tmp = head, n1, n2;
        int fo = 0, sum;
        while (l1 != null && l2 != null) {
            n1 = l1;
            n2 = l2;
            l1 = l1.next;
            l2 = l2.next;
            sum = n1.val + n2.val + fo;
            fo = sum / 10;
            tmp.next = new ListNode(sum % 10);
            tmp = tmp.next;
        }
        if (l2 != null) l1 = l2;
        while (l1 != null) {
            n1 = l1;
            l1 = l1.next;
            sum = n1.val + fo;
            fo = sum / 10;
            tmp.next = new ListNode(sum % 10);
            tmp = tmp.next;
        }
        if (fo > 0) {
            tmp.next = new ListNode(fo);
            tmp = tmp.next;
        }
        return head.next;
    }

    // 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode l1 = head;
        ListNode l2 = head;
        while (l2 != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next.next;
        }
        l2 = l1.next;
        l1.next = null;
        l1 = head;
        return mergeTwoLists(sortList(l1), sortList(l2));
    }

    // 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) return head;
            else {
                set.add(head);
                head = head.next;
            }
        }
        return null;
    }

    // 编写一个程序，找到两个单链表相交的起始节点。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Deque<ListNode> q1 = new LinkedList<>();
        Deque<ListNode> q2 = new LinkedList<>();
        while (headA != null) {
            q1.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            q2.add(headB);
            headB = headB.next;
        }
        ListNode tmp, pre = null;
        while (q1.size() > 0 && q2.size() > 0) {
            tmp = q1.pollLast();
            if (tmp == q2.pollLast()) pre = tmp;
            else return pre;
        }
        return pre;
    }

    public ListNode getIntersectionNodeGGGGGG(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }

    // 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length < 2) return lists[0];
        ListNode base = lists[0];
        for (int i = 1; i < lists.length; i++) {
            base = mergeTwoLists(base, lists[i]);
        }
        return base;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        TreeNode tmp;
        List<List<Integer>> lists = new LinkedList<>();
        boolean seq = true;
        while (!q.isEmpty()) {
            List<Integer> l = new LinkedList<>();
            while ((tmp = q.poll()) != null) {
                if (seq) l.add(tmp.val);
                else l.add(0, tmp.val);
                if (tmp.left != null) q.add(tmp.left);
                if (tmp.right != null) q.add(tmp.right);
            }
            seq = !seq;
            lists.add(l);
            if (q.size() > 0) q.add(null);
        }
        return lists;
    }


    // 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        map.put(root, null);
        TreeNode tmp;
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if (tmp.left != null) {
                map.put(tmp.left, tmp);
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                map.put(tmp.right, tmp);
                queue.add(tmp.right);
            }
        }
        Deque<TreeNode> l2 = new LinkedList<>();
        Deque<TreeNode> l1 = new LinkedList<>();
        while (p != null) {
            l1.add(p);
            p = map.get(p);
        }
        while (q != null) {
            l2.add(q);
            q = map.get(q);
        }
        for (TreeNode node : l1) System.out.println(node.val);
        System.out.println();
        for (TreeNode node : l2) System.out.println(node.val);
        TreeNode pre = null;
        while (l2.size() > 0 && l1.size() > 0) {
            tmp = l1.pollLast();
            if (tmp == l2.pollLast()) pre = tmp;
            else return pre;
        }
        return root;
    }

    public TreeNode lowestCommonAncestorR(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;
        TreeNode l = lowestCommonAncestorR(root.left, p, q);
        TreeNode r = lowestCommonAncestorR(root.right, p, q);
        if (l == null) return r;
        if (r == null) return l;
        return root;
    }

    public static void main(String[] args) {
        Pointer s = new Pointer();
        ListNode head = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode head2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode node = s.addTwoNumbers(head, head2);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

        node = s.sortList(node);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

        s.zigzagLevelOrder(new TreeNode(3));
    }
}
