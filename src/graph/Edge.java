/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

/**
 *
 * @author Raymond Tukpe
 */
public class Edge {
    
    private final int edgeWeight;
    private final Vertex fromVertex;
    private final Vertex toVertex;
    private final String edgeID;
    
    public Edge(String eEdgeID, int eWeight, Vertex eFromVertex, Vertex eToVertex)
    {
        this.edgeID = eEdgeID;
        this.fromVertex = eFromVertex;
        this.toVertex = eToVertex;
        this.edgeWeight = eWeight;
        
        //add each adjacent pair to each others neighbour list
        fromVertex.addToAdjacent(toVertex);
        toVertex.addToAdjacent(fromVertex);
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public String getEdgeName() {
        return edgeID;
    }
    
    @Override
    public String toString()
    {
        return fromVertex.getVertexName() + toVertex.getVertexName();
    }
}