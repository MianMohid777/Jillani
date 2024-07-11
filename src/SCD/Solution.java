package SCD;

public class Solution {
    public static void merge(int[] arr1, int m, int[] arr2, int n) {

        int[] merge = new int[m+n];
        int k = 0;

        for (int i = 0, j = 0; k < m+n;) {
            if (j < n && arr1[i] != 0 && arr1[i] < arr2[j]) {
                merge[k++] = arr1[i++];
            } else if (j < n  && arr2[j] < arr1[i]) {
                merge[k++] = arr2[j++];

            } else if (j < n && i < m && arr2[j] == arr1[i]) {
                merge[k++] = arr1[i++];
                merge[k++] = arr2[j++];


            } else if (arr1[i] != 0 && j == n)
            {
                merge[k++] = arr1[i++];
            }
            else
            {
                merge[k++] = arr2[j++];
                i++;
            }

        }
        arr1 = merge;
        for(int i = 0; i < m+n; i++)
        {
        System.out.println(arr1[i] + " , ");}
    }

    public static void main (String[] args)
    {
        int []arr1 = {-12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int []arr2 = {-49,-45,-42,-41,-40,-39,-39,-39,-38,-36,-34,-34,-33,-33,-32,-31,-29,-28,-26,-26,-24,-21,-20,-20,-18,-16,-16,-14,-11,-7,-6,-5,-4,-4,-3,-3,-2,-2,-1,0,0,0,2,2,6,7,7,8,10,10,13,13,15,15,16,17,17,19,19,20,20,20,21,21,22,22,24,24,25,26,27,29,30,30,30,35,36,36,36,37,39,40,41,42,45,46,46,46,47,48};

        Solution.merge(arr1,1,arr2,90);

    }
}
