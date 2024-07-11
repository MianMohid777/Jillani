package SCD;

public class DivideConquer {


    public int binarySearch(int []arr, int s,int e, int val)
    {
        int diff = (e-s)/2;
        int mid = s+diff;

        if(s<=e)
        {
            if(val == arr[mid])
            {
                return mid;
            }
            else if(val < arr[mid]) {
                return binarySearch(arr,s,mid-1,val);
            }
            else
            {
                return binarySearch(arr,mid+1,e,val);
            }
        }

        return -1;
    }

   private void merge(int []arr,int s,int mid,int e)
   {
       int size1 = mid-s+1;
       int size2 = e-mid;
       int[] left = new int[size1];
       int[] right = new int[size2];

       int i = s;
       int j = mid+1;

       int k = 0;

       while(i<=mid)
       {
           left[k++] = arr[i++];
       }

       k=0;

       while(j<=e)
       {
           right[k++] = arr[j++];
       }

       i=0;
       j=0;
       k = s;
       while(i < size1 && j < size2)
       {
          if(left[i] <= right[j])
          {
              arr[k++] = left[i++];
          }
          else{
              arr[k++] = right[j++];
          }
       }

       while(i < size1)
       {
           arr[k++] = left[i++];
       }

       while(j < size2)
       {
           arr[k++] = right[j++];
       }
   }
    public void mergeSort(int []arr,int s,int e)
    {
        if(s<e)
        {
            int mid = s + (e-s)/2;

            mergeSort(arr,s,mid);
            mergeSort(arr,mid+1,e);
            merge(arr,s,mid,e);
        }
    }

    public boolean findPair(int [] arr,int n,int k)
    {

        for(int i = 0; i < n;i++)
        {
            if(binarySearch(arr,0,n-1,arr[i]+k) != -1)
                return true;
        }


        return false;
    }


    public int countElement(int []arr,int s,int e,int k)
    {
        int count = 0;

        if(s<e)
        {
            int mid = (s+e)/2;

            count+=countElement(arr,s,mid,k);
            count+=countElement(arr,mid+1,e,k);

            return count;
        }
        else if(arr[e] == k)
            return 1;
        else
            return 0;
    }

    public int majorElement(int[]arr,int n)
    {
        int i=0;
        int count = 0;
        int majorCount = 0;
        int majorE = -1;


        while(i<n) {
            if (majorE != arr[i])
                count = countElement(arr, 0, n - 1, arr[i]);

            if (count > n / 2 && count > majorCount) {
                majorCount = count;
                majorE = arr[i];
            }
            i++;
        }

        return majorE;

    }

    public int[] findLargest(int []arr,int s,int e)
    {
        int [] largest = new int[2];
        int []x;
        int []y;

        // FIRST INDEX STORES INDEX OF LARGEST ELEMENT
        // SECOND INDEX STORES LARGEST ELEMENT
        if(s<e)
        {
            int mid = (s+e)/2;

            x = findLargest(arr,s,mid);
            y = findLargest(arr,mid+1,e);


           if(x[1] > y[1])
           {
               largest[1] = x[1];
               largest[0] = x[0];
           }
           else if(x[1] < y[1]){
               largest[1] = y[1];
               largest[0] = y[0];
        }

            return largest;

        }

        largest[0] = e+1;
        largest[1] = arr[e];
        return largest;
    }

    public int findKth(int []a,int[]b,int m,int n, int kth)
    {

        int i = 0;
        int j = 0;

        int pos = -1;

        while(i<m && j < n)
        {
            if(a[i] <= b[j])
            {
                pos++;

                if(pos == kth)
                {
                    return a[i];
                }
                else
                    i++;
            }
            else {
                pos++;

                if(pos == kth)
                {
                    return b[j];
                }
                else
                    j++;
            }
        }

        if(i>=m)
        {
            int rem = kth - pos -1;

            return b[j+rem];
        }
        else {
            int rem = kth - pos -1;

            return a[i+rem];
        }

    }


    private float calcMedian(int []arr,int n)
    {
        if(n %2 == 0)
        {

            return (float) (arr[n / 2 - 1] + arr[n / 2]) /2;
        }
        else
        {
            return arr[n/2];
        }
    }
    public float medianFind(int []a,int []b,int n)
    {
        if(n == 0)
            return -1;
        else if(n ==1)
        {
            return ((float) (a[0] + b[0]) /2);
        }
        else if(n ==2)
        {
            return ((float) (Integer.max(a[0], b[0]) + Integer.min(a[1], b[1])) / 2);
        }
        else
        {
            float median1 =  calcMedian(a,n);
            float median2 = calcMedian(b,n);

            if(median1==median2)
            {
                return median1;
            }
            else if(median1 < median2)
            {
                int []subA = new int[n/2];
                System.arraycopy(a,n/2 + 1,subA,0,subA.length-1);
                return medianFind(subA,b,n/2);
            }else
            {
                int []subB = new int[n/2];
                System.arraycopy(b,n/2 + 1,subB,0,subB.length-1);
                return medianFind(a,subB,n/2);
            }
        }
    }
    public static void main(String[] args)
    {
        DivideConquer d = new DivideConquer();

        //int[] array = {48, 92, 35, 48, 92, 35, 48, 35, 48};

        int []arr = {78, 85, 92, 100, 34, 48, 56, 62};

        int []a = {1,2,3,4,5};
        int []b = {7,8,9,10,20};
         //d.mergeSort(array,0,5);
         //System.out.println(d.findPair(array,6,1));

        //System.out.println(d.majorElement(array,9));

        //System.out.println(d.findLargest(arr,0,7)[0] );

        //System.out.println(d.findKth(a,b,3,6,0));

        System.out.println(d.medianFind(a,b,5));
    }

}
