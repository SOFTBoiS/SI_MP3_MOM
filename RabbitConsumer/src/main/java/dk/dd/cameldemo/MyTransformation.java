package dk.dd.cameldemo;

import java.util.Scanner;

public class MyTransformation {

    public String offer(String s){
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(s);
        if(input.contains("pass")){
            return "pass";
        }
        String reply = "Order confirmation\n" + s;
        return reply;
    }
}
