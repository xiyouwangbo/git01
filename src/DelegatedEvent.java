import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;


public class DelegatedEvent {
	//Java中的事件委托机制
	//监听器就是使用了事件委托机制的设计模式实现的,例如swing中的监听器
	//事件委托机制比观察者模式耦合度更低
	//观察者模式代码:略(需要一个被观察者接口)
	//事件委托机制(不需要被观察者接口)
	public static  void test() {
		//定义两个委托者
		class Dog{
			@SuppressWarnings("unused")
			void speak() {
				System.out.println("狗叫");
			}
		}
		class Cat{
			@SuppressWarnings("unused")
			void show(Integer x) {
				for(int i=0;i<x;i++) {
					System.out.println("猫叫第"+(i+1)+"声");
				}
			}
		}
		//声明一个执行者
		EventHandler xiaoming = new EventHandler();
		//声明两个委托者
		Dog dog = new Dog();
		Cat cat = new Cat();
		//狗和猫委托小明执行相关方法
		xiaoming.addEvent(dog, "speak");
		xiaoming.addEvent(cat, "show", 3);
		//小明执行方法
		xiaoming.notifyX();
		
	} 
	public static void main(String[] args) {
		test();
	}
}
//第一步:首先需要一个事件类[描述等下被通知者需要执行的方法]
class Event{
	private Object obj;//执行者将调用该对象的方法
	private String methodName;//方法名字(反射用)
	private Object[] methodParameter;//方法参数
	private Class<?>[] methodType;//方法参数类型
	public Event(Object obj, String methodName, Object...methodParameters) {
		super();
		this.obj = obj;
		this.methodName = methodName;
		this.methodParameter = methodParameters;
		int len = methodParameters.length;
		this.methodType = new Class[len];
		for(int i=0;i<len;i++) {
			methodType[i] = methodParameters[i].getClass();
		}
	}
	//执行该对象的方法
	public void invoke() {
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName, methodType);
			method.invoke(obj, methodParameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//第二步:定义一个事件的处理者(执行者,别的类委托它来处理)
class EventHandler{
	private List<Event> list = new ArrayList<Event>();
	//添加需要委托的事情
	public void addEvent(Object obj, String methodName, Object...methodParameters) {
		list.add(new Event(obj, methodName, methodParameters));
	}
	//循环执行这些事情
	public void notifyX() {
		for (Event event : list) {
			event.invoke();
		}
	}
}