package cdu.liangchao.app;

import java.util.Scanner;

import cdu.liangchao.model.Graph;
import cdu.liangchao.util.GraphUtil;

public class GraphVerifier {
	
     
	public static void main(String[] args) {
		 GraphUtil gu=new GraphUtil();
	      
		  Scanner scan = new Scanner(System.in);
	        System.out.println("Enter number of Types (1.undirected graph   2.directed graph)");
            int t=scan.nextInt();
	        System.out.println("Enter number of Vertices");
	        int v = scan.nextInt();
	        /** make graph **/
	        int[][] e = new int[v][v];
	        int i=0;
	        System.out.println("Enter a "+v+"*"+v+"matrix  (Enter quit will stop the project)");
	       scan.nextLine();
	        StringBuffer sb=new StringBuffer(scan.nextLine());
	        
	        while(!(("quit").equals(sb.toString().trim())))
	        {
	         String[] ss=sb.toString().split(" ");
            gu.checkInputLength(ss.length, v);	       
	         for(int j=0;j<v;j++){
	          e[i][j]=Integer.parseInt(ss[j]); 
	         }
	         i++;
	         sb.setLength(0);
	         sb.append(scan.nextLine());
	        }
	        
	        Graph g= new Graph(t,v,e);
	        gu.checkGraph(g);
		
		

  int	s=gu.estimateConnectivity(g);
  if (s==0)
      System.out.println("unconnected graph");
  else if(s==1)
      System.out.println("connected undirected graph");
  else if(s==2)
  	System.out.println("weakly connected graph");
  else if(s==3)
  	System.out.println("unilaterally connected graph");
  else if(s==4)
	  	System.out.println("strongly connected graph");

	}

}
