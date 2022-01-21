import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;


class Edge{
    private int fromVertex, toVertex;
    private int weight1;
    private int weight2;
    public Edge(int from, int to, int weight1, int weight2){
        this.fromVertex = from;
        this.toVertex = to;
        this.weight1 = weight1;
        this.weight2 = weight2;
    }

    public int getFrom(){
        return this.fromVertex;
    }
    public int getTo(){
        return this.toVertex;
    }
    public int getWeight1(){
        return this.weight1;
    }

    public int getWeight2(){
        return this.weight2;
    }

    public void setFrom(int from){
        this.fromVertex = from;
    }
    public void setTo(int to){
        this.toVertex = to;
    }
    public void setWeight1(int weight1){
        this.weight1 = weight1;
    }
    public void setWeight2(int weight2){
        this.weight2 = weight2;
    }
}

class GraphNodeNeighbor<E> {
    GraphNode<E> node;
    int weight1;
    int weight2;

    public GraphNodeNeighbor(GraphNode<E> node, int weight1, int weight2) {
        this.node = node;
        this.weight1 = weight1;
        this.weight2 = weight2;
    }

    public GraphNodeNeighbor(GraphNode<E> node) {
        this.node = node;
        this.weight1 = 0;
        this.weight2 = 0;
    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
        return pom.node.equals(this.node);
    }
}

class GraphNode<E> {
    private int index; //index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNodeNeighbor<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNodeNeighbor<E>>();
    }

    public boolean containsNeighbor(GraphNode<E> o){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0,0);
        return neighbors.contains(pom);
    }

    public void addNeighbor(GraphNode<E> o, int weight1, int weight2){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,weight1,weight2);
        neighbors.add(pom);
    }

    public void removeNeighbor(GraphNode<E> o){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0,0);
        if(neighbors.contains(pom))
            neighbors.remove(pom);
    }

    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+="("+neighbors.get(i).node.info+","+neighbors.get(i).weight1+","+neighbors.get(i).weight2+") ";
        return ret;

    }

    public void updateNeighborWeight(GraphNode<E> o, int weight1, int weight2){
        Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
        while(i.hasNext()){
            GraphNodeNeighbor<E> pom = i.next();
            if(pom.node.equals(o))
            {
                pom.weight1 = weight1;
                pom.weight2 = weight2;
            }
        }

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

    public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
        this.neighbors = neighbors;
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

    void addEdge(int x, int y, int tezina1, int tezina2) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        if (adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].updateNeighborWeight(adjList[y], tezina1, tezina2);
        } else
            adjList[x].addNeighbor(adjList[y], tezina1, tezina2);
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    // Funkcija za prebaruvanje na index na jazel so dadeno info vo listata na sosednost vo grafot
    int searchIndex(String key)
    {
        for(int i=0;i<num_nodes;i++){
            Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                if(pom.node.getInfo().equals(key))
                    return pom.node.getIndex();

            }
        }
        return -1;
    }


    List<Edge> pustina(int oaza) {
        // Vasiot kod ovde
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
        return prim(oaza);

    }

    private Edge getMinimalEdge(boolean[] included){
        int index1 = Integer.MAX_VALUE, index2 = Integer.MAX_VALUE;
        int minweight1 = Integer.MAX_VALUE;
        int minweight2 = Integer.MAX_VALUE;

        for(int i=0;i<this.num_nodes;i++){
            if(included[i]){
                //ako e vkluceno temeto i
                //izmini gi negovite nevkluceni sosedi
                Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
                while(it.hasNext()){
                    GraphNodeNeighbor<E> pom = it.next();
                    //ako sosedot ne e poseten i ima do sega najmala tezina
                    if(!included[pom.node.getIndex()] && (pom.weight1<minweight1 ||  (pom.weight2<minweight2 && pom.weight1 == minweight1))){
                        index1 = i;
                        index2 = pom.node.getIndex();
                        minweight1 = pom.weight1;
                        minweight2 = pom.weight2;
                    }
                }
            }
        }

        if(minweight1<Float.MAX_VALUE){
            Edge ret = new Edge(index1, index2, minweight1, minweight2);
            return ret;
        }
        return null;
    }

    List<Edge> prim(int start_index) {
        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        boolean included[] = new boolean[this.num_nodes];
        for(int i=0;i<this.num_nodes;i++)
            included[i]=false;

        included[start_index] = true;

        for(int i=0;i<this.num_nodes-1;i++){
            Edge e = this.getMinimalEdge(included);
            if(e==null){
                System.out.println("Ne mozat da se povrzat site jazli");
                break;
            }
            included[e.getFrom()] = included[e.getTo()] = true;
            mstEdges.add(e);
        }

        return mstEdges;
    }


    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += adjList[i] + "\n";
        return ret;
    }

}

public class Pustina {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n_nodes = Integer.parseInt(br.readLine());

        String oazi[] = new String[n_nodes];
        Graph<String> g = new Graph<String>(n_nodes,oazi);

        int n_edges = Integer.parseInt(br.readLine());

        int x,y,tezina1,tezina2;
        String xInfo, yInfo, pom[];
        for(int i=0;i<n_edges;i++)
        {
            pom = br.readLine().split(" ");
            x = Integer.parseInt(pom[0]); //reden broj
            xInfo = pom[1];
            y = Integer.parseInt(pom[2]);
            yInfo = pom[3]; //imeto na vtorata oaza
            g.adjList[x].setInfo(xInfo);
            g.adjList[y].setInfo(yInfo);
            tezina1 = Integer.parseInt(pom[4]);
            tezina2 = Integer.parseInt(pom[5]);
            g.addEdge(x,y,tezina1,tezina2);   //se dodava rebro so informacii dolzina i temperatura
            g.addEdge(y,x,tezina1,tezina2);
        }

        String oaza = br.readLine();
        br.close();

        List<Edge> pateki=g.pustina(g.searchIndex(oaza)); //gi sodrzi site rebra
        int dolzina = 0;  int i,j,tmp;

        // Sortiranje na vrskite za pecatenje
        for(i=0;i<n_nodes-1;i++)
            if(pateki.get(i).getFrom()>pateki.get(i).getTo())
            {
                tmp = pateki.get(i).getFrom();
                pateki.get(i).setFrom(pateki.get(i).getTo());
                pateki.get(i).setTo(tmp);
            }
        for(i=0;i<n_nodes-2;i++)
            for(j=i+1;j<n_nodes-1;j++)
                if ((pateki.get(i).getFrom()>pateki.get(j).getFrom())||((pateki.get(i).getFrom()==pateki.get(j).getFrom())&&(pateki.get(i).getTo()>pateki.get(j).getTo())))
                {
                    tmp = pateki.get(i).getFrom();
                    pateki.get(i).setFrom(pateki.get(j).getFrom());
                    pateki.get(j).setFrom(tmp);

                    tmp = pateki.get(i).getTo();
                    pateki.get(i).setTo(pateki.get(j).getTo());
                    pateki.get(j).setTo(tmp);

                    tmp = pateki.get(i).getWeight1();
                    pateki.get(i).setWeight1(pateki.get(j).getWeight1());
                    pateki.get(j).setWeight1(tmp);

                    tmp = pateki.get(i).getWeight2();
                    pateki.get(i).setWeight2(pateki.get(j).getWeight2());
                    pateki.get(j).setWeight2(tmp);

                }

        ListIterator<Edge> it = pateki.listIterator();

        while(it.hasNext()){
            Edge e = it.next();
            dolzina+=e.getWeight1();
            System.out.println(g.adjList[e.getFrom()].getInfo() + " " + g.adjList[e.getTo()].getInfo() + " " + e.getWeight1() + " " + e.getWeight2());
        }

        System.out.println(dolzina);
    }

}