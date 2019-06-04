public class ExceptionTest {
 
    public void getName(String cityName) throws RuntimeException {
        try {
            getSchoolName(cityName);
        } catch (StringIndexOutOfBoundsException e) {
            throw new RuntimeException("���������쳣",e);
        	 //System.out.println("getname XXX");
        }
        
        System.out.println("get Name");
    }
 
    public void getSchoolName(String schoolName) throws StringIndexOutOfBoundsException{
        if (schoolName.equals("**��ѧ��")){
            throw new StringIndexOutOfBoundsException("��ѧ�����쳣");
        }
        
        System.out.println("get school Name");
    }
 
    public static void main(String[] args)  {
        String name = "**��ѧ��";
 
        ExceptionTest exceptionTest = new ExceptionTest();
 
        try {
            exceptionTest.getName(name);
            System.out.println("main %%%%%%%%%%%%%%");
        }
        catch (StringIndexOutOfBoundsException e2) {
            e2.printStackTrace();
            System.out.println("mainXXX");
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            
            System.out.println("main ----------");
        }
        
        
        System.out.println("main");
    }
 
}