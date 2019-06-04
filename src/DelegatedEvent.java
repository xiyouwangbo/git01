import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;


public class DelegatedEvent {
	//Java�е��¼�ί�л���
	//����������ʹ�����¼�ί�л��Ƶ����ģʽʵ�ֵ�,����swing�еļ�����
	//�¼�ί�л��Ʊȹ۲���ģʽ��϶ȸ���
	//�۲���ģʽ����:��(��Ҫһ�����۲��߽ӿ�)
	//�¼�ί�л���(����Ҫ���۲��߽ӿ�)
	public static  void test() {
		//��������ί����
		class Dog{
			@SuppressWarnings("unused")
			void speak() {
				System.out.println("����");
			}
		}
		class Cat{
			@SuppressWarnings("unused")
			void show(Integer x) {
				for(int i=0;i<x;i++) {
					System.out.println("è�е�"+(i+1)+"��");
				}
			}
		}
		//����һ��ִ����
		EventHandler xiaoming = new EventHandler();
		//��������ί����
		Dog dog = new Dog();
		Cat cat = new Cat();
		//����èί��С��ִ����ط���
		xiaoming.addEvent(dog, "speak");
		xiaoming.addEvent(cat, "show", 3);
		//С��ִ�з���
		xiaoming.notifyX();
		
	} 
	public static void main(String[] args) {
		test();
	}
}
//��һ��:������Ҫһ���¼���[�������±�֪ͨ����Ҫִ�еķ���]
class Event{
	private Object obj;//ִ���߽����øö���ķ���
	private String methodName;//��������(������)
	private Object[] methodParameter;//��������
	private Class<?>[] methodType;//������������
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
	//ִ�иö���ķ���
	public void invoke() {
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName, methodType);
			method.invoke(obj, methodParameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//�ڶ���:����һ���¼��Ĵ�����(ִ����,�����ί����������)
class EventHandler{
	private List<Event> list = new ArrayList<Event>();
	//�����Ҫί�е�����
	public void addEvent(Object obj, String methodName, Object...methodParameters) {
		list.add(new Event(obj, methodName, methodParameters));
	}
	//ѭ��ִ����Щ����
	public void notifyX() {
		for (Event event : list) {
			event.invoke();
		}
	}
}