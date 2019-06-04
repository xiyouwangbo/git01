package test;
import java.util.Scanner;
import java.io.File;
import java.lang.String;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
public class Main {
    public  static void main(String[] args){
//        Scanner input=new Scanner(System.in);
//
//        String[] name=new String[5];
//        for(int k=0;k<5;k++)   name[k]="0";
//        String[] telnum=new String[5];
//        for(int k=0;k<5;k++)   telnum[k]="0";
//        String s= "##";
//        int i=0,j;
//        
//        String tmp; 
//        String chaxunnum= "noname";
//
//		while(input.hasNextLine()){
//			 tmp =input.next();
//			if(name[i].equals("noname"))
//				break;
//			
//			name[i] = tmp;
//			telnum[i]=input.next();			
//			
//			i++;
//		}
//        
//        
//
//        String chaxunName=input.next();
//        for(j=0;j<5;j++){
//            if(chaxunName.equalsIgnoreCase(name[j])) {
//                System.out.println(telnum[j]);
//                break;
//            }
//           // else continue;
//        }
//        if(j==i)
//            System.out.println("Not found");
    	
//    	System.out.println(File.separator);
    	
    	 System.out.println("Not found");
    	 
    	 Timeline animation = new Timeline(
    		      new KeyFrame(Duration.millis(100), e -> {
    		    		 System.out.println("Not found");}));
    	 
    	 animation.setCycleCount(Timeline.INDEFINITE);
    	    animation.play(); // Start animation
    	    
    	    System.out.println("Not found");
    }
}