import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        int h = (29*(29*(29*0 + ((String) key).charAt(0)) + ((String) key).charAt(1)) + ((String) key).charAt(2)) % 102780;
        return  h % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
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
        // Insert newEntry at the front of the SLL in bucket b ...
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

class Lek implements Comparable<Lek>{
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public int getCena() {
        return cena;
    }
    public void setCena(int cena) {
        this.cena = cena;
    }
    public int getKolicina() {
        return kolicina;
    }
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    public int getPozLista() {
        return pozLista;
    }

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime.toUpperCase();
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }
    @Override
    public boolean equals(Object obj) {
        Lek pom = (Lek) obj;
        return this.ime.equals(pom.ime);
    }
    @Override
    public String toString() {
        if(pozLista==1)
            return ime+"\n"+"POZ\n"+cena+"\n"+kolicina;
        else
            return ime+"\n"+"NEG\n"+cena+"\n"+kolicina;
    }
    public int compareTo(Lek arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
}

public class Apteka {

    public static void main(String[] args) throws Exception, IOException {
        CBHT<String,Lek> tabela;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tabela = new CBHT<String,Lek>(50);
        for(int i=0;i<n;i++) {
            String line = br.readLine();
            String[] parts = line.split(" ");
            Lek lek = new Lek(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            tabela.insert(parts[0].toUpperCase(), lek);
        }
        String naracka = (br.readLine()).toUpperCase();
        while(naracka.compareTo("KRAJ")!=0){
            int kol = Integer.parseInt(br.readLine());
            SLLNode<MapEntry<String,Lek>> r = tabela.search(naracka);
            if(r==null){
                System.out.println("Nema takov lek");
                naracka = (br.readLine()).toUpperCase();
            }
            else if(r.element.value.getIme().equals(naracka)){
                System.out.println(r.element.value.toString());
                int old = r.element.value.getKolicina();
                if(old < kol)
                    System.out.println("Nema dovolno lekovi");
                else {
                    r.element.value.setKolicina(old - kol);
                    tabela.insert(naracka, r.element.value);
                    System.out.println("Napravena naracka");
                }
                naracka = (br.readLine()).toUpperCase();
            }
            else{
                naracka = br.readLine();
            }
        }

    }
}
