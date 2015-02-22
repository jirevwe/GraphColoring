/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.util.List;

/**
 *
 * @author Raymond Tukpe
 */
public class Graph {
    
    private final List<Edge> edges;
    private final List<Vertex> vertices;
    
    public Graph(List<Edge> theEdges, List<Vertex> theVertices)
    {
        this.edges = theEdges;
        this.vertices = theVertices;
    }
    
    /**
     * 
     * @param vertexName the name of the vertex
     * @return the number of vertices adjacent to a the specified vertex
     */
    public int getDegreeOfVertex(String vertexName)
    {
        int c = 0;
        c = edges.stream().filter((e) -> (e.getFromVertex().getVertexName().equalsIgnoreCase(vertexName) ||
                e.getToVertex().getVertexName().equalsIgnoreCase(vertexName))).map((_item) -> 1).reduce(c, Integer::sum);
        return c;
    }
    
    /**
     * 
     * @return the edges of the graph
     */
    public List<Edge> getEdges()
    {
        return edges;
    }

    /**
     * 
     * @param vName the vertex name
     * @return the Vertex object with the name vName
     */
	public Vertex getVertex(String vName)
    {
        for(Vertex v: vertices)
        {
            if(v.getVertexName().equalsIgnoreCase(vName))
            {
                return v;
            }
        }
        return null;
    }

	/**
	 * 
	 * @return the vertices of the graph 
	 */
	public List<Vertex> getVertices()
    {
        return vertices;
    }
}
