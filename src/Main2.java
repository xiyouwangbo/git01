import java.math.BigDecimal;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double x1, y1, x2, y2, x3, y3, x4, y4;
		x1 = scanner.nextDouble();
		y1 = scanner.nextDouble();
		x2 = scanner.nextDouble();
		y2 = scanner.nextDouble();
		x3 = scanner.nextDouble();
		y3 = scanner.nextDouble();
		x4 = scanner.nextDouble();
		y4 = scanner.nextDouble();

		double k1 = (y2 - y1) / (x2 - x1);
		double k2 = (y4 - y3) / (x4 - x3);

		if (k1 == k2) {
			System.out.println("The two lines are parallel");
		} else {
			double x = ((x2 - x1) * (x3 - x4) * (y3 - y1) - x3 * (x2 - x1) * (y3 - y4) + x1 * (y2 - y1) * (x3 - x4))
					/ ((y2 - y1) * (x3 - x4) - (x2 - x1) * (y3 - y4));
			double y = ((y2 - y1) * (y3 - y4) * (x3 - x1) - y3 * (y2 - y1) * (x3 - x4) + y1 * (x2 - x1) * (y3 - y4))
					/ ((x2 - x1) * (y3 - y4) - (y2 - y1) * (x3 - x4));

			System.out.print("The intersecting point is at (" + x + ", " + y + ")");
		}
	}

	public static BigDecimal jia(BigDecimal... a) {
		BigDecimal b = new BigDecimal("0");
		for (BigDecimal bigDecimal : a) {
			b = b.add(bigDecimal);
		}
		return b;
	}

	public static BigDecimal jian(BigDecimal a, BigDecimal b) {
		return a.subtract(b);
	}

	public static BigDecimal cheng(BigDecimal... a) {

		BigDecimal b = new BigDecimal("1");
		for (BigDecimal bigDecimal : a) {
			b = b.multiply(bigDecimal);
		}
		return b;
	}

	public static BigDecimal chu(BigDecimal a, BigDecimal b) {
		return a.divide(b, 16, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal getB(Double d) {
		return new BigDecimal(Double.toString(d));
	}
}
