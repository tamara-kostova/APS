import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Heap<E extends Comparable<E>> {
    private E[] elements;
    private int size;

    // go zgolemuvame za 1, bidejki indeksot pocnuva od 0
    public int getParent(int i) {
        return (i + 1) / 2 - 1;
    }

    public int getLeft(int i) {
        return ((i + 1) * 2) - 1;
    }

    public int getRight(int i) {
        return (i + 1) * 2;
    }
    // go zgolemuvame za 1, bidejki indeksot pocnuva od 0

    public E getAt(int i) {
        return elements[i];
    }

    public void setElement(int index, E insert) {
        elements[index] = insert;
    }

    public void swap(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    public void buildMaxHeap() {
        for (int i = elements.length / 2 - 1; i >= 0; i--)
            adjustMax(i, size);
    }

    private void adjustMax(int i, int n) {
        if (i >= n) return;
        int left = getLeft(i);
        int right = getRight(i);
        int max = i;

        if ((left < n) && elements[left].compareTo(elements[max]) > 0)
            max = left;
        if ((right < n) && elements[right].compareTo(elements[max]) > 0)
            max = right;
        if (max == i)
            return;

        swap(i, max);
        adjustMax(max, n);
    }

    public void buildMinHeap() {
        for (int i = elements.length / 2 - 1; i >= 0; i--)
            adjustMin(i, size);
    }

    private void adjustMin(int i, int n) {
        if (i >= n) return;
        int left = getLeft(i);
        int right = getRight(i);
        int min = i;

        if ((left < n) && elements[left].compareTo(elements[min]) < 0)
            min = left;
        if ((right < n) && elements[right].compareTo(elements[min]) < 0)
            min = right;
        if (min == i)
            return;

        swap(i, min);
        adjustMin(min, n);
    }


    public Heap(E[] arr) {
        this.elements = arr;
        this.size = arr.length;
    }

    public void heapSort() {
        buildMaxHeap();
        for (int i = size; i > 1; i--) {
            swap(0, i - 1);
            adjustMax(0, i - 1);
        }

    }

    // prioritetna redica implmentacija, el so max prioritet e na prva pozicija
    // so sekoj insert ili delete na element se adjust heap-ot, za max prioritet da
    // ostane na prva pozicija

    @SuppressWarnings("unchecked")
    public Heap(int size) {
        this.size = 0;
        this.elements = (E[]) new Comparable[size];
    }

    public boolean insert(E elem) {
        if (size == elements.length) return false;
        elements[size] = elem;
        size++;
        adjustUp(size - 1);
        return true;
    }

    private void adjustUp(int i) {
        if (i <= 0) return;
        int parent = getParent(i);
        if (elements[i].compareTo(elements[parent]) <= 0)
            return;
        else {
            swap(i, parent);
            adjustUp(parent);
        }
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public E getMax() {
        return isEmpty() ? null : elements[0];
    }

    public int getSize() {
        return size;
    }

    public E removeMax() {
        if (isEmpty()) return null;
        E tmp = elements[0];
        elements[0] = elements[size - 1];
        size--;
        adjustMax(0, size);
        return tmp;
    }

    // zavrsuva implementacijata na Prioritetnata redica
}
class Buyer implements Comparable<Buyer>{
    private int entry;
    private int time;
    public Buyer(String entry, int time){
        String [] parts=entry.split(":");
        this.entry=Integer.parseInt(parts[1])+Integer.parseInt(parts[0])*60;
        this.time=time;
        if (time+this.entry>1439)
            this.time-=(this.time+this.entry)-1439;
    }
    @Override
    public int compareTo(Buyer b) {
        return -((entry + time) - (b.entry + b.time));
    }
    public boolean istovreme(Buyer b){
        if (entry+time>=b.entry)
            return true;
        return false;
    }
}
public class Christmas {
    public static void main(String [] args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        Heap <Buyer> priority = new Heap<>(n);
        for (int i=0; i<n; i++){
            String [] parts = br.readLine().split(" ");
            priority.insert(new Buyer(parts[0],Integer.parseInt(parts[1])));
        }
        int c=1, max=0;
        for (int i=0; i<n-1; i++){
            Buyer b = priority.removeMax();
            for (int j=0; j<priority.getSize(); j++)
                if (b.istovreme(priority.getAt(j)))
                    c++;
            max=Math.max(max, c);
            c=1;
        }
        System.out.println(max);
    }
}
