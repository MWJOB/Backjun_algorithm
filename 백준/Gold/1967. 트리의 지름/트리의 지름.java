import java.util.*;
import java.io.*;



public class Main{
	static ArrayList<Integer[]> graph[];
	static boolean visited[];
	static int max = 0;
	static int findNode = 1 ;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		visited=new boolean[n+1];
		graph=new ArrayList[n+1];
		for(int i=1;i<graph.length;i++) {
			graph[i]=new ArrayList<>();
		}

		for(int i=0;i<n-1;i++) {
			st=new StringTokenizer(br.readLine());
			int node1=Integer.parseInt(st.nextToken());
			int node2=Integer.parseInt(st.nextToken());
			int cost=Integer.parseInt(st.nextToken());
			graph[node1].add(new Integer[] {node2,cost});
			graph[node2].add(new Integer[] {node1,cost});
		
		}
		dfs(1,0);
		max=0;
		Arrays.fill(visited,false);
		dfs(findNode,0);
		
		System.out.println(max);


	}
	public static void dfs(int node,int sum) {
		visited[node]=true;
		if(sum>max) {
			max = sum;
			findNode = node;
		}

		for(Integer[] temp:graph[node]) {
			int next = temp[0];
			int cost = temp[1];
			if(!visited[next]){
				dfs(next,sum+cost);
			}
		}


	}
}
