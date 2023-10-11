package SCD;
import java.lang.*;
public class Box2 {

    private Object val;

    public Box2()
    {
        this.val = null;
    }

    public Box2(Object val)
    {
        this.val = val;
    }

    public void set(Object setter)
    {
        this.val = setter;
    }

    public Object get()
    {
        return this.val;
    }

    public static void main(String[] args)
    {

        Box2 b = new Box2('A');

        float num = (float) b.get(); //Casting



    }
}
