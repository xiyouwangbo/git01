import java.lang.instrument.Instrumentation;  
import java.lang.reflect.Array;  
import java.lang.reflect.Field;  
import java.lang.reflect.Modifier;  
import java.util.*;  
   
/** 
 * 对象占用字节大小工具类 
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
     * 直接计算当前对象占用空间大小，包括当前类及超类的基本类型实例字段大小、</br> 
     * 引用类型实例字段引用大小、实例基本类型数组总占用空间、实例引用类型数组引用本身占用空间大小;</br> 
     * 但是不包括超类继承下来的和当前类声明的实例引用字段的对象本身的大小、实例引用数组引用的对象本身的大小 </br> 
     * 
     * @param obj 
     * @return 
     */ 
    public static long sizeOf(Object obj) {  
        return inst.getObjectSize(obj);  
    }  
   
    /** 
     * 递归计算当前对象占用空间总大小，包括当前类和超类的实例字段大小以及实例字段引用对象大小 
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
            //sizeOf的时候已经计基本类型和引用的长度，包括数组  
            size += skipObject(visited, obj) ? 0L : sizeOf(obj);  
            Class<?> tmpObjClass = obj.getClass();  
            if (tmpObjClass.isArray()) {  
                //[I , [F 基本类型名字长度是2  
                if (tmpObjClass.getName().length() > 2) {  
                    for (int i = 0, len = Array.getLength(obj); i < len; i++) {  
                        Object tmp = Array.get(obj, i);  
                        if (tmp != null) {  
                            //非基本类型需要深度遍历其对象  
                            toBeQueue.add(Array.get(obj, i));  
                        }  
                    }  
                }  
            } else {  
                while (tmpObjClass != null) {  
                    Field[] fields = tmpObjClass.getDeclaredFields();  
                    for (Field field : fields) {  
                        if (Modifier.isStatic(field.getModifiers())   //静态不计  
                                || field.getType().isPrimitive()) {    //基本类型不重复计  
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
     * String.intern的对象不计；计算过的不计，也避免死循环 
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
        System.out.println("------------------空对象----------------------------");
        // 16 bytes + 0 + 0 = 16  空对象， 只有对象头
//        System.out.println("sizeOf(new Object()) = " + SizeOfObject.sizeOf(new Object()));
//        System.out.println("fullSizeOf(new Object()) = " + SizeOfObject.fullSizeOf(new Object()));
 
        System.out.println("----------------非空对象含有原始类型、引用类型------------------------------");
 
        // 16 bytes + 8 + 4 + padding = 32
        System.out.println("sizeOf(new A()) = " + SizeOfObject.sizeOf(new A()));
        System.out.println("fullSizeOf(new A()) = " + SizeOfObject.fullSizeOf(new A()));
 
        // 16 + 4 + padding =24      数据是一个 int
        System.out.println("sizeOf(new Integer(1)) = " + SizeOfObject.sizeOf(new Integer(1)));
 
        // (16 + int hash:4 + int hash32:4 + refer char value[]:8 + padding) = 32
        // 静态属性（static）不计算空间，因为所有对象都是共享一块空间的
        // 不同版本JDK可能 String 内部 Field 可能不同，本次测试使用JDK1.7
        System.out.println("sizeOf(new String()) = " + SizeOfObject.sizeOf(new String()));
        // (16 + 4 + 4 + 8 + padding) + (24 + 0 + padding) = 56
        System.out.println("fullSizeOf(new String()) = " + SizeOfObject.fullSizeOf(new String()));
        // (16 + 4 + 4 + 8 + padding) = 32
        System.out.println("sizeOf(new String('a')) = " + SizeOfObject.sizeOf(new String("a")));
        // (16 + 4 + 4 + 8 +padding)  +  (24 + 2 + padding) = 64
        System.out.println("fullSizeOf(new String('a')) = " + SizeOfObject.fullSizeOf(new String("a")));
 
        System.out.println("-------------------原始类型数组对象---------------------------");
 
        // 24 bytes + 0*1 + 0 = 24      数组长度为 0，所以只有对象头的长度
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
 
        System.out.println("--------------------引用类型数组对象--------------------------");
 
        // 24 bytes + 0*8 + 0  = 24       数组长度为 0
        System.out.println("sizeOf(new Integer[0]) = " + SizeOfObject.sizeOf(new Integer[0]));
        System.out.println("fullSizeOf(new Integer[0]) = " + SizeOfObject.fullSizeOf(new Integer[0]));
 
        // 24 bytes + 1*8 + 0 = 32    引用对象 64-bit JVM 占用 8 bytes
        System.out.println("sizeOf(new Integer[1]) = " + SizeOfObject.sizeOf(new Integer[1]));
        System.out.println("fullSizeOf(new Integer[1]) = " + SizeOfObject.fullSizeOf(new Integer[1]));
 
        // 24 bytes + 2*8 + padding = 40
        System.out.println("sizeOf(new Integer[1]) = " + SizeOfObject.sizeOf(new Integer[1]));
        System.out.println("fullSizeOf(new Integer[1]) = " +SizeOfObject.fullSizeOf(new Integer[1]));
 
        // 24 + 3*8 + padding = 48
        System.out.println("sizeOf(new Integer[3]) = " + SizeOfObject.sizeOf(new Integer[3]));
        System.out.println("fullSizeOf(new Integer[3]) = " + SizeOfObject.fullSizeOf(new Integer[3]));
 
        System.out.println("-------------------自定义数组对象---------------------------");
        // 16 + (4+8) + padding = 32
        System.out.println("sizeOf(new B()) = " + SizeOfObject.sizeOf(new B()));
        System.out.println("fullSizeOf(new B()) = " + SizeOfObject.fullSizeOf(new B()));
 
        // 24 + 0*8 + padding = 24    引用对象 64-bit JVM 占用 8 bytes,
        // 因为没创建真实的 new B()所以 B类内部数据还未占用空间
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
 
        System.out.println("-------------------复合对象---------------------------");
        // 16 + (4+8) + padding = 32  sizeOf 只计算单层次占用空间大小
        System.out.println("sizeOf(new C()) = " + SizeOfObject.sizeOf(new C()));
 
        // (16 + (4+8) + padding1) + (24 + 2*8 + padding2) + 2*(16 + (4+8) + padding3) = 136
        // 递归计算当前对象占用空间总大小，包括当前类和超类的实例字段大小以及实例字段引用对象大小
        System.out.println("fullSizeOf(new C()) = " + SizeOfObject.fullSizeOf(new C()));
 
        System.out.println("-------------------继承关系---------------------------");
        // 涉及继承关系的时候有一个最基本的规则：首先存放父类中的成员，接着才是子类中的成员, 父类也要按照 8 byte 规定
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
 
        // 初始化
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