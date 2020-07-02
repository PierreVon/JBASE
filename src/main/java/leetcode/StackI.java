package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class StackI {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0) return l2;
        if (l2.val == 0) return l1;
        Stack<ListNode> q1 = new Stack<>();
        Stack<ListNode> q2 = new Stack<>();
        while (l1 != null) {
            q1.add(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            q2.add(l2);
            l2 = l2.next;
        }
        ListNode head = new ListNode(-1);
        int x = 0;
        int now = 0;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            now = q1.pop().val + q2.pop().val + x;
            x = now / 10;
            now %= 10;
            ListNode tmp = new ListNode(now);
            tmp.next = head.next;
            head.next = tmp;
        }
        Stack <ListNode> q3 = q1.isEmpty() ? q2 : q1;
        while (!q3.isEmpty()) {
            now = q3.pop().val + x;
            x = now / 10;
            now %= 10;
            ListNode tmp = new ListNode(now);
            tmp.next = head.next;
            head.next = tmp;
        }
        if (x != 0) {
            ListNode tmp = new ListNode(x);
            tmp.next = head.next;
            head.next = tmp;
        }
        return head.next;
    }

    // 739. 每日温度
    // 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超
    // 过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
    //
    // 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出
    // 应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
    public int[] dailyTemperatures_Stack(int[] T) {
        if (T == null || T.length == 0) return new int[]{};
        int[] res = new int[T.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < T.length; i++) {
            if (stack.isEmpty()) stack.push(i);
            else {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int tmp = stack.poll();
                    res[tmp] = i - tmp;
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            res[stack.poll()] = 0;
        }
        return res;
    }
}
