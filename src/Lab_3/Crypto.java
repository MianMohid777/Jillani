package Lab_3;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Crypto {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }




    public void encrypt() {

        char[] arr = str.toCharArray();
        String s = "";

        for (int i = 0; i < str.length(); i++) {

            if(arr[i] != ' ') {
                int newAscii =  arr[i] + (5*2+3);
                arr[i] = (char) newAscii;
                s = s + arr[i];
            }
            else
                s = s + arr[i];

        }
        setStr(s);
    }
    public void decrypt() {
        char[] arr = str.toCharArray();

        String s = "";
        for (int i = 0; i < str.length(); i++) {

            if((arr[i] != ' ')) {
                int newAscii =  arr[i] - (5*2+3);
                arr[i] = (char) newAscii;
                s = s + arr[i];
            }
            else
                s = s + arr[i];

        }
        setStr(s);
    }

    public static void main(String[] args) throws IOException {
        Crypto c = new Crypto();

        Scanner in = new Scanner(System.in);

        System.out.print("Enter A String to Encrypt = ");
        c.setStr(in.nextLine());

        c.encrypt();

        FileWriter file = new FileWriter("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/Lab_3/output.txt");
        file.write(c.getStr());

        file.close();
        System.out.println(c.getStr());


        c.decrypt();
        System.out.println(c.getStr());
    }
}
