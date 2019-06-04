import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

/*
abstract class A{
	
	int a=9;
     void f() {
    	 System.out.println("in A" +a);
    	 g();
     }
     
     abstract void g();
}

class B extends A{
	int a = 99;
	void g(){
		System.out.println("in B" +a);
	}
}


class C{
	int f(){return 9;}
}


class ACmp extends A implements Comparable<A>{

	@Override
	public int compareTo(A o) {
		// TODO Auto-generated method stub
		
		if(o.a >a) 
			return 1;
		else if(o.a ==a) 
			return 0;
		else
			return -1;
	}

	@Override
	void g() {
		// TODO Auto-generated method stub
		
	}
	
}

class ACmptor implements Comparator<A>
{
	@Override
	public int compare(A o1, A o2) {
		// TODO Auto-generated method stub
		if(o1.a >o2.a) 
			return 1;
		else if(o1.a ==o2.a) 
			return 0;
		else
			return -1;
	}
	
}
class D extends C{
	int f() {
		return  2*super.f();
	}
}


interface I1{
	void f();
}

interface I2{
	void f();
}

interface I3{
	double PI = 3.14;
}

class E implements I1,I2{
	public void f() {
		System.out.println("haha");
	}
}

class F{
	F() {
		Timer t = new Timer();
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("hahah");
			}
			
		}, new Date(), 1000);
	}
}

class G{
	void f(){}
	static void g() {
		System.out.println("haha1");
	}
}

class H extends G{
	// int f(){return 100;} error
	static void g() {
		System.out.println("haha2");
	}
	
}

class K<E> {
	
}

class L extends K<L>{
	
}



public class Test2 {
	public static void main(String[] args){
		//1 
//		A a = new B();
//		a.f();
//		System.out.println(a.a);
		
		//2
//		C c = new D();
//		System.out.println(c.f());
		
		//3
		
//		I2 e = new E();
//		e.f();
//		System.out.println(I3.PI);
		//4
		 //new F();
		 
//		H g = new H();
//		 g.g();
		
//		 System.out.println("1df@164.comm".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));
//		 System.out.println(Pattern.compile("JAVA").matcher("Hello").matches());
		 
		int a[][][] ;
		a = new int[2][][];
		a[0] = new int[2][];
		a[1] = new int[2][];
		a[0][0] = new int[2];
		a[0][1] = new int[2];
		a[0][0][0] = 2;
		
		GregorianCalendar gc = new GregorianCalendar();
		System.out.println(gc);
		gc.add(gc.MONTH, 5);
		System.out.println(gc.get(GregorianCalendar.MONTH)); 
	}
}*/
