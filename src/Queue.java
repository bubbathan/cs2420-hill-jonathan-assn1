public class Queue<E> {

    private ListNode<E> head = new ListNode<>();
    private ListNode<E> tail;
    private int size;

    public Queue() {
        this.size = 0; tail = head;
    }

    public void enqueue(E value) {
        ListNode<E> node = new ListNode<>(value);
        ListNode<E> current = head.next;
        ListNode<E> previous = head;

        while (current != null) {
            previous = current;
            current = current.next;
        }

        previous.next = node;
        node.next = null;
        tail = node;
        size++;
    }

    public void clearQueue() {
        head.next = null;
        tail = head;
    }

    public E dequeue() {
        ListNode<E> node = head.next;
        if (head.next == null) {
            tail = head;
        } else {
            head.next = node.next;
        }
        size--;
        return node.value;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getSize() {
        return this.size;
    }

    private class ListNode<E> {
        public E value;
        public ListNode<E> next;

        public ListNode() { }
        public ListNode(E o) {
            this.value = o;
        }
    }
}