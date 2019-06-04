
import java.util.Scanner;

public class _7_4 {
	public static void main(String[] args) {
		int d[] = null;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String a = input.next();
		char  b[] = a.toCharArray();
		d = new int[10];
		for (int i = 0; i < b.length; i++) {
			String c = b[i]+"";
			 d[i+1] = Integer.parseInt(c);
		}
		int sum = 0;
		for (int i = 1; i < d.length; i++) {
			sum += d[i]*i;
		}

		if(sum%11==10) {
			System.out.print("The ISBN-10 number is ");
			for (int i = 1; i < d.length; i++) {
				System.out.print(d[i]);
				if(i+1==d.length)
					System.out.print("X");
			}
		}
	}
}
