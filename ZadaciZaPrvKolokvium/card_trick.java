import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}
class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }
    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }
    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }
    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }
    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }
    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}
interface Queue<E> {
    boolean isEmpty();

    int size();

    E peek();

    void clear();

    void enqueue(E x);

    E dequeue();
}
class ArrayQueue<E> implements Queue<E> {
    private E[] elements;
    private int front, rear, length;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxSize) {
        elements = (E[]) new Object[maxSize];
        clear();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E peek() {
        if (length != 0) {
            return elements[front];
        } else throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        length = front = rear = 0;
    }

    @Override
    public void enqueue(E x) {
        if (length != elements.length) {
            if (rear == elements.length)
                rear = 0;
            elements[rear++] = x;
            ++length;
        }
    }

    @Override
    public E dequeue() {
        if (length != 0) {
            E element = elements[front++];
            if (front == elements.length)
                front = 0;
            --length;
            return element;
        } else throw new NoSuchElementException();
    }
}
public class card_trick {
    public static int count(int N){
        Queue<Integer> karti = new ArrayQueue<>(51);
        Stack <Integer> shuffle = new ArrayStack<>(7);
        for (int i=1; i<=51; i++)
            karti.enqueue(i);
        int shuffles=0;
        while (karti.peek()!=N){
            for (int i=0; i<7; i++)
                shuffle.push(karti.dequeue());
            while (!(shuffle.isEmpty())){
                karti.enqueue(shuffle.pop());
                karti.enqueue(karti.dequeue());
            }
            shuffles++;
        }
        return shuffles;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
