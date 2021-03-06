package render;

import graph.Graph;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import coloring.WelchPowellGraphColoring;

@SuppressWarnings("serial")
public class Render extends JFrame {

	private Graph graph;
	private int width;
	private int height; 
	private int polysize;
	
	public Render(Graph theGraph, int theViewportWidth, int theViewportHeight, int theGraphSize)
	{
		graph = theGraph;
		width = theViewportWidth;
		height = theViewportHeight;
		polysize = theGraphSize;
		
		setTitle("Graph Coloring Using Welch-Powell's Algorithm");
		setVisible(true);
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int num = graph.getVertices().size();
		int viewportWidth = width / 2;
		int viewportHeight = height / 2;
		
	    for (int i = 0; i < num; i++)
	    {
	    	graph.getVertices().get(i).setPosition((int) (viewportWidth + polysize * Math.cos(i * 2 * Math.PI / num)), (int) (viewportHeight + polysize * Math.sin(i * 2 * Math.PI / num)));
	    }
	}

	@Override
	public void paint(Graphics g)
	{
		int vertexCount = graph.getVertices().size();
		int edgeCount = graph.getEdges().size();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.WHITE);
		for(int i = 0;i < edgeCount;i++)
		{
			g.drawLine(graph.getEdges().get(i).getFromVertex().getPosX(), graph.getEdges().get(i).getFromVertex().getPosY(), graph.getEdges().get(i).getToVertex().getPosX(), graph.getEdges().get(i).getToVertex().getPosY());
		}
		
		for (int i = 0; i < vertexCount; i++)
	    {
			g.setColor(WelchPowellGraphColoring.COLORS[graph.getVertices().get(i).getColor()]);
			g.fillArc(graph.getVertices().get(i).getPosX() - 15, graph.getVertices().get(i).getPosY() - 15, 30, 30, 0, 360);
	    }	
		
		g.setColor(Color.WHITE);
		for (int i = 0; i < vertexCount; i++)
	    {
			g.drawString(graph.getVertices().get(i).getVertexName(), graph.getVertices().get(i).getPosX() - 15, graph.getVertices().get(i).getPosY() - 15);
	    }
		
		g.drawString("The chromatic Number is: " + WelchPowellGraphColoring.CHROMATIC_NUM, 10, height - 10);
	}
}
