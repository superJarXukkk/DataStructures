package alpgrithms;

/**
 * NEXT数组算法：
 *
 * 核心：前缀式 与 后缀式 的对比，比较：
 *      1.当前缀式 == 后缀式
 *
 *      2.当后缀式 ！=前缀式
 *
 * 注意：在比较中， 涉及到 前缀下标prefix 和 后缀下标suffix 的 下标变化：
 *
 *      在方法1中： suffix 下标变化较为巧妙：
 *              初始化 next【0】 = -1；  suffix = 0；  prefix = -1；
 *              在 后缀式 ！=前缀式（持续） 的情况下，prefix 会在 prefix = next【prefix】的情况下一直回归到 0
 *              这时 ，next【prefix】 = -1；
 *              又因 前缀式 == 后缀式 情况下 有附加条件 ：||（或）prefix = -1； suffix在不需要 for循环 的情况下会自增；
 *
 *
 *      在方法2中：理解 在 后缀式 ！=前缀式 的情况下 与方法1较为相同；只是有微小变化：prefix = next【prefix -1】
 *      且 初始化变为；suffix = 1； prefix = 0；next【0】 = 0；且 suffix 是在 for循环里自增；
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


public class KMP {

    //==============================================================================
    //前缀表→移
    private static int[] KMPMatch(String str2) {
        char[] p = str2.toCharArray();
             int[] next = new int[p.length];
             next[0] = -1;
             int suffix = 0;
             int preffix = -1;

             while (suffix < p.length - 1) {
                 /**
                  * preffix = -1；
                  * 1.当初此进入：
                  * 2.当一个前缀式的所有子字符串与后缀都不相等时：
                  */
                   if (preffix == -1 || p[suffix] == p[preffix]) {
                          next[++suffix] = ++preffix;
                        } else {
                       /**
                        *      A B C D A B D
                        * 遍历：A B C D A B D
                        *      A B C   A B D;（这时C 与 D不相等）
                        *      当遇到 D 是，最大子字符串就不可能是A B D ，
                        *      担任有可能是 ，前方A B C 字符串中的 子串；
                        *      所以采用：prefix = next[prefix] ，来检索A B C中
                        *      所包含的最大重复子串；在这里next【prefix】等于
                        *      0；下一次比较p[prefix] = A,p[suffix] = D任然不相等；
                        *      在下一次：next【prefix】=-1；
                        */
                       //当遇到，字符不相等的情况，那么最大子字符串，
                       //当next【preffix】 == 0 时，那么极有可能会在下次情况下，发生next【0】 = -1
                         preffix = next[preffix];
                       }
                 }
             return next;
    }
//=====================================================================================
//前缀表方法解决问题
    public static int[]  KMP(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        int prefix = 0;//表示前缀,初始化为0；

        //后缀，初始化为1：从数组下标1开始搜索；
        int suffix = 1;// 表示后缀；
        char[] chars = str.toCharArray();

        while (suffix < chars.length){

            if(prefix!=0 &&chars[suffix] != chars[prefix]){
                //见上文表达式：当前缀和后缀表达式不相等时，前缀回退到next【prefix - 1】 对应的值（也就是当前前缀表达式的子串）
                //在方法1中：next【prefix】= 当前方法中的next【prefix - 1】；理解方法可以见方法1；
                //在这里 next【prefix】 代表实例中为：A B A C 的最大子串，这个实例已经在if判断语句中被比较过，所以应该比较其子串next【preffix - 1】 A B A中的最大子串；
                prefix = next[prefix - 1];
            }
            if (chars[suffix] == chars[prefix]){
                next[suffix] = ++prefix;
            }
            suffix++;
        }
        return next ;
    }

    private static int KMPSearch(String str1, String str2, int[] next) {
        for (int i = 0,j = 0; i <str1.length(); i++) {
            // 需要处理 str1.charAt(i) != str2.charAt(j), 调整 j 的 大小
            // KMP代码的核心点
            while(j>0&&str1.charAt(i)!=str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }





    public static void main(String[] args) {
        KMPMatch("abacdababc");
        KMP("abacdababc");
    }
}
