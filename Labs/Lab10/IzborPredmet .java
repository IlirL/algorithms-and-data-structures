
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
import java.util.LinkedList;


class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    boolean containsNeighbor(GraphNode<E> o){
        return neighbors.contains(o);
    }

    void addNeighbor(GraphNode<E> o){
        neighbors.add(o);
    }


    void removeNeighbor(GraphNode<E> o){
        if(neighbors.contains(o))
            neighbors.remove(o);
    }


    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+=neighbors.get(i).info+" ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }



}


public class IzborPredmet  {
    public static int getIndex(String s, String list[])
    {
        for(int i = 0; i<list.length; i++)
        {
            if(s.equals(list[i]))
                return i;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
//    Graph<String> g = new Graph<String>(5);
        String list[] = new String[n];
        for (int i = 0; i < n; i++) {
            list[i] = br.readLine();
        }
        Graph<String> g = new Graph<String>(n, list);
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split("\\s+");
            for (int j = 1; j < parts.length; j++) {
                g.addEdge(getIndex(parts[0],list), getIndex(parts[j], list));
            }
        }

        g.topological_sort_dfs(getIndex(br.readLine(), list));
    }
}

class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    /************************TOPOLOGICAL SORT*******************************************************************/

    void dfsVisit(Stack<Integer> s, int i, boolean[] visited){
        if(!visited[i]){
            visited[i] = true;
            Iterator<GraphNode<E>> it = adjList[i].getNeighbors().iterator();
//			System.out.println("dfsVisit: "+i+" Stack="+s);
            while(it.hasNext()){
                dfsVisit(s, it.next().getIndex(), visited);
            }
            s.push(i);
//			System.out.println("--dfsVisit: "+i+" Stack="+s);
        }
    }

    void topological_sort_dfs(int node){
        boolean visited[] = new boolean[num_nodes];
        for(int i=0;i<num_nodes;i++){
            visited[i] = false;
        }

        Stack<Integer> s = new Stack<Integer>();

        for(int i=0;i<num_nodes;i++){
            dfsVisit(s,i,visited);
        }
//		System.out.println("----Stack="+s);
        boolean found = false;
        int current = 0;
        int previous = 0;
        while(!s.isEmpty()){
//			System.out.println(adjList[s.pop()].getInfo());
            previous = current;
            current = s.pop();
            if(current == node)
            {
                found = true;
                System.out.println(adjList[previous].getInfo());
                break;
            }
        }
    }

    /***********************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }

}

