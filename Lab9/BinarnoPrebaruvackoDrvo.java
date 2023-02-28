import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class BNode<E extends Comparable<E>> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }
    @SuppressWarnings("unchecked")
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}
class BinarySearchTree<E extends Comparable<E>> {
    public BNode<E> root;
    @SuppressWarnings("unchecked")
private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
        t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
        t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
        t.right = insert(x, t.right);
        } else; // Duplicate; do nothing
        return t;
        }
    @SuppressWarnings("unchecked")
public void insert(E x) {
        root = insert(x, root);
        }
    @SuppressWarnings("unchecked")
private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
        return null;
        } else if (t.left == null) {
        return t;
        }
        return findMin(t.left);
        }
public E findMin() {
        return elementAt(findMin(root));
        }
    @SuppressWarnings("unchecked")
private BNode<E> findMax(BNode<E> t) {
        if (t == null) {
        return null;
        } else if (t.right == null) {
        return t;
        }
        return findMax(t.right);
        }
public E findMax() {
        return elementAt(findMax(root));
        }
    @SuppressWarnings("unchecked")
private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
        return null;
        if (x.compareTo(t.info) < 0) {
        return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
        return find(x, t.right);
        } else {
        return t; // Match
        }
        }
    @SuppressWarnings("unchecked")
    private E elementAt(BNode<E> t) {
        if (t == null)
            return null;
        return t.info;
    }
    @SuppressWarnings("unchecked")
public BNode<E> find(E x) {
        return find(x, root);
        }
    @SuppressWarnings("unchecked")
private BNode<E> remove(Comparable x, BNode<E> t) {
        if (t == null)
        return t; // Item not found; do nothing
        if (x.compareTo(t.info) < 0) {
        t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
        t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
        t.info = findMin(t.right).info;
        t.right = remove(t.info, t.right);
        } else {
        if (t.left != null)
        return t.left;
        else
        return t.right;
        }
        return t;
        }
public void remove(E x) {
        root = remove(x, root);
        }
}
public class BinarnoPrebaruvackoDrvo {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BinarySearchTree<Integer> drvo = new BinarySearchTree<Integer>();
        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++)
            drvo.insert(Integer.parseInt(br.readLine()));
        BNode<Integer> node =drvo.find(Integer.parseInt(br.readLine()));
        int d = visina(node);
        System.out.println(d);
        System.out.println(elementinadlabocina(drvo.root, d, 0));
    }
    @SuppressWarnings("unchecked")
    private static int visina (BNode<Integer>node){
        if (node==null)
            return 0;
        return Math.max(visina(node.left),visina(node.right))+1;
    }
    @SuppressWarnings("unchecked")
    private static int elementinadlabocina (BNode<Integer> root, int d, int curr){
        if (root==null)
            return 0;
        if (curr==d)
            return 1;
        return elementinadlabocina(root.left, d, curr+1)+elementinadlabocina(root.right,d,curr+1);
    }
}
