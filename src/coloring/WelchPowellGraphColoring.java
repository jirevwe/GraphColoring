/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coloring;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.MapUtil;

/**
 *
 * @author Raymond Tukpe
 */
public class WelchPowellGraphColoring {
 
	public static int CHROMATIC_NUM = 0;
	public final static Color[] COLORS = {
		Color.RED,
		Color.BLUE,
		Color.GREEN,
		Color.DARK_GRAY,
		Color.YELLOW,
		Color.GRAY,
		Color.CYAN,
		Color.PINK
	};
	
	/**
	 * parse the input from the file and return the graph
	 * 
	 * @param vertexString should have the format A B C D E F G 
	 * @param edgeString should have the format AB BC CD DE EF FG GA 
	 * @return a representation of the graph of the supplied input 
	 */
	public Graph ParseInput(String vertexString, String edgeString)
	{
		List<Vertex> vertices = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        
        HashMap<String, Vertex> v = new HashMap<>();
        
        for(char s : vertexString.toCharArray())
        {
        	if(s != ' ')
        	{
        		v.put(String.valueOf(s), new Vertex(String.valueOf(s)));
    		}
        }
        
        char[] edgeArray = edgeString.toCharArray();
        for(int i = 0;i < edgeArray.length;i++)
        {
        	if(edgeArray[i] == ' ')
        	{
        		edges.add(new Edge(String.valueOf(i), 0, v.get(String.valueOf(edgeArray[i + 1])), v.get(String.valueOf(edgeArray[i + 2]))));
        	}	
        }  
        
        v.forEach((String x, Vertex y) ->
        {
        	vertices.add(y);
        });
        
        return new Graph(edges, vertices);
	}
    
    /**
     * 
     * Get the degrees of vertices in a graph
     * 
     * @param g the graph g
     * @return a map of the vertices in g, with their corresponding degree value
     */
    public HashMap<String, Integer> orderVerticesByDegree(Graph g)
    {
        HashMap<String, Integer> vertexDegree = new HashMap<>();
                
        g.getVertices().stream().forEach((vertex) ->
        {
            vertexDegree.put(vertex.getVertexName(), g.getDegreeOfVertex(vertex.getVertexName()));
        });
        
        return MapUtil.sortMapByValue(vertexDegree);
    }
    
    /**
     * Colors a graph
     * 
     * @param g the graph to be colored
     * @param mapOfVerticesWithDegrees a hash map that contains the vertices and the corresponding degree values
     * @return a map of the vertices in graph g, with their corresponding color values  
     */
    public HashMap<String, String> colorGraph(Graph g, HashMap<String, Integer> mapOfVerticesWithDegrees)
    {
        HashMap<String, String> result = new HashMap<>();
        int count = 1;
        int chomatic = 0;

        //get the vertices
        for(String vertex : mapOfVerticesWithDegrees.keySet())
        {
            if(g.getVertex(vertex).isColoured() == false){
                for(Vertex v : g.getVertices())
                {
                    if(!g.getVertex(vertex).chkAdjacentVertex(v.getVertexName()))
                    {
                        if(!result.containsKey(v.getVertexName()))
                        {
                            if (v.isColoured() == false) {
                                result.put(v.getVertexName(), String.valueOf(count));
                                g.getVertex(v.getVertexName()).colourVertex();
                                g.getVertex(v.getVertexName()).setColor(count);
                                chomatic = count;
                            }
                        }
                    }
                }
            }
            count++;
        }  
        WelchPowellGraphColoring.CHROMATIC_NUM = chomatic;
        return result;
    }

    /**
     * 
     */
    public Graph createGraph(String pathToFile)
    {
    	String vertexLine = null;
	    String edgesLine = null;
    	File file = new File(pathToFile);
    	try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
    	    String line = null;
    	    while ((line = reader.readLine()) != null) {
    	        if(line.startsWith("Vertices"))
    	        {
    	        	vertexLine = line.substring(10);
    	        }
    	        if(line.startsWith("Edges"))
    	        {
    	        	edgesLine = line.substring(6);
    	        }
    	    }
    	    
    	} catch (IOException x) {
    	    System.err.format("IOException: %s%n", x);
    	}   
    	return ParseInput(vertexLine, edgesLine);
    }
}
