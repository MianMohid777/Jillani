package SCD;

public class Box<T> {

    private T val;

    public void set(T val)
    {
        this.val = val;
    }
    public T get(){
        return this.val;
    }

   public static void main(String[] args)
   {
       Box<Integer> b = new Box<>();
      // Integer num = 10;
       b.set(10);

   }
}
