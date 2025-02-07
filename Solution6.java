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