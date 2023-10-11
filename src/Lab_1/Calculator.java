package Lab_1;

import java.util.Scanner;
import java.util.Vector;

public class Calculator {


    private float calc;



    public Calculator(float calc) {
        this.calc = calc;
    }


    public void takeInput()
    {
        Scanner in = new Scanner(System.in);
        int temp = 0;
        int num = 0;

        boolean flag = true;
        String op = "";
        String op2 = "";

        while(flag) {

            System.out.println("Enter Num => ");
            temp = in.nextInt();
            num = temp;

            op = "";

            System.out.println("Choose Operation ( +, - , * , / ) or Press 'k' to Exit");
            op = in.next();

            if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
                System.out.println("Enter Num => ");
                temp = in.nextInt();
            }
            else if(op.equals("k")){
                flag = false;
                return;
            }

            System.out.println(" Press 'c' to Calculate or Press 'k' to Exit");
            op2 = in.next();

             if(op2.equals("c"))
              {
                if (op.equals("+"))
                {
                    calc = temp + num;
                    System.out.println(calc);
                }
                else if (op.equals("-"))
                {
                    calc = temp - num;
                    System.out.println(calc);
                }
                else if (op.equals("'"))
                {
                    calc = temp * num;
                    System.out.println(calc);
                }
                else if (op.equals("/"))
                {
                    calc = temp / num;
                    System.out.println(calc);
                }
            }
            else if(op.equals("k")){
                flag = false;
            }

        }


    }

}