import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;



public class RealPathTest {

	/**
	 * ��ȡ��ǰ�����ڵĹ���·��
	 */
	public void fun1() {
		File file = new File(this.getClass().getResource("/").getPath());
		// D:\project\taotaoshop\src\blog-mybatis1\target\test-classes
		System.out.println(file);
	}

	/**
	 * ��ȡ��ǰ��ľ���·��
	 */
	public void fun2() {
		File file = new File(this.getClass().getResource("").getPath());
		// D:\project\taotaoshop\src\blog-mybatis1\target\test-classes\com\ygh\blog\realpath
		System.out.println(file);
	}

	/**
	 * ��ȡ��ǰ�����ڵĹ���·�������ַ����Կ�
	 * 
	 * @throws IOException
	 */

	public void fun3() throws IOException {
		File file = new File("");
		String path = file.getCanonicalPath();
		// D:\project\taotaoshop\src\blog-mybatis1
		System.out.println(path);
		// D:\project\taotaoshop\src\blog-mybatis1
		System.out.println(System.getProperty("user.dir"));
	}

	/**
	 * ��ȡ��ǰsrc������ļ���·��
	 */
	public void fun4() {
		URL url = this.getClass().getClassLoader()
				.getResource("jdbc.properties");
		System.out.println(url);
	}

	/**
	 * ��ȡ����Դ���������ļ�·��
	 */
	public void fun5() {
		// ʹ�����ַ������Ի�ȡ·��
		URL url = this.getClass().getClassLoader().getResource("Test.java");
		// file:/D:/project/taotaoshop/src/blog-mybatis1/target/classes/test.txt
		System.out.println(url);
	}

	public void fun6() throws Exception {
		URL url = this.getClass().getClassLoader().getResource("Test.java");
		System.out.println(url.getPath());
		Properties properties = new Properties();
		// ʹ�����ַ�ʽ���Ի�ȡ�ļ���Ӧ�������
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("jdbc.properties");
		properties.load(inputStream);
		File file = new File(url.getPath());
		System.out.println(properties.get("jdbc.driverClassName"));
	}

	public static void main(String[] args) throws Exception {
//		RealPathTest path = new RealPathTest();
//		path.fun1();
//		path.fun2();
//		path.fun3();
//		path.fun4();
//		path.fun5();
//		path.fun6();
		
		File file =new File("..\\..");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
	}

}