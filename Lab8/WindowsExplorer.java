import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.firstChild == null || tmp.compareTo(curr.firstChild) < 0) {
            tmp.sibling = curr.firstChild;
            curr.firstChild = tmp;
            tmp.parent = curr;
            return tmp;
        }
        SLLNode<E> node1 = (SLLNode<E>) curr.firstChild;
        SLLNode<E> node2 = (SLLNode<E>) node1.sibling;
        while (node2 != null) {
            if (tmp.compareTo(node2) < 0) {
                node1.sibling = tmp;
                tmp.sibling = node2;
                tmp.parent = curr;
                return tmp;
            }
            node1 = node1.sibling;
            node2 = node2.sibling;
        }
        node1.sibling = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i=0;i<level;i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>)node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level+1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }
    public Node<E> getNode(Node<E> node, E elem) {
        SLLNode<E> tmp = (SLLNode<E>) node;
        tmp = tmp.firstChild;
        while (tmp != null) {
            if (tmp.element.equals(elem)) {
                return tmp;
            }
            tmp = tmp.sibling;
        }
        throw new NoSuchElementException();
    }
}

class SLLNode<P> implements Node<P>, Comparable<SLLNode<P>> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
    @Override
    public int compareTo(SLLNode<P> arg0) {
        String s1 = (String) element;
        String s2 = (String) arg0.element;
        return s1.compareTo(s2);
    }
}

public class WindowsExplorer {
        public static void pateka(SLLNode<String> x) {
        if (x == null) return;
        pateka(x.parent);
        System.out.print(x.element + "\\");
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (i=0;i<N;i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");

        // vasiot kod stoi ovde
        SLLNode<String> curr = tree.root;
        for (i=0; i<commands.length; i++){
            String parts [] = commands[i].split("\\s");
            if (parts[0].equals("CREATE")){
                tree.addChild(curr, parts[1]);
            }
            else if (parts[0].equals("OPEN")){
                curr = (SLLNode<String>) tree.getNode(curr, parts[1]);
            }
            else if (parts[0].equals("DELETE")){
                SLLNode<String> izb = curr.firstChild;
                while (izb != null) {
                    if (izb.element.equals(parts[1])) {
                        tree.remove(izb);
                    }
                    izb = izb.sibling;
                }
            }
            else if (parts[0].equals("BACK")){
                curr=curr.parent;
            }
            else if (parts[0].equals("PATH")){
                pateka(curr);
                System.out.println();
            }
            else if (parts[0].equals("PRINT")){
                tree.printTree();
            }
        }
    }
}
