package main;

import graph.Graph;

import java.io.File;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import render.Render;
import coloring.WelchPowellGraphColoring;

public class Main {
	public static void main(String[] args) {
        
        WelchPowellGraphColoring w = new  WelchPowellGraphColoring();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Graph Files";
			}
			
			@Override
			public boolean accept(File file) {
				/**
				 * checks if the file chosen is of the required file format
				 */
				if(file.getAbsolutePath().endsWith(".graph") || file.isDirectory())
				{
					return true;
				}
				return false;
			}
		});
        
        int returnVal = fileChooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().getAbsolutePath().endsWith(".graph"))
        {
        	System.out.println("You chose to open this file: " + fileChooser.getSelectedFile().getAbsolutePath() + "\n");
        
	        /**
	         * create the graph
	         */
	        Graph testGraph = w.createGraph(fileChooser.getSelectedFile().getAbsolutePath());
	        
	        /**
	         * using the degrees, order the vertices by their degrees
	         */
	        HashMap<String, Integer> g = w.orderVerticesByDegree(testGraph);
	        
	        /**
	         * color the vertices and save them in a new structure
	         */
	        HashMap<String, String> coloredGraph = w.colorGraph(testGraph, g);
	        
			System.out.printf("THE CHOMATIC NUMBER OF THE GRAPH IS: %d \n", WelchPowellGraphColoring.CHROMATIC_NUM);
	        System.out.printf("Vertex  Colour \n");
	        
	        coloredGraph.forEach((String vertex, String colour) -> {
	            System.out.printf("%3s %10s \n", vertex, WelchPowellGraphColoring.COLORS[Integer.parseInt(colour)]);
	        });
	        
	        new Render(testGraph, 1000, 700, 300);
        }
        else
        {
        	System.out.println("Error: File type Exception");
        }
    }
}
