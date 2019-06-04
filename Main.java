import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // add by wangbo 2019 4 8
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
       for (int i = 0; i < m; i++) list1.add(input.nextInt());
        for (int i = 0; i < n; i++) list2.add(input.nextInt());
       ArrayList<Integer>  unionList =  union(list1, list2);
       Collections.sort(unionList);
       for(int i=0 ; i <unionList.size();i++) {
        	System.out.print(unionList.get(i) +" ");
        }
    }

    public static ArrayList<Integer> union(ArrayList<Integer> list1, ArrayList<Integer> list2) {

        ArrayList<Integer> temp = new ArrayList<>();
        temp.addAll(list1);
        temp.addAll(list2);
        return temp;
    }
}
