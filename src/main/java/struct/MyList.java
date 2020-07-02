package struct;

public class MyList {

    Node head;


    class Node {
        int val;
        Node next;

        Node() {
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public void add(int val) {
        if (head == null) head = new Node(val, null);
        else head = new Node(val, head);
    }

    public int remove(int val) {
        Node tmp = head;
        Node pre = head;
        while (tmp != null) {
            if (tmp.val == val) {
                if (pre == tmp) {
                    head = tmp.next;
                    tmp.next = tmp;
                    return val;
                }
                pre.next = tmp.next;
                tmp.next = tmp;
                return val;
            } else {
                tmp = tmp.next;
                if (pre.next != tmp) {
                    pre = pre.next;
                }
            }
        }
        return -1;
    }

    public int contains(int val) {
        Node tmp = head;
        while (tmp != null) {
            if (tmp.val == val) {
                return val;
            }
            tmp = tmp.next;
        }
        return -1;
    }

    public void travel() {
        Node tmp = head;
        while (tmp != null) {
            System.out.printf("%d ", tmp.val);
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyList list = new MyList();
        list.add(5);
        list.add(2);
        list.add(3);
        list.add(4);
        list.travel();
        list.remove(5);
        list.travel();
        list.remove(4);
        list.travel();
    }
}
