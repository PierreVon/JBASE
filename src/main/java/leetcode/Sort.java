package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sort {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 23. 合并K个排序链表
    // 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
    // *这里仅使用归并，还可以通过优先队列，先将每个链表头结点加入堆中，每次去顶部，并将该节点下一个节点加入堆中
    public static ListNode mergeKLists(ListNode[] lists) {
        return mergeDivide(lists, 0, lists.length - 1);
    }

    private static ListNode mergeDivide(ListNode[] list, int l, int r) {
        if (l == r) return list[l];
        if (l > r) return null;
        int mid = (l + r) >> 1;
        return merge(mergeDivide(list, l, mid), mergeDivide(list, mid + 1, r));
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode tmp = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                tmp.next = l2;
                l2 = l2.next;
            } else {
                tmp.next = l1;
                l1 = l1.next;
            }
            tmp = tmp.next;
        }
        if (l1 != null) tmp.next = l1;
        if (l2 != null) tmp.next = l2;
        return head.next;
    }

    // 56. 合并区间
    // 给出一个区间的集合，请合并所有重叠的区间。
    // 输入: [[1,3],[2,6],[8,10],[15,18]]
    // 输出: [[1,6],[8,10],[15,18]]
    // 链接：https://leetcode-cn.com/problems/merge-intervals/
    public static int[][] merge(int[][] intervals) {
        if(intervals==null||intervals.length==0) return new int[][]{};
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int start=intervals[0][0], end=intervals[0][1];
        ArrayList<Integer[]> list= new ArrayList<>();
        for(int i=1;i<intervals.length;i++){
            if(end>=intervals[i][0]) end=Math.max(end, intervals[i][1]);
            else{
                list.add(new Integer[]{start, end});
                start=intervals[i][0];
                end=intervals[i][1];
            }
        }
        list.add(new Integer[]{start, end});
        int[][] res = new int[list.size()][2];
        for(int i=0;i<list.size();i++)
            res[i]=new int[]{list.get(i)[0], list.get(i)[1]};
        return res;
    }

    public static void QuickSort(int[] nums, int low, int high) {
        if (low >= high) return;
        int pivot = findPivot(nums, low, high);
        QuickSort(nums, low, pivot - 1);
        QuickSort(nums, pivot + 1, high);
    }

    public static int findPivot(int[] nums, int low, int high) {
        int i = low, j = high;
        int node = nums[low];
        int tmp;
        while (i < j) {
            while (i < j && nums[j] > node) j--;
            while (i < j && nums[i] <= node) i++;
            if (i < j) {
                tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
        }
        nums[low] = nums[i];
        nums[i] = node;
        return i;
    }

    public static void MergeSort(int[] nums, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        MergeSort(nums, low, mid);
        MergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        if (low >= high) return;
        int[] tmp = new int[high - low + 1];
        int i = low, j = mid + 1;
        int x = 0;
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) tmp[x++] = nums[i++];
            else tmp[x++] = nums[j++];
        }
        while (i <= mid) tmp[x++] = nums[i++];
        while (j <= high) tmp[x++] = nums[j++];
        for (i = low; i <= high; i++) nums[i] = tmp[i - low];
    }


}
