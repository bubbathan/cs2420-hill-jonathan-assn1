import java.util.ArrayList;

public class Queue<E extends Comparable> {

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
    }

    public E dequeue() {
        E firstValue = wordLadderQueue.get(0);
        wordLadderQueue.remove(wordLadderQueue.get(0));
        return firstValue;
    }

    public boolean isEmpty() {
        return this.size == 0;
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