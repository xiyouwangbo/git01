abstract class Person {
	public String name;
	public String sex;
	public long data;
}

class Teacher_and_ extends Person {
	private int number;
	private String school;

	Teacher_and_(String name, String sex, long data, int number, String school) {
		this.name = name;
		this.sex = sex;
		this.data = data;
		this.number = number;
		this.school = school;
	}

	public void printInfo() {
		System.out.println(name + " " + sex + " " + data + " " + number + " "
				+ school + " ");
	}
}

class Student extends Person {
	private int number;
	private String school;
	private int grade;

	Student(String name, String sex, long data, int number, String school,
			int grade) {
		this.name = name;
		this.sex = sex;
		this.data = data;
		this.number = number;
		this.school = school;
		this.grade = grade;
	}

	public void printInfo() {
		System.out.println(name + " " + sex + " " + data + " " + number + " "
				+ school + " " + grade + " ");
	}
}

public class Teacher_and_s {
	public static void main(String[] args) {
		Teacher_and_ r = new Teacher_and_("liq", "nv", 20000218, 33, "yd");
		r.printInfo();
		Student s = new Student("lq", "nv", 1998202, 32, "hu", 2);
		s.printInfo();
	}
}