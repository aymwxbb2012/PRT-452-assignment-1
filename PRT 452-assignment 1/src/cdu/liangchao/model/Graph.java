package cdu.liangchao.model;

import java.util.Arrays;



public class Graph {

	private int type;
	private int vertex;
    private int edge[][];  

  

	public Graph() {
		
	}


    
   public Graph(int type, int vertex, int[][] edge) {
		super();
		this.type = type;
		this.vertex = vertex;
		this.edge = edge;
	}



public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getVertex() {
		return vertex;
	}



	public void setVertex(int vertex) {
		this.vertex = vertex;
	}



	public int[][] getEdge() {
		return edge;
	}



	public void setEdge(int[][] edge) {
		this.edge = edge;
	}



@Override
	public String toString() {
		return "Graph [type=" + type + ", vertex=" + vertex + ", edge=" + Arrays.toString(edge) + "]";
	}


}
