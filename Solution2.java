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