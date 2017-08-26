package cdu.liangchao.test;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;

import cdu.liangchao.model.Graph;
import cdu.liangchao.model.GraphException;
import cdu.liangchao.util.GraphUtil;

public class GraphUtilTest {
//  g1:connected undirected graph
//	g2:not connected undirected graph
//	g3:worry undirected graph
//	g4:weakly connected graph
//	g5:unilaterally connected graph;
//	g6:strongly connected graph
//	g7:unconnected directed graph	
	private static GraphUtil gu;
	private static Graph g1;
	private static Graph g2;
	private static Graph g3;
	private static Graph g4;
	private static Graph g5;
	private static Graph g6;
	private static Graph g7;

	
	@BeforeClass
	public static void setUpBeforeClass() {
		int t=1;
		int v=3;
		int[][] e= {{0,1,0},{1,0,1},{0,1,0}};
		 g1=new Graph(t,v,e);
		e=null;
		e= new int[][]{{0,0,0},{0,0,1},{0,1,0}};
		g2=new Graph(t,v,e);
		e=null;
		e= new int[][]{{0,0,0},{1,0,1},{0,1,0}};
		 g3=new Graph(t,v,e);
		 t=2;
		e=null;
		e= new int[][]{{0,1,0},{0,0,0},{0,1,0}};
		 g4=new Graph(t,v,e);
		
		e=null;
		e= new int[][]{{0,1,0},{0,0,1},{0,0,0}};
		 g5=new Graph(t,v,e);
			
		e=null;
		e= new int[][]{{0,1,0},{0,0,1},{1,0,0}};
		g6=new Graph(t,v,e);
		
		e=null;
		e= new int[][]{{0,0,0},{0,0,1},{1,0,0}};
		 g7=new Graph(t,v,e);
	     gu= new GraphUtil();
	}

	
  boolean[] initVisited(boolean[] visited,int v) {
	  for (int i = 0; i < v; i++)
          visited[i] = false;
	  return visited;
  }
	@Test
	public void testDFSUtil() {
		int v=g1.getVertex();
		int[][] e=g1.getEdge();
		boolean visited[] = new boolean[v];
      visited=initVisited(visited,v);
        gu.DFSUtil(0,visited,e);
        for (int i = 0; i < v; i++)
    	assertTrue(visited[i]);
        
         v=g2.getVertex();
         e=g2.getEdge();
        visited=initVisited(visited,v);
        gu.DFSUtil(0,visited,e);
        assertFalse(visited[1]);
        assertFalse(visited[2]);
        
	}

	@Test
	public void testAdj() {
	 int[][] e=g1.getEdge();
	 boolean[] visited= {true,false,false};
	   int a=gu.adj(0,e,visited);
	   assertEquals(a, 1);
	}
	
	@Test
	public void testGetTranspose() {
	  Graph gr=gu.getTranspose(g1);
      int[][] e=g1.getEdge();
      int v=g1.getVertex();
      int a;
      for(int i=0;i<v;i++) {
  		for(int j=i;j<v;j++) {
  	    a=e[i][j];
  		e[i][j]=e[j][i];
  		e[j][i]=a;
  		}
      }
      assertArrayEquals(gr.getEdge(), e);
	}
	
	@Test
	public void testDirected2Undirected() {
		Graph gc=gu.directed2Undirected(g4);
		int[][] e=g4.getEdge();
		int v=g4.getVertex();
		for(int i=0;i<v;i++) {
	  		for(int j=0;j<v;j++) {
	  			if(e[i][j]>0)
	  		e[j][i]=e[i][j];
	  		}
	      }
	      assertArrayEquals(gc.getEdge(), e);
	}
	
	@Test(expected=GraphException.class)
	public void testCheckGraph() {
		gu.checkGraph(g3);
	}
	
	@Test(expected=GraphException.class)
	public void testCheckInputLength() {
		int v=g1.getVertex();
		gu.checkInputLength(4,v);
	}
	
	
//	0.unconnected graph,
//	1.connected undirected graph,
//	2.weakly connected graph,
//	3.unilaterally connected graph,
//	4.strongly connected graph
	@Test
	public void testEstimateConnectivity() {
	assertEquals(gu.estimateConnectivity(g1), 1);
	assertEquals(gu.estimateConnectivity(g2), 0);
	assertEquals(gu.estimateConnectivity(g4), 2);
	assertEquals(gu.estimateConnectivity(g5), 3);
	assertEquals(gu.estimateConnectivity(g6), 4);
	assertEquals(gu.estimateConnectivity(g7), 0);
	}
	
}
