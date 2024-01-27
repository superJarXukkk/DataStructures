package alpgrithms;


import java.util.Arrays;

//该算法讲解了动态规划的核心思想：依赖于上一次或上几次产出的数据，来进行这次数据的导出；
public class DynamicProgramming {
     public static void main(String[] args) {
         int[] w = {1,3,4};//每件物品的重量
         int[] val = {1500,2000,3000};// 物品的价格
         int c = 4;//背包的容量
         int n = w.length;//物品的个数

         //创建二维数组 v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大值
         int[][] v = new int[n+1][c+1];

         //创建二维数组 记录存放路径
         int[][] path = new int[n+1][c+1];


         //初始化第一行 和第一列的数据 在本案例中可以不用处理，因为默认就是0
         for (int i = 0; i < v.length; i++) {
             v[i][0]=0;
         }
         for (int i = 0; i < v[0].length; i++) {
             v[0][i]=0;
         }
         // 根据前面的公式来动态规划处理
         for (int i = 1; i < v.length; i++) {// 不处理第一行
             for (int j = 1; j < v[0].length; j++) {// 不处理第一列
                 if(w[i-1]>j){//因为i是从1开始的所有需要减一 -1  以保证 从数组第一个值开始取值
                     v[i][j]=v[i-1][j];
                 }else{
                     //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                     if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                         v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                         path[i][j]=1;//修改路径的值
                     }else{
                         v[i][j] = v[i-1][j];
                     }
                 }
             }
         }

         for (int i = 0; i < v.length; i++) {
             System.out.println(Arrays.toString(v[i]));
         }

         System.out.println("--------------");
         for (int i = 0; i < v.length; i++) {
             System.out.println(Arrays.toString(path[i]));
         }

         for (int i = 0; i < path.length; i++) {
             for (int j = 0; j < path[0].length; j++) {
                 if(path[i][j]==1){
                     System.out.println("第"+i+"个商品放入背包");
                 }
             }
         }

         System.out.println("--------------");
         int i = path.length-1; //行的最大下标
         int j = path[0].length-1;//列的最大下标
         while(i>0&&j>0){ //逆向遍历
             if(path[i][j]==1){
                 System.out.println("第"+i+"个商品放入背包");
                 j -= w[i-1]; //背包减去容量
             }
             i--;
         }
     }

}
