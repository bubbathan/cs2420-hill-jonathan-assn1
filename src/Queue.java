import java.util.ArrayList;

public class Queue<E> {

    ArrayList<E> wordLadderQueue = new ArrayList<E>();

    public void enqueue(E value) {
        wordLadderQueue.add(value);
    }

    public E dequeue() {
        E firstValue = wordLadderQueue.get(0);
        wordLadderQueue.remove(wordLadderQueue.get(0));
        return firstValue;
    }

    public boolean isEmpty() {
        return wordLadderQueue.size() == 0;
    }
}