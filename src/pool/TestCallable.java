package pool;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
 
/*
 * һ������ִ���̵߳ķ�ʽ����ʵ�� Callable �ӿڡ� �����ʵ�� Runnable �ӿڵķ�ʽ�����������з���ֵ�����ҿ����׳��쳣��
 *
 * ����ִ�� Callable ��ʽ����Ҫ FutureTask ʵ�����֧�֣����ڽ�����������  FutureTask ��  Future �ӿڵ�ʵ����
 */
public class TestCallable {
 
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
 
        //1.ִ�� Callable ��ʽ����Ҫ FutureTask ʵ�����֧�֣����ڽ�����������
        FutureTask<Integer> result = new FutureTask<>(td);
 
        new Thread(result).start();
 
        //2.�����߳������Ľ��
        try {
            Integer sum = result.get();  //FutureTask ������ ���� ������CountDownLatch�����ã������е��߳�û��ִ�����֮�������ǲ���ִ�е�
            System.out.println(sum);
            System.out.println("------------------------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
 
}
 
class ThreadDemo implements Callable<Integer> {
 
    @Override
    public Integer call() throws Exception {
        int sum = 0;
 
        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }
 
        return sum;
    }
 
}