package com.example.java;

import java.util.ArrayList;

/**
 * Created by nayanks on 3/12/17.
 */
public class Node {

    public int nodeNumber;
    public int indegree;
    public int outdegree;
    public ArrayList<Node> inlist;
    public ArrayList<Node> outlist;

    public Node(int nodeNumber) {
        this.nodeNumber = nodeNumber;
        this.indegree =0;                       /* this attribute retrieves indegree of a node in constant time */
        this.outdegree=0;                       /* this attribute retrieves outdegree of a node in constant time  */
        this.inlist = new ArrayList<Node>();    /* this attribute retrieves indegree list of nodes of a node in constant time  */
        this.outlist = new ArrayList<Node>();   /* this attribute retrieves outdegree list of nodes of a node in constant time  */
    }

    @Override
    public String toString() {
        return "Vertex : " +nodeNumber;
    }
}
