package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test1 {

    public static void main(String[] args) {
        File file = new File("SampleFile.txt");
        String text = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
           String str;
            while ((str = br.readLine()) != null) {
                text = text.concat(str);
            }

        }
        catch (IOException e){
            System.out.println("Not file");
        }


        String[] tx = text.split("\\;|\\.|\\,|\\/|\\-|\\:|\\s");


        for (String t : tx){
            if(!t.isEmpty())
                System.out.println(t);
        }
    }
}
