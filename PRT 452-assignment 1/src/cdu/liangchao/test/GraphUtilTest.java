package cdu.liangchao.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GraphUtilTest {
//  g1:connected undirected graph
//	g2:not connected undirected graph
//	g3:worry undirected graph
//	g4:weakly connected graph
//	g5:unilaterally connected graph;
//	g6:strongly connected graph
//	g7:unconnected directed graph	
	private GraphUtil gu;
	private Graph g1;
	private Graph g2;
	private Graph g3;
	private Graph g4;
	private Graph g5;
	private Graph g6;
	private Graph g7;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int t=1;
		int v=3;
		int[][] e= {{0,1,0},{1,0,1},{0,1,0}};
		 g1=new graph(t,v,e);
		e=null;
		e= new int[][]{{0,0,0},{0,0,1},{0,1,0}};
		g2=new graph(t,v,e);
		e=null;
		e= new int[][]{{0,0,0},{1,0,1},{0,1,0}};
		 g3=new graph(t,v,e);
		 t=2;
		e=null;
		e= new int[][]{{0,1,0},{0,0,0},{0,1,0}};
		 g4=new graph(t,v,e);
		
		e=null;
		e= new int[][]{{0,1,0},{0,0,1},{0,0,0}};
		 g5=new graph(t,v,e);
			
		e=null;
		e= new int[][]{{0,1,0},{0,0,1},{1,0,0}};
		g6=new graph(t,v,e);
		
		e=null;
		e= new int[][]{{0,0,0},{0,0,1},{1,0,0}};
		 g7=new graph(t,v,e);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		fail("Not yet implemented");
	}
  boolean[] initVisited(boolean[] visited,int v) {
	  for (int i = 0; i < v; i++)
          visited[i] = false;
  }
	@Test
	public void testDFSUtil() {
		int v=g1.getVertex();
		boolean visited[] = new boolean[v];
      visited=initVisited(visited,v);
        gu.DFSUtil(0,visited,g1);
        for (int i = 0; i < v; i++)
    	assertTrue(visited[i]);
        
        int v=g2.getVertex();
        visited=initVisited(visited,v);
        gu.DFSUtil(0,visited,g2);
        assertFalse(visited[0]);
        
	}

	@Test
	public void testAdj() {
	 int[][] e=g1.getEdge();
	   int a=gu.adj(0,e);
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
      assertArrayEquals(gr.getEdge, e);
	}
	
	@Test
	public void testDirected2Undirected() {
		Graph gc=gu.directed2Undirevted();
		int[][] e=g4.getEdge();
		int v=g4.getVertex();
		for(int i=0;i<v;i++) {
	  		for(int j=0;j<v;j++) {
	  			if(e[i][j]>0)
	  		e[j][i]=e[i][j];
	  		}
	      }
	      assertArrayEquals(gc.getEdge, e);
	}
	
	@Test(expected=GraphException.class)
	public void testCheckGraph() {
		gu.checkGraph(g3);
	}
	
	@Test(expected=GraphException.class)
	public void testCheckInputLength() {
		gu.checkInputLength(4,g1);
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
