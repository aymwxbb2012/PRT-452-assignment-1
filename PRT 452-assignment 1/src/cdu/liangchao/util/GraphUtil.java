package cdu.liangchao.util;

import cdu.liangchao.model.Graph;
import cdu.liangchao.model.GraphException;

public class GraphUtil {



	
	
	public int adj(int x,int[][] a,boolean visited[])   //get next vertex
	{   
		
	   for(int i=0;i<a.length;i++) {  
	        if(a[x][i]>=1 && visited[i]==false)  
	           return i;  
	   }
	   return 0;  
	}  

	
	  public void DFSUtil(int v,boolean visited[],int[][] a)
	    {
		
		

		      visited[v]=true;  
		    
		      int adj=adj(v,a,visited);  
		      while(adj!=0)  
		     {  
		              
		             DFSUtil(adj,visited,a);        
		    
		          adj=adj(v,a,visited);  
		     }  
		 

		 }  
      //turn directed graph to undirected graph
	  public Graph directed2Undirected(Graph g) {
	    	int[][] e=g.getEdge();
			int v=g.getVertex();
			for(int i=0;i<v;i++) {
		  		for(int j=0;j<v;j++) {
		  			if(e[i][j]>0)
		  		e[j][i]=e[i][j];
		  		}
	    }
			Graph gc= new Graph(g.getType(),v,e);
			return gc;
	    }
	 
	    // get transpose of the graph
	  public  Graph getTranspose(Graph g)
	    {
	    	int v=g.getVertex();
	    	int[][] e=g.getEdge();
	    	int t=g.getType();
	        
	        for(int i=0;i<v;i++) {
        		for(int j=0;j<v;j++) {
        		e[i][j]=e[j][i];
        		}
        	}
	        Graph gr = new Graph(t,v,e);
	        return gr;
	    }
	    
	    //check whether this graph is a right undirected graph
	    public void checkGraph(Graph g) {
	    	int t=g.getType();
	    	int[][] e=g.getEdge();
	    	int v=g.getVertex();
	    
	        if(t==1) {
	        	for(int i=0;i<v;i++) {
	        		for(int j=0;j<v;j++) {
	        		if(e[i][j]==e[j][i])
	        			throw new GraphException("this graph is not a undirected graph");
	        		}
	        	}
	    		
	    	
	    }
	    }
	   
	    
	    public void checkInputLength(int l,int v) {
	    	if(l!=v) throw  new GraphException("matrix is wrong");
	    }
	    
	 //0.unconnected graph,1.connected undirected graph,2.weakly connected graph,3.unilaterally connected graph,4.strongly connected graph
	    public int estimateConnectivity(Graph g)
	    {   int V=g.getVertex();
	        int T=g.getType();
	        boolean visited[] = new boolean[V];
	        for (int i = 0; i < V; i++)
	            visited[i] = false;
	       
	     Graph gc=directed2Undirected(g);
	        DFSUtil(0, visited,gc.getEdge());
	        for (int i = 0; i < V; i++)
	            if (visited[i] == false)
	                return 0;
	        
	        
	        for (int i = 0; i < V; i++)
	            visited[i] = false;
	        
	        DFSUtil(0, visited,g.getEdge());
	 
	        for (int i = 0; i < V; i++)
	            if (visited[i] == false)
	                return 2;
	         
	       if(T==1)
	    	   return 1;
	       
	        Graph gr = getTranspose(g);
	 

	        
	        for (int i = 0; i < V; i++)
	            visited[i] = false;
	 
	     
	        DFSUtil(0, visited,gr.getEdge());
	 
	       
	        for (int i = 0; i < V; i++)
	            if (visited[i] == false)
	                return 3;
	 
	        return 4;
	    }
}
