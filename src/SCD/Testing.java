package SCD;

import java.util.*;
import java.util.LinkedList;

public class Testing {

    public static <T> void myCode(T val)
    {

        System.out.println("So the DataType is = " + val );

    }

    public static void myCode()
    {
        System.out.println("Adventuring Guy =>  \"Mohid Jillani\"");

    }


    public static void main(String[] args)
    {
        myCode("String");
        myCode("1");
        myCode("9.9");

        Testing.myCode();

        //Scanner input = new Scanner(System.in);

        //String val = input.nextLine();

        for(int i =0 ;i<5;i++) {
            if (i%2 == 0)
                if (Boolean.TRUE) {
                    System.out.println("Nested If");
                }
            if (Boolean.TRUE) {
                System.out.println("!Nested if");
            }

        }

    }
}
