package algo;

import java.util.Queue;

public class Sort {

    // 插入排序，将一个值插入已排好序的数组中
    public static void InsertSort(int[] nums) {
        int sentry;
        for (int i = 1; i < nums.length; i++) {
            sentry = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > sentry) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = sentry;
        }
    }

    // 冒泡排序，每次将剩下数组中的最大值排到对应位置
    public static void BubbleSort(int[] nums) {
        int tmp;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }
    }

    // 快速排序， 每次找到首节点的位置，用双指针确定节点在数组中的位置，之后对左右数组进行同样操作
    public static void QuickSort(int[] nums, int low, int high) {
        if (low > high) return;
        int pivot = findPivot(nums, low, high);
        QuickSort(nums, low, pivot - 1);
        QuickSort(nums, pivot + 1, high);
    }

    private static int findPivot(int[] nums, int low, int high) {
        if (low > high) return low;
        int sentry = nums[low], tmp;
        int i = low, j = high;
        while (i < j) {
            while (i < j && nums[j] > sentry) j--;
            while (i < j && nums[i] <= sentry) i++;
            if (i < j) {
                tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
        }
        nums[low] = nums[i];
        nums[i] = sentry;
        return i;
    }

    // 归并排序，将数组分为两个数组，持续的分割。最后将有序的两个数组合并
    public static void MergeSort(int[] nums, int low, int high) {
        if (low >= high) return;
        int mid = (high + low) / 2;
        MergeSort(nums, low, mid);
        MergeSort(nums, mid + 1, high);
        Merge(nums, low, mid, high);
    }

    private static void Merge(int[] nums, int low, int mid, int high) {
        if (low >= high) return;
        int[] tmp = new int[high - low + 1];
        int i = low, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) tmp[k++] = nums[i++];
            else tmp[k++] = nums[j++];
        }
        while (i <= mid) tmp[k++] = nums[i++];
        while (j <= high) tmp[k++] = nums[j++];
        for (k = 0; k < tmp.length; k++) {
            nums[low + k] = tmp[k];
        }
    }

    // 堆排序，先构建大根堆，每次将堆顶与末尾替换，重新构造堆
    public static void HeapSort(int[] nums, int low, int high) {
        for (int k = (high + low) / 2; k >= 0; k--) {
            ShiftDown(nums, k, high);
        }
        int tmp;
        for (int i = high; i > low; i--) {
            tmp = nums[i];
            nums[i] = nums[low];
            nums[low] = tmp;
            ShiftDown(nums, low, i - 1);
        }
    }

    private static void ShiftDown(int[] nums, int i, int high) {
        int n, m, tmp;
        n = i;
        while (2 * n + 1 <= high) {
            m = 2 * n + 1;
            if (m + 1 <= high && nums[m + 1] > nums[m]) m++;
            if (nums[m] > nums[n]) {
                tmp = nums[m];
                nums[m] = nums[n];
                nums[n] = tmp;
            }
            n = m;
        }
    }

    public static void main(String[] args) {

        System.out.println("InsertSort=====");
        int[] a = new int[]{2, 1, 4, 3, 1, 7, 5, 8, -1, 0, -5};
        InsertSort(a);
        for (int i : a) System.out.printf("%d, ", i);

        System.out.println("\n\nBubbleSort=====");
        a = new int[]{2, 1, 4, 3, 1, 7, 5, 8, -1, 0, -5};
        BubbleSort(a);
        for (int i : a) System.out.printf("%d, ", i);

        System.out.println("\n\nQuickSort=====");
        a = new int[]{2, 1, 4, 3, 1, 7, 5, 8, -1, 0, -5};
        QuickSort(a, 0, a.length - 1);
        for (int i : a) System.out.printf("%d, ", i);

        System.out.println("\n\nMergeSort=====");
        a = new int[]{2, 1, 4, 3, 1, 7, 5, 8, -1, 0, -5};
        MergeSort(a, 0, a.length - 1);
        for (int i : a) System.out.printf("%d, ", i);

        System.out.println("\n\nHeapSort=====");
        a = new int[]{2, 1, 4, 3, 1, 7, 5, 8, -1, 0, -5};
        HeapSort(a, 0, a.length - 1);
        for (int i : a) System.out.printf("%d, ", i);
    }

}
