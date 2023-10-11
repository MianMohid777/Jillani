package Lab_2;

public class Rectangle {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double calcArea()
    {
        return (this.length*this.width);
    }

    public double calcPerimeter()
    {
        return ((this.length*2) + (this.width*2));
    }

    public static void main(String []args)
    {
        Rectangle R = new Rectangle(15,10);

        System.out.print(R.calcArea());

        System.out.print("\n");

        System.out.print(R.calcPerimeter());
    }
}
