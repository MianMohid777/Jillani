package Lab_2;

import java.util.Random;


public class Practice {

    public static int[][] rotateMatrix_90(int[][] matrix)
    {
        int[][] rotateMatrix = new int[matrix.length][matrix.length];

        int rows = matrix.length;
        int cols = matrix.length;

        for(int i = 0; i < rows;i++)
        {
            for(int j = 0; j < cols;j++)
            {
                 if(i == 0)
                 {
                     rotateMatrix[j][rows-1] = matrix[i][j];
                 }
                 else if(i == (rows-1))
                 {
                     rotateMatrix[j][0] = matrix[i][j];
                 }
                 else if(j==0 || j == (cols - 1))
                 {
                     rotateMatrix[j][rows-i-1] = matrix[i][j];
                 }
                 else {
                     rotateMatrix[i][j] = matrix[i][j];
                 }
            }
        }

        for(int[] i: rotateMatrix)
        {
            for(int j : i)
            {
                System.out.print(j + " ");
            }

            System.out.print("\n");
        }

        return rotateMatrix;
    }

    public static <T extends Comparable> int binarySearch(T[] arr, T item)
    {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;

        while(left<=right)
        {
            mid = (right + left)/2;

            if(arr[mid] == item)
            {
                return mid;
            }
            else if(arr[mid].compareTo(item) > 0)
            {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void RollDice(char [][] board)
    {
        char dice = 'X';

        int index = 0;
        int curr = 0;
        boolean flag = true;
        int row = 0;
        int diff = 0;
        int itr = 1;

        Random rand = new Random();

        System.out.println("Board");

        for(char[] i:board)
        {
            for(char j : i)
            {
                System.out.print(j);
            }

            System.out.print("\n");
        }

        while(flag)
        {
            index = rand.nextInt(6) + 1;


            if(row < 10)
            {
                if((curr + index ) < 10)
                {
                    board[row][curr + index] = 'X';
                    board[row][curr] = '0';
                    curr = curr + index;
                }
                else if((curr + index) > 9)
                {
                    if(row == 9)
                    {
                        board[row][9] = 'X';
                        board[row][curr] = '0';
                        flag = false;

                    }
                    else {
                        diff = curr + index - 10;
                        board[row + 1][diff] = 'X';
                        board[row][curr] = '0';
                        curr = diff;
                        row++;
                    }
                }

                System.out.println("\nIteration " + itr);

                for(char[] i:board)
                {
                    for(char j : i)
                    {
                        System.out.print(j + " ");
                    }

                    System.out.print("\n");
                }
            }
            else
                flag = false;

            itr++;
        }

    }
    public static void main(String[] args)
    {

        // Question 5 //
        int[][] mat = new int[][]{{1, 2, 3,4}, {5, 6, 7,8}, {9,10, 11, 12},{13,14,15,16}};
        for(int[] i: mat)
        {
            for(int j : i)
            {
                System.out.print(j + " ");
            }

            System.out.print("\n");
        }

        System.out.print("\nAfter Rotation \n \n");

        mat = rotateMatrix_90(mat);

        // Question 4 //

        //Integer[] arr = {1,2,3,4,5,6};
        //String[] str = {"A","B","C","D","E"};

        //System.out.println(binarySearch(arr,5));
        //System.out.println(binarySearch(str,"A"));


        // Question 3 //

        char[][] board = new char[10][10];

        for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            board[i][j] = '0';
        }
    }
        RollDice(board);

    }
}
