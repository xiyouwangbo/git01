import java.util.Scanner;

class LinearEquation {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    public LinearEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getX() {
        return (e * d - b * f) / ab_Minus_bc();
    }


    public double getY() {
        return (a * f - e * c) / ab_Minus_bc();
    }


    /** If there is no solution the lines are parallel **/
    public boolean isSolvable(){
        return ab_Minus_bc() != 0;
    }

    private double ab_Minus_bc(){
        return a * d - b * c;
    }

    public static LinearEquation getIntersectingPoint(double x1,double y1,double x2,double y2,
                                                      double x3,double y3,double x4,double y4) {
        double a = (y1 - y2);
        double b = (-x1 + x2);
        double c = (y3 - y4);
        double d = (-x3 + x4);
        double e = -y1 * (x1 - x2) + (y1 - y2) * x1;
        double f = -y3 * (x3 - x4) + (y3 - y4) * x3;
        return new LinearEquation(a,b,c,d,e,f);
    }


    public static LinearEquation getIntersectingPoint(double[][] points) {

        return getIntersectingPoint(points[0][0],points[0][1],points[1][0],points[1][1],
                                    points[2][0],points[2][1],points[3][0],points[3][1]);
    }

}
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] points = new double[4][2];
        for (int i = 0; i < points.length; i++)
            for (int j = 0; j < points[i].length; j++)
                points[i][j] = input.nextDouble();

        LinearEquation linear = LinearEquation.getIntersectingPoint(points);

        if (linear.isSolvable()) {
            System.out.println("The intersecting point is at (" + linear.getX() + ", " + linear.getY() + ")");
        } else {
            System.out.println("The two lines are parallel");
        }
    }
}
