
import java.util.ArrayList;

import javax.swing.*;
class AA <T> {
	T t ;
	
	void f(){
		System.out.println(t);
	}
}

class BB<T extends Runnable>
{
	T t;
	void f(){
		t.run();
	}
}
/* ²»ÕýÈ·£¡
class CC<H super JFrame>{
	H h ;
	void f(){
		System.out.println(h);
	}
} */ 

interface I<T>{
	void f(T t);
}

class DD implements I<DD> {
	public void f(DD d) {
		System.out.println(d);
	}
}

class EE{
	public <T> void f(T t) {
		System.out.println(t);
	}
}

class FF {
	public <T extends Runnable> void f(T t) {
		t.run();
	}
}

class HH {
	public <T extends Runnable> void f(ArrayList<T> t) {
		t.get(t.size()-1).run();
	}
}



public class TestGeneric {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AA<String> a = new AA<>();
        a.t = "hello";
        a.f();
        
        Thread t =new Thread() {
        	public void run(){
        		System.out.println("in thread");
        	}
        };
        
        BB<Thread> bb = new BB<>();
        bb.t = t;
        bb.f();
        
        ArrayList<JFrame> list1 = new ArrayList<>();
        ArrayList<? extends JFrame > list2 = new ArrayList<>();
        ArrayList<? super JFrame> list3 = new ArrayList<>();
       
        EE ee = new EE();
        ee.f(new Thread());
        
        ee.<Thread>f(new Thread());
        
	}

}
