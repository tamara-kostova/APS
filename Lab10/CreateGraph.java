import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Stack;
class Graph<E> {

    int num_nodes; // broj na jazli
    E nodes[];    // informacija vo jazlite - moze i ne mora?
    int adjMat[][];  // matrica na sosednost

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        nodes = (E[]) new Object[num_nodes];
        adjMat = new int[num_nodes][num_nodes];

        for (int i = 0; i < this.num_nodes; i++)
            for (int j = 0; j < this.num_nodes; j++)
                adjMat[i][j] = 0;
    }


    public Graph(int num_nodes, E[] nodes) {
        this.num_nodes = num_nodes;
        this.nodes = nodes;
        adjMat = new int[num_nodes][num_nodes];

        for (int i = 0; i < this.num_nodes; i++)
            for (int j = 0; j < this.num_nodes; j++)
                adjMat[i][j] = 0;
    }


    int adjacent(int x, int y) {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
        return (adjMat[x][y] != 0) ? 1 : 0;
    }

    void addEdge(int x, int y) {  // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y] = 1;
        adjMat[y][x] = 1;
    }

    void deleteEdge(int x, int y) {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y] = 0;
        adjMat[y][x] = 0;
    }

    // Moze i ne mora?
    E get_node_value(int x) {  // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }

    // Moze i ne mora?
    void set_node_value(int x, E a) {  // ja postavuva informacijata vo jazelot so indeks na a
        nodes[x] = a;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);

        for (int i = 0; i < this.num_nodes; i++) {
            if (adjacent(node, i) == 1) {
                if (!visited[i])
                    dfsRecursive(i, visited);
            }
        }
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        int pom;

        while (!s.isEmpty()) {
            pom = s.peek();
            int pom1 = pom;
            for (int i = 0; i < num_nodes; i++) {
                if (adjacent(pom, i) == 1) {
                    pom1 = i;
                    if (!visited[i])
                        break;
                }
            }
            if (!visited[pom1]) {
                visited[pom1] = true;
                System.out.println(pom1 + ": " + nodes[pom1]);
                s.push(pom1);
            } else
                s.pop();
        }
    }


    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < num_nodes; i++)
            ret += nodes[i] + " ";
        ret += "\n";
        for (int i = 0; i < num_nodes; i++) {
            ret += nodes[i] + " ";
            for (int j = 0; j < num_nodes; j++)
                ret += adjMat[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }
}
public class CreateGraph {
    public static void main (String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Graph<Character>graph=null;
        for (int i=0; i<n; i++){
            String [] line = br.readLine().split("\\s+");
            if (line[0].equals("CREATE")){
                Character [] bukvi = new Character[Integer.parseInt(line[1])];
                for (int j=0; j<bukvi.length; j++)
                    bukvi[j]=(char)('A'+j);
                graph = new Graph<>(Integer.parseInt(line[1]),bukvi);
            }
            if (line[0].equals("ADDEDGE")){
                graph.addEdge(Integer.parseInt(line[1]),Integer.parseInt(line[2]));
            }
            if (line[0].equals("DELETEEDGE")){
                graph.deleteEdge(Integer.parseInt(line[1]),Integer.parseInt(line[2]));
            }
            if (line[0].equals("PRINTMATRIX")) {
                for (int j = 0; j < graph.adjMat.length; j++){
                    for (int k = 0; k < graph.adjMat.length; k++)
                        System.out.print(graph.adjMat[j][k] + " ");
                System.out.println();
                }
            }
            if (line[0].equals("PRINTNODE")){
                System.out.println(graph.get_node_value(Integer.parseInt(line[1])));
            }
            if (line[0].equals("ADJACENT")){
                System.out.println(graph.adjacent(Integer.parseInt(line[1]),Integer.parseInt(line[2])));
            }
        }
    }
}
