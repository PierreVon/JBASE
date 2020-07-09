package basic;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Struct {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();

        List<Integer> v = new Vector<>();
        List<Integer> s = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map4 = new LinkedHashMap<>();
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map2 = new ConcurrentHashMap<>();
        Map<Integer, Integer> map3 = new Hashtable<>();

        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool2 = Executors.newFixedThreadPool(2);

        Queue<Integer> q = new ArrayBlockingQueue<>(2);
        Queue<Integer> q2 = new LinkedBlockingQueue<>();
        Queue<Integer> q3 = new PriorityQueue<>();
        DelayQueue q4 = new DelayQueue();
        Queue<Integer> q5 = new SynchronousQueue<>();

        Lock lock = new ReentrantLock();
        Condition c = lock.newCondition();

        CountDownLatch latch = new CountDownLatch(3);
        CyclicBarrier cb = new CyclicBarrier(5);
        Semaphore sema = new Semaphore(5);

    }
}
