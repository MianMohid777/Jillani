import java.util.*;
import Lab_1.Calculator;



public class Main {

    public static void createTenTable()
    {

        Scanner in = new Scanner(System.in);

        int num = 0;

        System.out.println("Enter Number => ");

        num = in.nextInt();

        for(int i = 1; i < 11; i++)
        {
            System.out.println(num + "x" + i + "=" + (num*i)  );
        }
    }


    public static int[] rotateArray(int[] arr,int pos)
    {
        int[] rotateArr = new int[arr.length];

        for(int i = 0; i < arr.length;i++)
        {

          if(i+pos < arr.length){
              rotateArr[i+pos] = arr[i];
          }
          else{
              int temp = (i+pos) - arr.length;
              rotateArr[temp] = arr[i];
          }
        }
        return rotateArr;
    }

    public static int sumInt() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Number (range in 0-1000) => ");
        int num = in.nextInt();

        boolean flag = false;
        while(!flag) {
            if (num < 0 || num > 1000) {
                System.out.println("Invalid Input");
            }
            else
            {flag = true;}
        }

        int sum = 0;
        while(num > 0)
        {
            sum = sum + (num %10);
            num = num /10;
        }
        return sum;
    }

    public static void transpose(int[][] arr)
    {

         int[][] transpose = new int[arr[0].length][arr.length];

       for(int i = 0; i< arr.length;i++)
       {
           for(int j = 0; j < arr[i].length;j++)
           {
               transpose[j][i] = arr[i][j];
           }
       }

        for(int i = 0; i< transpose.length;i++)
        {
            for(int j = 0; j < transpose[i].length;j++)
            {
                System.out.print(transpose[i][j]);
            }
            System.out.print("\n");
        }



    }
    public static int oddSum(int[] arr )
    {
        int sum = 0;
        for(int i = 0 ; i < arr.length;i++)
        {
            if(arr[i] % 2 != 0)
            {
                sum+=arr[i];
            }
        }
        return sum;
    }

    public static boolean isPalindrome(int[] arr)
    {
        for(int i = 0,j = arr.length - 1 ; i < arr.length / 2; i++,j--)
        {
            if(arr[i] != arr[j])
            {return false;
            };
        }
        return true;
    }
    public static void welcomeToFast()
    {
        for(int i= 1; i <= 40;i++)
        {
            if (i >=1 && i<=10)
            {
                if(i == 1 || i == 5)
                {
                    System.out.println("***********");
                }
                else
                {
                    System.out.print("*");
                    System.out.println();
                    if(i == 10)
                    {
                        System.out.println();
                        System.out.println();
                    }
                }
            }
            else if ((i >=11 && i<=20))
            {
                if(i == 11 || i == 15)
                {
                    System.out.println("***********");
                }
                else
                {
                    System.out.println("*        *");
                    if(i == 20)
                    {
                        System.out.println();
                        System.out.println();
                    }
                }

            }
            else if ((i >=21 && i<=30))
            {
                if(i == 21 || i == 25 || i == 30)
                {

                    System.out.println("***********");

                    if(i == 30)
                    {
                        System.out.println();
                        System.out.println();
                    }
                }
                else
                {
                    if(i < 25 )
                    {
                        System.out.println("*          ");
                    }
                    else
                    {
                        System.out.println("         *");
                    }

                }

            }
            else
            {
                if(i == 31 )
                {
                    System.out.println("***********");

                }
                else
                {

                    System.out.println("    *    ");

                }
            }
        }
    };



    public static void main(String[] args) {


        createTenTable(); //Question 1

        //Question 2

        int[] arr = {1,2,3,4,5};


        arr = rotateArray(arr,3);

        for(int a:arr)
        {
            System.out.print(a + " ");
        }


        //Question 3
        int[][] arrD = {{1,2,3}, {4,5,6},{7,8,9}};
        transpose(arrD);

        //Question 4
        System.out.println(sumInt());

        //Question 5
        welcomeToFast();
        
        //Question 6
        int[] arr2 = {4,5,6,7,8};
        System.out.println(oddSum(arr2));

        //Question 7
        Calculator c = new Calculator(0);
        c.takeInput();

        //Question 8
        System.out.println(isPalindrome(arr));


    }
}