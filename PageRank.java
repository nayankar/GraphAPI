package com.example.java;

import java.util.TreeMap;

public class PageRank {

    /**
     * Method calculatePageRank calculates pageRank of each vertex.
     * We assume page A has pages T1...Tn which point to it.
     * The parameter d is a damping factor which can be set between 0 and 1. I have set DAMPING_FACTOR to 0.85.
     * THRESHOLD is a constant and can be updated based on requirement. This is used to converge the pagerank on n iterations.
     * PR(A) = (1-d) + d (PR(T1)/C(T1) + ... + PR(Tn)/C(Tn))
     */

    private static final double THRESHOLD =0.0005;
    private static final float DAMPING_FACTOR = 0.85f; /* DAMPING_FACTOR can be set to any value between 0-1 */

    public static void calculatePageRank(DirectedGraph graph){
        boolean set_pagerank_flag = false;
        TreeMap<Integer, Double> pageMap = new TreeMap<Integer, Double>();
        /* Initializes pageRank of each vertex to 1.0. */
        for (Node v : graph.neighborVertices.keySet()){
            pageMap.put(v.nodeNumber,(double) 1.0);
        }
        /* until equilibrium is reached */
        while(!set_pagerank_flag) {
            //iteration++;
            float newCost=0f;
            int count=0;
            int i = 0;
            for (Node v : graph.neighborVertices.keySet()) {
                /* if no inbound neighbours found for that vertex then PageRank is defaulted to 0.15 */
                if (v.indegree == 0) {
                    newCost = (float) (1 - DAMPING_FACTOR);
                    if(newCost==pageMap.get(v.nodeNumber)){
                        count++;
                    }
                    pageMap.put(v.nodeNumber, (double) newCost);
                    i++;
                } else {
                    newCost = 0f;
                    for (Node invertex : v.inlist) {
                        newCost += (DAMPING_FACTOR * pageMap.get(invertex.nodeNumber)/ invertex.outdegree);
                    }
                    newCost += (1 - DAMPING_FACTOR);
                    if(newCost==pageMap.get(v.nodeNumber)){
                        count++;
                    }
                    pageMap.put(v.nodeNumber, (double) newCost);
                    i++;
                }
            }
            /* if count is equal to the number of nodes, we have reached equilibrium
            */
            if(count == graph.neighborVertices.size()){
                set_pagerank_flag=true;                 /* set_pagerank_flag set to true if equilibrium is reached */
            }
        }
        for (Integer v : pageMap.keySet()) {
            System.out.println("Page Rank of Vertex " +v+" is : " +pageMap.get(v));
        }
    }

    public static void main(String[] args) {
	// write your code here

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        DirectedGraph graph = new DirectedGraph();
        graph.addVertex(node1);
        graph.addVertex(node2);


        graph.addEdge(node1,node2);
        graph.addEdge(node1,node3);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node1);
        graph.addEdge(node4, node3);

        graph.instantiateCostEdge();
        System.out.println("The number of vertices is: " + graph.neighborVertices.keySet().size());
        System.out.println("The current graph: " + graph);
        System.out.println("the indegree of node 1 is "+ node1.indegree);
        System.out.println("the Outdegree of node 1 is "+ node1.outdegree);
        System.out.println("the in-list of node 1 is "+ node1.inlist);
        System.out.println("the out-list of node 1 is "+ node1.outlist);


        calculatePageRank(graph);  /* calculate the page rank for each node in the graph iteratively */

    }
}
