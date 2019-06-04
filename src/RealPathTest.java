import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;



public class RealPathTest {

	/**
	 * 获取当前类所在的工程路径
	 */
	public void fun1() {
		File file = new File(this.getClass().getResource("/").getPath());
		// D:\project\taotaoshop\src\blog-mybatis1\target\test-classes
		System.out.println(file);
	}

	/**
	 * 获取当前类的绝对路径
	 */
	public void fun2() {
		File file = new File(this.getClass().getResource("").getPath());
		// D:\project\taotaoshop\src\blog-mybatis1\target\test-classes\com\ygh\blog\realpath
		System.out.println(file);
	}

	/**
	 * 获取当前类所在的工程路径，两种方法皆可
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
	 * 获取当前src下面的文件的路径
	 */
	public void fun4() {
		URL url = this.getClass().getClassLoader()
				.getResource("jdbc.properties");
		System.out.println(url);
	}

	/**
	 * 获取其他源码包下面的文件路径
	 */
	public void fun5() {
		// 使用这种方法可以获取路径
		URL url = this.getClass().getClassLoader().getResource("Test.java");
		// file:/D:/project/taotaoshop/src/blog-mybatis1/target/classes/test.txt
		System.out.println(url);
	}

	public void fun6() throws Exception {
		URL url = this.getClass().getClassLoader().getResource("Test.java");
		System.out.println(url.getPath());
		Properties properties = new Properties();
		// 使用这种方式可以获取文件对应的输出流
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