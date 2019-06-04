import java.util.Scanner;
public class JavaGame {
	int row,column;                       //����
	char Graph[][];                       //��ά����
	int top[];		                      //ģ��ջβָ��
	String C[]={"red","yellow"};          //������ɫ
	int empty;                            //����ʣ��ո�
    public JavaGame(int r,int c){                  //��ʼ����������
    	empty=r*c;
    	row=r;column=c;
    	Graph=new char[r][c];
    	top=new int[c];
    	for(int i=0;i<c;i++)                       //��ʼ������ջ
    		top[i]=-1;
    	for(int i=0;i<r;i++)
    		for(int j=0;j<c;j++)
    			Graph[i][j]=' ';
    }
    public void show(){                            //չʾ����
    	for(int i=row-1;i>=0;i--){
    		System.out.print("|");
    		for(int j=0;j<column;j++)
    			System.out.print(Graph[i][j]+"|");
    		System.out.println();
    	}
    }
    public  int Input(int y,int key){              //����
    	if(y>=column||y<0||top[y]==row-1){
    		System.out.println("out of range");
    		return 1;
    	}
    	Graph[++top[y]][y]=C[key%2].toUpperCase().charAt(0);
    	empty--;
    	return 0;
    }
	public int judge(int x,int y) {                //�ж��Ƿ�����ʤ��
		if (search(Graph[x][y], x, y, 1,  0) + search(Graph[x][y], x, y, -1,  0) == 3|| //ˮƽ̽��
		    search(Graph[x][y], x, y, 1,  1) + search(Graph[x][y], x, y, -1, -1) == 3|| //�Խ�̽��
		    search(Graph[x][y], x, y, 0,  1) + search(Graph[x][y], x, y,  0, -1) == 3|| //��ֱ̽��
		    search(Graph[x][y], x, y, 1, -1) + search(Graph[x][y], x, y, -1,  1) == 3)  //�Խ�̽��
			return 1;
		return 0;                          //δ���˻�ʤ
	}
  	public int search(char k, int a, int b, int z, int f) {    //����̽�⺯��
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
			if(My.Input(tmp,i)!=0)      //����� ִ����һѭ����iû��++��
				continue;
			My.show();
			if(My.judge(My.top[tmp],tmp)==1){	//�ж��Ƿ�����ʤ��
				System.out.println("The "+My.C[i%2]+" player won");
				break;
			}
			if(My.empty==0){					//ƽ��
				System.out.println("Chess draw");
				break;
			}
			i++;
		}
    }
}