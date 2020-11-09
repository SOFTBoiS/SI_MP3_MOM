package dk.dd.cameldemo;

import java.util.Scanner;

public class MyTransformation {

    public String offer(String s){
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println(s);
        System.out.println("==============================================");

        System.out.println("Do you wish to accept the offer?");
        System.out.println("Write \"accept\" to accept the offer, or write \"decline\" to decline");
        String input = sc.next();


        while(!input.equals("accept") && !input.equals("decline")){
            System.out.println("Invalid input.");
            System.out.println("Write \"accept\" to accept the offer, or write \"decline\" to decline");
            input = sc.next();
        }

        if(input.contains("decline")){
            return "declined";
        }
        String reply = "Order confirmation\n" + s;
        return reply;
    }
}
