

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