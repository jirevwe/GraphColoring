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
	        /**
	         * create the graph
	         */
	        Graph testGraph = w.createGraph(fileChooser.getSelectedFile().getAbsolutePath());
	        
	        /**
	         * using the degrees, order the vertices by their degrees
	         */
	        HashMap<String, Integer> g = w.orderVerticesByDegree(testGraph);
	        
	        /**
	         * color the vertices
	         */
	        w.colorGraph(testGraph, g);
	        
	        new Render(testGraph, 1000, 700, 300);
        }
        else
        {
        	System.out.println("Error: File type Exception");
        }
    }
}
