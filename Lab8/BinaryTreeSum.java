import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;
    @SuppressWarnings("unchecked")
    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }
    @SuppressWarnings("unchecked")
    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BTree<E extends Comparable<E>> {

    public BNode<E> root;
    @SuppressWarnings("unchecked")
    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode(elem);
    }
    @SuppressWarnings("unchecked")
    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }
    @SuppressWarnings("unchecked")
    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }
    public BNode<E> getRoot() {
        return root;
    }
    public BNode<E> search(BNode<E> node, E nodeValue) {
        BNode<E> result = null;
        if (node == null) return null;
        if (node.info.compareTo(nodeValue) == 0) return node;
        if (node.left != null) result = search(node.left, nodeValue);
        if (result == null) result = search(node.right, nodeValue);
        return result;
    }
    @SuppressWarnings("unchecked")
    public static int sumright (BNode<Integer> node, BNode <Integer> root){
        if (node==null) return 0;
        if (node.info.compareTo(root.info)>0)
            return node.info+sumright(node.right,root)+sumright(node.left,root);
        return sumright(node.right,root)+sumright(node.left,root);
    }
    @SuppressWarnings("unchecked")
    public static int sumleft (BNode<Integer> node, BNode <Integer> root){
        if (node==null) return 0;
        if (node.info.compareTo(root.info)<0)
            return node.info+sumleft(node.right,root)+sumleft(node.left,root);
        return sumleft(node.right,root)+sumleft(node.left,root);
    }
}

public class BinaryTreeSum {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i=0;i<N;i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        int baranaVrednost=Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod ovde

        BNode<Integer> root = tree.search(tree.getRoot(), baranaVrednost);
        System.out.println(BTree.sumleft(root.left, root)+" "+BTree.sumright(root.right, root));

    }
}
