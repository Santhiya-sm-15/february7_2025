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