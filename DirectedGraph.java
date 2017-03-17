package com.example.java;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Created by nayanks on 3/9/17.
 */

/**
 * Instantiate Class DirectedGraph to create a Directed Graph object.
 * This has an Inner Static class Edge which is used to define an edge.
 * An instance of InnerClass-Edge can exist only within an instance of OuterClass-Directed Graph.
 * Edge has two Nodes and cost c associated with it.
 */

public class DirectedGraph{

    public static class Edge{
        private Node node;
        private float cost;

        public Edge(Node node){
            this.node= node;
            cost = 1;
        }

        public Node getNode() {
            return node;
        }

        public float getCost() {
            return cost;
        }
        /**
         * Overriding the toString function to Give the output of the Edge instantiated with "To vertex" and the cost of the Edge.
         */
        @Override
        public String toString() {
            return "Edge [To vertex=" + node.toString() + ", cost=" + cost + "]";
        }
    }

    /**
     * A HashMap neighborVertices is used to map each vertex to its list of adjacent vertices.
     */

    public HashMap<Node, List<Edge>> neighborVertices = new HashMap<Node, List<Edge>>();

    /**
     * String representation of graph.
     */

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Node node : neighborVertices.keySet())
            s.append("\n    " + node.toString() + " -> " + neighborVertices.get(node));
        return s.toString();
    }

    /**
     * Adds a vertex to the graph. Nothing happens if vertex is already in graph.
     */

    public void addVertex(Node node, List<Node>... lists) {
        if (neighborVertices.containsKey(node)){
            return;}
        for(List<Node> list:lists){
            for(Node n:list){
                this.addVertex(n);
            }
        }
        neighborVertices.put(node, new ArrayList<Edge>());
    }

    /**
     * This implementation allows the creation of multi-edges and self-loops.
     * also constantly updating the indegree and outdegree of a node.
     * also updates the neighbours list of a node.
     */

    public void addEdge(Node from, Node to) {
        this.addVertex(from);
        this.addVertex(to);
        neighborVertices.get(from).add(new Edge(to));
        from.outdegree++;
        to.indegree++;
        from.outlist.add(to);                           /* adds node-to to the outdegreelist of the from-node */
        to.inlist.add(from);                            /* adds node-to to the outdegreelist of the from-node */
    }


    /**
     * Method contains Returns True iff graph contains vertex.
     */

    public boolean containsVertex(Node node) {
        return neighborVertices.containsKey(node);
    }

    /**
     * Method instantiateCostEdge Initilaizes cost of each outdegree edge of the vertex to 1/outdegree of the edge.
     */

    public void instantiateCostEdge(){
        for (Node v : neighborVertices.keySet()) {
            for (Edge e : neighborVertices.get(v)) {
                e.cost = (float)1/v.outdegree;
            }
        }
    }

    /**
     * Method returns the cost of an Edge.
     */

    public float getCost(Node from, Node to) {
        for(Edge e :  neighborVertices.get(from)){
            if(e.node.equals(to))
                return e.cost;
        }
        return -1;
    }
}
