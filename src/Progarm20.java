import java.util.Scanner;

public class Progarm20 {
	static boolean redOrYellow = true;
	static boolean[] overflow = new boolean[7];
	private static Scanner input;

	public static void main(String[] args) {
		play();
	}

	private static int nPrint() {
		input = new Scanner(System.in);
		System.out.print("Drop a " + (redOrYellow ? "red" : "yellow"));
		System.out.println(" disk at column(0,6):");
		return input.nextInt();
	}

	private static void play() {
		char[][] qi = new char[6][7];
		for (int i = 0; i < qi.length; i++) {
			for (int j = 0; j < qi[i].length; j++) {
				qi[i][j] = '*';
			}
		}

		chessBoard(qi);
		do {
			int n;
			while (true) {
				n = nPrint();
				if (overflow[n])
					System.out.println("你输入的格子已经有字符！重新输入");
				else
					break;
			}
			int[] a = newChessBoard(n, qi);
			chessBoard(qi);
			boolean key1 = Jund(qi, a);
			boolean key2 = heqi(qi);
			if (key1 || key2)
				break;
		} while (true);
	}

	private static boolean heqi(char[][] qi) {
		int sum = 0;
		for (int i = 0; i < qi.length; i++) {
			for (int j = 0; j < qi[i].length; j++) {
				if (qi[i][j] == '*')
					sum++;
			}
		}
		if (sum == 0)
			return true;
		return false;
	}

	private static boolean Jund(char[][] qi, int[] a) {
		char temp = (redOrYellow) ? 'R' : 'Y';
		int Dx[] = { 1, 0, 0, 1, 1 };
		int Dy[] = { 0, 1, -1, 1, -1 };
		int c = 0;
		for (int k = 0; k < 5; k++) {
			for (int i = a[0] + Dx[k], j = a[1] + Dy[k]; i >= 0 && j >= 0 && i < qi.length && j < qi[0].length
					&& temp == qi[i][j]; i += Dx[k], j += Dy[k])
				c++;
			if (c >= 3) {
				System.out.print("The " + temp);
				System.out.println(" is won");
				return true;
			}
		}
		return false;
	}

	private static void chessBoard(char[][] qi) {// 创造数组棋盘
		for (int i = 0; i < qi.length; i++) {
			for (int j = 0; j < qi[i].length; j++) {
				System.out.print(qi[i][j]);
			}
			System.out.println();
		}

	}

	private static int[] newChessBoard(int i, char[][] qi) {
		int a[] = new int[2];
		for (int j = 0; j < qi.length; j++) {
			if (qi[qi.length - 1 - j][i] == '*') {
				qi[qi.length - 1 - j][i] = redOrYellow ? 'R' : 'Y';
				if (qi.length - 1 - j == 0)
					overflow[i] = true;
				redOrYellow = !redOrYellow;
				a[0] = qi.length - 1 - j;
				a[1] = i;
				return a;
			}
		}
		return null;
	}

}
