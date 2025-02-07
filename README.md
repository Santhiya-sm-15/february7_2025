# february7_2025
The problems that I solved today

1.You are given an integer limit and a 2D array queries of size n x 2. There are limit + 1 balls with distinct labels in the range [0, limit]. Initially, all balls are uncolored. For every query in queries that is of the form [x, y], you mark ball x with the color y. After each query, you need to find the number of distinct colors among the balls. Return an array result of length n, where result[i] denotes the number of distinct colors after ith query. Note that when answering a query, lack of a color will not be considered as a color.

Code:
class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int[] res=new int[queries.length];
        Map<Integer,Set<Integer>> color=new HashMap<>();
        Map<Integer,Integer> ball=new HashMap<>();
        int i=0;
        for(int[] a:queries)
        {
            int x=a[0],y=a[1];
            if(ball.containsKey(x)){
                color.get(ball.get(x)).remove(x);
                if(color.get(ball.get(x)).isEmpty())
                    color.remove(ball.get(x));
            }
            ball.put(x,y);
            if(!color.containsKey(y))
                color.put(y,new HashSet<>());
            color.get(y).add(x);
            res[i++]=color.size();
        }
        return res;
    }
}

2.Given an adjacency list, adj of Directed Graph, Find the number of strongly connected components in the graph.

Code:
class Solution {
    public void dfs(int src,boolean[] visited,Stack<Integer> st,ArrayList<ArrayList<Integer>> adj)
    {
        visited[src]=true;
        for(int a:adj.get(src))
        {
            if(!visited[a])
                dfs(a,visited,st,adj);
        }
        st.push(src);
    }
    public void dfs1(int src,boolean[] visited,ArrayList<Integer>[] adj)
    {
        visited[src]=true;
        for(int a:adj[src])
        {
            if(!visited[a])
                dfs1(a,visited,adj);
        }
    }
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int n=adj.size();
        int i;
        boolean[] visited=new boolean[n];
        Stack<Integer> st=new Stack<>();
        for(i=0;i<n;i++)
        {
            if(!visited[i])    
                dfs(iSS,visited,st,adj);
        }
        ArrayList<Integer>[] rev=new ArrayList[n];
        for(i=0;i<n;i++)
            rev[i]=new ArrayList<>();
        for(i=0;i<n;i++)
        {
            for(int a:adj.get(i))
                rev[a].add(i);
        }
        visited=new boolean[n];
        int scc=0;
        while(!st.isEmpty())
        {
            int x=st.pop();
            if(!visited[x])
            {
                dfs1(x,visited,rev);
                scc++;
            }
        }
        return scc;
    }
}

3.There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network. A critical connection is a connection that, if removed, will make some servers unable to reach some other server. Return all critical connections in the network in any order.

Code:
class Solution {
    ArrayList<Integer>[] adj;
    public void dfs(int src,int par,boolean[] visited,int[] tym,int[] low,int time,List<List<Integer>> res)
    {
        visited[src]=true;
        tym[src]=low[src]=time;
        time++;
        for(int a:adj[src])
        {
            if(a==par)
                continue;
            if(!visited[a])
            {
                dfs(a,src,visited,tym,low,time,res);
                if(low[a]>tym[src])
                    res.add(Arrays.asList(src,a));
            }
            low[src]=Math.min(low[src],low[a]);
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) 
    {
        List<List<Integer>> res=new ArrayList<>();
        boolean[] visited=new boolean[n];
        adj=new ArrayList[n];
        int i;
        for(i=0;i<n;i++)
            adj[i]=new ArrayList<>();
        for(List<Integer> x:connections)
        {
            int a=x.get(0);
            int b=x.get(1);
            adj[a].add(b);
            adj[b].add(a);
        }
        int[] tym=new int[n];
        int[] low=new int[n];
        dfs(0,-1,visited,tym,low,0,res);
        return res;
    }
}

4.Given an undirected connected graph with V vertices and adjacency list adj. You are required to find all the vertices removing which (and edges through it) disconnects the graph into 2 or more components and return it in sorted manner. Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.

Code:


class Solution
{
    public void dfs(int src,int par,int[] tym,int[] low,int time,boolean[] visited,ArrayList<ArrayList<Integer>> adj,boolean[] mark)
    {
        visited[src]=true;
        low[src]=tym[src]=time;
        time++;
        int child=0;
        for(int a:adj.get(src))
        {
            if(a==par)
                continue;
            if(!visited[a])
            {
                dfs(a,src,tym,low,time,visited,adj,mark);
                low[src]=Math.min(low[src],low[a]);
                if(low[a]>=tym[src] && par!=-1)
                    mark[src]=true;
                child++;
            }
            else
                low[src]=Math.min(low[src],tym[a]);
        }
        if(child>1 && par==-1)
            mark[src]=true;
    }
    public ArrayList<Integer> articulationPoints(int V,ArrayList<ArrayList<Integer>> adj)
    {
        int[] tym=new int[V];
        int[] low=new int[V];
        int i;
        boolean[] visited=new boolean[V];
        boolean[] mark=new boolean[V];
        dfs(0,-1,tym,low,0,visited,adj,mark);
        ArrayList<Integer> res=new ArrayList<>();
        for(i=0;i<V;i++)
        {
            if(mark[i])
                res.add(i);
        }
        if(res.isEmpty())
            res.add(-1);
        return res;
    }
}

5.Given a weighted and directed graph of v vertices and edges, Find the shortest distance of all the vertex's from the source vertex, src and return a list of integers where the ith integer denotes the distance of the ith node from the source node. If a vertices can't be reach from the s then mark the distance as 10^8. Note: If there exist a path to a negative weighted cycle from the source node then return {-1}.

Code:


// User function Template for Java

/*   Function to implement Bellman Ford
 *   edges: 2D array which represents the graph
 *   src: source vertex
 *   V: number of vertices
 */
class Solution {
    static int[] bellmanFord(int V, int[][] edges, int src) {
        int i;
        int[] dist=new int[V];
        Arrays.fill(dist,(int)1e8);
        dist[src]=0;
        for(i=0;i<V-1;i++)
        {
            for(int[] x:edges)
            {
                int u=x[0];
                int v=x[1];
                int wt=x[2];
                if(dist[u]!=(int)1e8 && dist[u]+wt<dist[v])
                    dist[v]=dist[u]+wt;
            }
        }
        for(int[] x:edges)
        {
            int u=x[0];
            int v=x[1];
            int wt=x[2];
            if(dist[u]!=(int)1e8 && dist[u]+wt<dist[v])
                return new int[]{-1};
        }
        return dist;
    }
}

6.The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph. The graph is represented as an adjacency matrix. mat[i][j] denotes the weight of the edge from i to j. If mat[i][j] = -1, it means there is no edge from i to j. Note: Modify the distances for every pair in place.

Code:


// User function template for JAVA

class Solution {
    public void shortestDistance(int[][] mat) {
        int n=mat.length,m=mat[0].length,i,j,k;
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(mat[i][j]==-1)
                    mat[i][j]=(int)1e9;
            }
        }
        for(k=0;k<n;k++)
        {
            for(i=0;i<n;i++)
            {
                for(j=0;j<n;j++)
                    mat[i][j]=Math.min(mat[i][j],mat[i][k]+mat[k][j]);
            }
        }
        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(mat[i][j]==(int)1e9)
                    mat[i][j]=-1;
            }
        }
    }
}
