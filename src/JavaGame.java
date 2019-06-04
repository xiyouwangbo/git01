import java.util.Scanner;
public class JavaGame {
	int row,column;                       //行列
	char Graph[][];                       //二维棋盘
	int top[];		                      //模拟栈尾指针
	String C[]={"red","yellow"};          //棋子颜色
	int empty;                            //棋盘剩余空格
    public JavaGame(int r,int c){                  //初始化棋盘数据
    	empty=r*c;
    	row=r;column=c;
    	Graph=new char[r][c];
    	top=new int[c];
    	for(int i=0;i<c;i++)                       //初始化数组栈
    		top[i]=-1;
    	for(int i=0;i<r;i++)
    		for(int j=0;j<c;j++)
    			Graph[i][j]=' ';
    }
    public void show(){                            //展示棋盘
    	for(int i=row-1;i>=0;i--){
    		System.out.print("|");
    		for(int j=0;j<column;j++)
    			System.out.print(Graph[i][j]+"|");
    		System.out.println();
    	}
    }
    public  int Input(int y,int key){              //下棋
    	if(y>=column||y<0||top[y]==row-1){
    		System.out.println("out of range");
    		return 1;
    	}
    	Graph[++top[y]][y]=C[key%2].toUpperCase().charAt(0);
    	empty--;
    	return 0;
    }
	public int judge(int x,int y) {                //判断是否有人胜出
		if (search(Graph[x][y], x, y, 1,  0) + search(Graph[x][y], x, y, -1,  0) == 3|| //水平探测
		    search(Graph[x][y], x, y, 1,  1) + search(Graph[x][y], x, y, -1, -1) == 3|| //对角探测
		    search(Graph[x][y], x, y, 0,  1) + search(Graph[x][y], x, y,  0, -1) == 3|| //垂直探测
		    search(Graph[x][y], x, y, 1, -1) + search(Graph[x][y], x, y, -1,  1) == 3)  //对角探测
			return 1;
		return 0;                          //未有人获胜
	}
  	public int search(char k, int a, int b, int z, int f) {    //单向探测函数
		a += z;	b += f;
		if (b >= column || a >= row || a < 0 || b < 0||Graph[a][b] != k)
			return 0;
		return search(k, a , b , z, f)+1;
	}
    public static void main(String[] args) {
		JavaGame My=new JavaGame(6,7);
		Scanner input=new Scanner(System.in);
		int tmp,key;
		My.show();
		for(int i=0;;){
			System.out.print("Drop a "+My.C[i%2]+" disk at column(0-6) : ");
			tmp=input.nextInt();
			if(My.Input(tmp,i)!=0)      //出错后 执行下一循环（i没有++）
				continue;
			My.show();
			if(My.judge(My.top[tmp],tmp)==1){	//判断是否有人胜出
				System.out.println("The "+My.C[i%2]+" player won");
				break;
			}
			if(My.empty==0){					//平局
				System.out.println("Chess draw");
				break;
			}
			i++;
		}
    }
}