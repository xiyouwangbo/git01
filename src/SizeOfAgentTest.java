import java.lang.instrument.Instrumentation;  
import java.lang.reflect.Array;  
import java.lang.reflect.Field;  
import java.lang.reflect.Modifier;  
import java.util.*;  
   
/** 
 * ����ռ���ֽڴ�С������ 
 * 
 * @author tianmai.fh 
 * @date 2014-03-18 11:29 
 */ 
class SizeOfObject {  
    static Instrumentation inst;  
   
    public static void premain(String args, Instrumentation instP) {  
        inst = instP;  
    }  
   
    /** 
     * ֱ�Ӽ��㵱ǰ����ռ�ÿռ��С��������ǰ�༰����Ļ�������ʵ���ֶδ�С��</br> 
     * ��������ʵ���ֶ����ô�С��ʵ����������������ռ�ÿռ䡢ʵ�����������������ñ���ռ�ÿռ��С;</br> 
     * ���ǲ���������̳������ĺ͵�ǰ��������ʵ�������ֶεĶ�����Ĵ�С��ʵ�������������õĶ�����Ĵ�С </br> 
     * 
     * @param obj 
     * @return 
     */ 
    public static long sizeOf(Object obj) {  
        return inst.getObjectSize(obj);  
    }  
   
    /** 
     * �ݹ���㵱ǰ����ռ�ÿռ��ܴ�С��������ǰ��ͳ����ʵ���ֶδ�С�Լ�ʵ���ֶ����ö����С 
     * 
     * @param objP 
     * @return 
     * @throws IllegalAccessException 
     */ 
    public static long fullSizeOf(Object objP) throws IllegalAccessException {  
        Set<Object> visited = new HashSet<Object>();  
        Deque<Object> toBeQueue = new ArrayDeque<Object>();  
        toBeQueue.add(objP);  
        long size = 0L;  
        while (toBeQueue.size() > 0) {  
            Object obj = toBeQueue.poll();  
            //sizeOf��ʱ���Ѿ��ƻ������ͺ����õĳ��ȣ���������  
            size += skipObject(visited, obj) ? 0L : sizeOf(obj);  
            Class<?> tmpObjClass = obj.getClass();  
            if (tmpObjClass.isArray()) {  
                //[I , [F �����������ֳ�����2  
                if (tmpObjClass.getName().length() > 2) {  
                    for (int i = 0, len = Array.getLength(obj); i < len; i++) {  
                        Object tmp = Array.get(obj, i);  
                        if (tmp != null) {  
                            //�ǻ���������Ҫ��ȱ��������  
                            toBeQueue.add(Array.get(obj, i));  
                        }  
                    }  
                }  
            } else {  
                while (tmpObjClass != null) {  
                    Field[] fields = tmpObjClass.getDeclaredFields();  
                    for (Field field : fields) {  
                        if (Modifier.isStatic(field.getModifiers())   //��̬����  
                                || field.getType().isPrimitive()) {    //�������Ͳ��ظ���  
                            continue;  
                        }  
   
                        field.setAccessible(true);  
                        Object fieldValue = field.get(obj);  
                        if (fieldValue == null) {  
                            continue;  
                        }  
                        toBeQueue.add(fieldValue);  
                    }  
                    tmpObjClass = tmpObjClass.getSuperclass();  
                }  
            }  
        }  
        return size;  
    }  
   
    /** 
     * String.intern�Ķ��󲻼ƣ�������Ĳ��ƣ�Ҳ������ѭ�� 
     * 
     * @param visited 
     * @param obj 
     * @return 
     */ 
    static boolean skipObject(Set<Object> visited, Object obj) {  
        if (obj instanceof String && obj == ((String) obj).intern()) {  
            return true;  
        }  
        return visited.contains(obj);  
    }  

    public final static class VolatileLong

    {
        public volatile long value = 0L;
        public long p1, p2, p3, p4, p5, p6; // comment out
    }

    public static void main(String[] args) {
         System.out.println(sizeOf(new Integer(2342)));
    }
}

public class SizeOfAgentTest {
 
    public static void main(String[] args)  throws Exception{
        System.out.println("------------------�ն���----------------------------");
        // 16 bytes + 0 + 0 = 16  �ն��� ֻ�ж���ͷ
//        System.out.println("sizeOf(new Object()) = " + SizeOfObject.sizeOf(new Object()));
//        System.out.println("fullSizeOf(new Object()) = " + SizeOfObject.fullSizeOf(new Object()));
 
        System.out.println("----------------�ǿն�����ԭʼ���͡���������------------------------------");
 
        // 16 bytes + 8 + 4 + padding = 32
        System.out.println("sizeOf(new A()) = " + SizeOfObject.sizeOf(new A()));
        System.out.println("fullSizeOf(new A()) = " + SizeOfObject.fullSizeOf(new A()));
 
        // 16 + 4 + padding =24      ������һ�� int
        System.out.println("sizeOf(new Integer(1)) = " + SizeOfObject.sizeOf(new Integer(1)));
 
        // (16 + int hash:4 + int hash32:4 + refer char value[]:8 + padding) = 32
        // ��̬���ԣ�static��������ռ䣬��Ϊ���ж����ǹ���һ��ռ��
        // ��ͬ�汾JDK���� String �ڲ� Field ���ܲ�ͬ�����β���ʹ��JDK1.7
        System.out.println("sizeOf(new String()) = " + SizeOfObject.sizeOf(new String()));
        // (16 + 4 + 4 + 8 + padding) + (24 + 0 + padding) = 56
        System.out.println("fullSizeOf(new String()) = " + SizeOfObject.fullSizeOf(new String()));
        // (16 + 4 + 4 + 8 + padding) = 32
        System.out.println("sizeOf(new String('a')) = " + SizeOfObject.sizeOf(new String("a")));
        // (16 + 4 + 4 + 8 +padding)  +  (24 + 2 + padding) = 64
        System.out.println("fullSizeOf(new String('a')) = " + SizeOfObject.fullSizeOf(new String("a")));
 
        System.out.println("-------------------ԭʼ�����������---------------------------");
 
        // 24 bytes + 0*1 + 0 = 24      ���鳤��Ϊ 0������ֻ�ж���ͷ�ĳ���
        System.out.println("sizeOf(new byte[0]) = " + SizeOfObject.sizeOf(new byte[0]));
        System.out.println("fullSizeOf(new byte[0]) = " + SizeOfObject.fullSizeOf(new byte[0]));
 
        // 24 + 1*1 + padding = 32
        System.out.println("sizeOf(new byte[1]) = " + SizeOfObject.sizeOf(new byte[1]));
        System.out.println("fullSizeOf(new byte[1]) = " + SizeOfObject.fullSizeOf(new byte[1]));
 
        // 24 + 1*2 + padding = 32
        System.out.println("sizeOf(new char[1]) = " + SizeOfObject.sizeOf(new char[1]));
        System.out.println("fullSizeOf(new char[1]) = " + SizeOfObject.fullSizeOf(new char[1]));
 
        // 24 + 9*1 + padding = 40
        System.out.println("sizeOf(new byte[9]) = " + SizeOfObject.sizeOf(new byte[9]));
        System.out.println("fullSizeOf(new byte[9]) = " + SizeOfObject.fullSizeOf(new byte[9]));
 
        System.out.println("--------------------���������������--------------------------");
 
        // 24 bytes + 0*8 + 0  = 24       ���鳤��Ϊ 0
        System.out.println("sizeOf(new Integer[0]) = " + SizeOfObject.sizeOf(new Integer[0]));
        System.out.println("fullSizeOf(new Integer[0]) = " + SizeOfObject.fullSizeOf(new Integer[0]));
 
        // 24 bytes + 1*8 + 0 = 32    ���ö��� 64-bit JVM ռ�� 8 bytes
        System.out.println("sizeOf(new Integer[1]) = " + SizeOfObject.sizeOf(new Integer[1]));
        System.out.println("fullSizeOf(new Integer[1]) = " + SizeOfObject.fullSizeOf(new Integer[1]));
 
        // 24 bytes + 2*8 + padding = 40
        System.out.println("sizeOf(new Integer[1]) = " + SizeOfObject.sizeOf(new Integer[1]));
        System.out.println("fullSizeOf(new Integer[1]) = " +SizeOfObject.fullSizeOf(new Integer[1]));
 
        // 24 + 3*8 + padding = 48
        System.out.println("sizeOf(new Integer[3]) = " + SizeOfObject.sizeOf(new Integer[3]));
        System.out.println("fullSizeOf(new Integer[3]) = " + SizeOfObject.fullSizeOf(new Integer[3]));
 
        System.out.println("-------------------�Զ����������---------------------------");
        // 16 + (4+8) + padding = 32
        System.out.println("sizeOf(new B()) = " + SizeOfObject.sizeOf(new B()));
        System.out.println("fullSizeOf(new B()) = " + SizeOfObject.fullSizeOf(new B()));
 
        // 24 + 0*8 + padding = 24    ���ö��� 64-bit JVM ռ�� 8 bytes,
        // ��Ϊû������ʵ�� new B()���� B���ڲ����ݻ�δռ�ÿռ�
        System.out.println("sizeOf(new B[0]) = " + SizeOfObject.sizeOf(new B[0]));
        System.out.println("fullSizeOf(new B[0]) = " + SizeOfObject.fullSizeOf(new B[0]));
 
        // 24 + 1*8 + padding = 32
        System.out.println("sizeOf(new B[1]) = " + SizeOfObject.sizeOf(new B[1]));
        System.out.println("fullSizeOf(new B[1]) = " + SizeOfObject.fullSizeOf(new B[1]));
 
        // 24 + 2*8 + padding = 40
        System.out.println("sizeOf(new B[2]) = " +SizeOfObject.sizeOf(new B[2]));
        System.out.println("fullSizeOf(new B[2]) = " + SizeOfObject.fullSizeOf(new B[2]));
 
        // 24 + 3*8 + padding = 48
        System.out.println("sizeOf(new B[3]) = " + SizeOfObject.sizeOf(new B[3]));
        System.out.println("fullSizeOf(new B[3]) = " + SizeOfObject.fullSizeOf(new B[3]));
 
        System.out.println("-------------------���϶���---------------------------");
        // 16 + (4+8) + padding = 32  sizeOf ֻ���㵥���ռ�ÿռ��С
        System.out.println("sizeOf(new C()) = " + SizeOfObject.sizeOf(new C()));
 
        // (16 + (4+8) + padding1) + (24 + 2*8 + padding2) + 2*(16 + (4+8) + padding3) = 136
        // �ݹ���㵱ǰ����ռ�ÿռ��ܴ�С��������ǰ��ͳ����ʵ���ֶδ�С�Լ�ʵ���ֶ����ö����С
        System.out.println("fullSizeOf(new C()) = " + SizeOfObject.fullSizeOf(new C()));
 
        System.out.println("-------------------�̳й�ϵ---------------------------");
        // �漰�̳й�ϵ��ʱ����һ��������Ĺ������ȴ�Ÿ����еĳ�Ա�����Ų��������еĳ�Ա, ����ҲҪ���� 8 byte �涨
        // 16 + 1 + padding = 24
 
    }
 
    public static class A {
        int a;
    }
 
    public static class B {
        int a;
        Integer b;
    }
 
    public static class C{
        int c;
        B[] b = new B[2];
 
        // ��ʼ��
        C() {
            for (int i = 0; i < b.length; i++) {
                b[i] = new B();
            }
        }
    }
 
    public static class D {
        byte d1;
    }
 
    public static class E extends D {
        byte e1;
    }
 
}