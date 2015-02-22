/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

import java.util.HashSet;

/**
 *
 * @author Raymond Tukpe
 */
public class Vertex
{    
    private final String vertexName;
    private final HashSet<Vertex> adjacent;
    private boolean isColoured = false;
    private int posX;
	private int posY;
	private int color = 100;
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
    
    public boolean isColoured() {
        return isColoured;
    }

    public void colourVertex() {
        this.isColoured = true;
    }
    
    public void setPosition(int x, int y)
    {
		this.posX = x;
		this.posY = y;
    }

    /**
     *
     * @param adjacent
     */
    public void addToAdjacent(Vertex adjacent) {
        this.adjacent.add(adjacent);
    }

    public boolean chkAdjacentVertex(String vName)
    {
        boolean res = false;
        for(Vertex v : adjacent)
        {
            if(v.vertexName.equalsIgnoreCase(vName))
            {
                res = true;
            }
        }
        return res;
    }
    
	/**
     *
     * @return
     */
    public HashSet<Vertex> getAdjacent()
    {
        return adjacent;
    }
    
    /**
     * Gets the vertex name
     * @return the vertex name
     */
    public String getVertexName()
    {
        return this.vertexName;
    }
    
    /**
     *create a new vertex
     * @param vName the vertex name
     */
    public Vertex(String vName)
    {
        this.adjacent = new HashSet<>();
        this.vertexName = vName;
    }

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}   
}
