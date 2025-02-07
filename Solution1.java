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