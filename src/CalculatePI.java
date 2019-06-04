
/**
 * @author WangBo
 */

class ThreadsSum extends Thread{  
    int id ;

    long n ;
    // 计算总和
    long sum;
    
    public ThreadsSum(int n) {
        sum = 0;
        this.n = n;
    }
    
    public void run() {
    	for(int i=0;i<n;i++){
    		if(isInCircle()){
    			sum++;
    		}
    	}
    }
 
    public long getSum() {
        return sum;
    }
    
    
    public boolean isInCircle(){
    	double x=Math.random();
    	double y=Math.random();
    	if((x-0.5)*(x-0.5)+(y-0.5)*(y-0.5)<0.25)
    		return true;
    	else
    		return false;
    }
    
}  

public class CalculatePI {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        int numOfThreads = 100;
        
        int n = 10000;
        long sum=0  ;
   
        ThreadsSum[]  threads =  new ThreadsSum[numOfThreads];
        
        for(int i =0;i<numOfThreads ; i++) {
            threads[i] = new ThreadsSum(n);
            threads[i].id = i;
            threads[i].start();
        }
        
        for(int i =0;i<numOfThreads ; i++) {
            threads[i].join();
        }
        
        for(int i=0;i<numOfThreads ;i++) {
            sum = sum +  threads[i].getSum();
        }
        
        double PI = 4*(double)sum/(numOfThreads*n);
       System.out.println(PI);
        
    }  
}
