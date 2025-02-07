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
