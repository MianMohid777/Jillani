package SCD;

import java.io.*;

public class IO_Stream {

    public void readFile_ByteStream(String path) throws IOException {
        FileInputStream file = new FileInputStream(path);

        BufferedInputStream buffer = new BufferedInputStream(file);

        int str = buffer.read();

        while(str != -1)
        {
            System.out.print((char) str);

            str = buffer.read();
        }

        buffer.close();
    }

    public void readFile_CharStream(String path) throws IOException {

        FileReader file = new FileReader(path);

        BufferedReader buffer = new BufferedReader(file);

        String str = buffer.readLine();

        while(str != null)
        {
            System.out.print( str);

            str = buffer.readLine();
        }

        buffer.close();
    }


    public static void main(String[] args) throws IOException {

        IO_Stream stream = new IO_Stream();

        stream.readFile_ByteStream("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/SCD/input.txt");

        System.out.print("\n");

        stream.readFile_CharStream("/Users/FullStackMohid/IdeaProjects/myJavaProject/src/SCD/input.txt");

    }
}
