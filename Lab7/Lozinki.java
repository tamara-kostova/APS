import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class CBHT<K extends Comparable<K>, E> {
	// An object of class CBHT is a closed-bucket hash table, containing
	// entries of class MapEntry.
	private SLLNode<MapEntry<K,E>>[] buckets;
	@SuppressWarnings("unchecked")
	public CBHT(int m) {
		// Construct an empty CBHT with m buckets.
		buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
	}
	private int hash(K key) {
		// Translate key to an index of the array buckets.
		return Math.abs(key.hashCode()) % buckets.length;
	}
	public boolean search(K targetKey, E targetValue) {
		int hashCode = hash(targetKey);
		for (SLLNode<MapEntry<K, E>> current = buckets[hashCode]; current != null; current = current.succ)
			if (current.element.key.equals(targetKey) && current.element.value.equals(targetValue))
				return true;
		return false;
	}
	public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
		MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
		int b = hash(key);
		for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
			if (key.equals(((MapEntry<K, E>) curr.element).key)) {
				// Make newEntry replace the existing entry ...
				curr.element = newEntry;
				return;
			}
		}
		// Insert newEntry at the front of the 1WLL in bucket b ...
		buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
	}

	public void delete(K key) {
		// Delete the entry (if any) whose key is equal to key from this CBHT.
		int b = hash(key);
		for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
			if (key.equals(((MapEntry<K,E>) curr.element).key)) {
				if (pred == null)
					buckets[b] = curr.succ;
				else
					pred.succ = curr.succ;
				return;
			}
		}
	}
	public String toString() {
		String temp = "";
		for (int i = 0; i < buckets.length; i++) {
			temp += i + ":";
			for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
				temp += curr.element.toString() + " ";
			}
			temp += "\n";
		}
		return temp;
	}
}
class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {
	// Each MapEntry object is a pair consisting of a key (a Comparable
	// object) and a value (an arbitrary object).
	K key;
	E value;
	public MapEntry (K key, E val) {
		this.key = key;
		this.value = val;
	}
	public int compareTo (K that) {
		// Compare this map entry to that map entry.
		@SuppressWarnings("unchecked")
		MapEntry<K,E> other = (MapEntry<K,E>) that;
		return this.key.compareTo(other.key);
	}
	public String toString () {
		return "<" + key + "," + value + ">";
	}
}
class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}
	@Override
	public String toString() {
		return element.toString();
	}
}
public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        CBHT<String,String> tbl = new CBHT<String, String>(2*n);
        for(int i=0;i<n;i++){
            String lozinka = bf.readLine();
            String[] pom = lozinka.split("\\s+");
            tbl.insert(pom[1], pom[0]);
        }
        while (true){
            String input = bf.readLine();
            if (input.equals("KRAJ"))
                break;
            String [] pom = input.split("\\s+");
			if (tbl.search(pom[1], pom[0])) {
				System.out.println("Najaven");
				return;
			}
			System.out.println("Nenajaven");
        }
    }
}
