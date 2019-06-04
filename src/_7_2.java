import java.util.Scanner;

public class _7_2 {
	public static void main(String[] args) {
		int day,month,year;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		year=input.nextInt();
		month=input.nextInt();
		day=input.nextInt();
		int h=dayofweek(year,month,day);
		System.out.println(week(h));
	}
	static int dayofweek(int year,int month,int day) {
		int q=day;
		int m;
		if(month==1) {
			m=13;
			year--;
		}else if(month==2){
			m=14;
			year--;
		}else
			m=month;
		int j=year/100;
		int k=year%100;
		int h=(int)(q+26*(m + 1) / 10 + k + k / 4 + j / 4 + 5*j) % 7;
		return h;
	}
	static String week(int h) {
		switch (h) {
		case 0:
			return "Saturday";
		case 1:
			return "Sunday";
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		default:
			break;
		}
		return null;
	}
	
}
