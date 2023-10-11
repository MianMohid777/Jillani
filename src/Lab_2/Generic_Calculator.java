package Lab_2;

public class Generic_Calculator <T> {

    private T item1;
    private T item2;

    public T Add(T x,T y)
    {
        if(x instanceof Integer)
        {
            return (T) Integer.valueOf(((Integer) x).intValue() + ((Integer) y).intValue());

        }
        else if(x instanceof Float)
        {
            return (T) Float.valueOf(((Float) x).floatValue() + ((Float) y).floatValue());
        }
        else if(x instanceof Long)
        {
            return (T) Long.valueOf(((Long) x).longValue() + ((Long) y).longValue());

        }
        else if(x instanceof Double)
        {
            return (T) Double.valueOf(((Double) x).doubleValue() + ((Double) y).doubleValue());

        }
        else if(x instanceof String)
        {
            return (T) String.valueOf(x).concat(String.valueOf(y));
        }


        return null;
    }

    public T Add(T[] arr)
    {

        if (arr[0] instanceof Integer) {

            Integer sum = 0;

            for (int i = 0; i < arr.length; i++) {

                sum = sum + (Integer) arr[i];
            }

            return (T) (sum);
        }
       else if (arr[0] instanceof Double) {

            Double sum = 0.00;

            for (int i = 0; i < arr.length; i++) {

                sum = sum + (Double) arr[i];
            }

            return (T) (sum);
        }
       else if (arr[0] instanceof Float) {

            Float sum = 0.0F;

            for (int i = 0; i < arr.length; i++) {

                sum = sum + (Float) arr[i];
            }

            return (T) (sum);
        }
       else if(arr[0] instanceof Long) {

            Long sum = (long) 0;

            for (int i = 0; i < arr.length; i++) {

                sum = sum + (long) arr[i];
            }

            return (T) (sum);
        }
       else if(arr[0] instanceof String) {

        String sum = "";

        for (int i = 0; i < arr.length; i++) {

            sum = sum + ((String) arr[i]);
        }

        return (T) (sum);
    }
        return null;
    }

    public T Subtract(T x,T y)
    {
        if(x instanceof Integer)
        {
            return (T) Integer.valueOf(((Integer) x).intValue() - ((Integer) y).intValue());

        }
        else if(x instanceof Float)
        {
            return (T) Float.valueOf(((Float) x).floatValue() - ((Float) y).floatValue());
        }
        else if(x instanceof Long)
        {
            return (T) Long.valueOf(((Long) x).longValue() - ((Long) y).longValue());

        }
        else if(x instanceof Double)
        {
            return (T) Double.valueOf(((Double) x).doubleValue() - ((Double) y).doubleValue());

        }


        return null;
    }

    public T Subtract(T[] arr)
    {

        if (arr[0] instanceof Integer) {

            Integer sum = 0;

            for (int i = 0; i < arr.length; i++) {

                sum = sum - (Integer) arr[i];
            }

            return (T) (sum);
        }
        else if (arr[0] instanceof Double) {

            Double sum = 0.00;

            for (int i = 0; i < arr.length; i++) {

                sum = sum - (Double) arr[i];
            }

            return (T) (sum);
        }
        else if (arr[0] instanceof Float) {

            Float sum = 0.0F;

            for (int i = 0; i < arr.length; i++) {

                sum = sum - (Float) arr[i];
            }

            return (T) (sum);
        }
        else if(arr[0] instanceof Long) {

            Long sum = (long) 0;

            for (int i = 0; i < arr.length; i++) {

                sum = sum - (long) arr[i];
            }

            return (T) (sum);
        }

        return null;
    }
    public T Multiply(T x,T y)
    {
        if(x instanceof Integer)
        {
            return (T) Integer.valueOf(((Integer) x).intValue() * ((Integer) y).intValue());

        }
        else if(x instanceof Float)
        {
            return (T) Float.valueOf(((Float) x).floatValue() * ((Float) y).floatValue());
        }
        else if(x instanceof Long)
        {
            return (T) Long.valueOf(((Long) x).longValue() * ((Long) y).longValue());

        }
        else if(x instanceof Double)
        {
            return (T) Double.valueOf(((Double) x).doubleValue() * ((Double) y).doubleValue());

        }

        return null;
    }

    public T Multiply(T[] arr)
    {

        if (arr[0] instanceof Integer) {

            Integer sum = 1;

            for (int i = 0; i < arr.length; i++) {

                sum = sum * (Integer) arr[i];
            }

            return (T) (sum);
        }
        else if (arr[0] instanceof Double) {

            Double sum = 1.00;

            for (int i = 0; i < arr.length; i++) {

                sum = sum * (Double) arr[i];
            }

            return (T) (sum);
        }
        else if (arr[0] instanceof Float) {

            Float sum = 1.0F;

            for (int i = 0; i < arr.length; i++) {

                sum = sum * (Float) arr[i];
            }

            return (T) (sum);
        }
        else if(arr[0] instanceof Long) {

            Long sum = (long) 1;

            for (int i = 0; i < arr.length; i++) {

                sum = sum * (long) arr[i];
            }

            return (T) (sum);
        }

        return null;
    }

    public T Divide(T x,T y)
    {
        if(x instanceof Integer)
        {
            return (T) Integer.valueOf(((Integer) x).intValue() / ((Integer) y).intValue());

        }
        else if(x instanceof Float)
        {
            return (T) Float.valueOf(((Float) x).floatValue() / ((Float) y).floatValue());
        }
        else if(x instanceof Long)
        {
            return (T) Long.valueOf(((Long) x).longValue() / ((Long) y).longValue());

        }
        else if(x instanceof Double)
        {
            return (T) Double.valueOf(((Double) x).doubleValue() / ((Double) y).doubleValue());

        }
        return null;
    }


    public T Divide(T[] arr)
    {

        if (arr[0] instanceof Integer) {

            Integer sum = 1;

            for (int i = 0; i < arr.length; i++) {

                sum = sum / (Integer) arr[i];
            }

            return (T) (sum);
        }
        else if (arr[0] instanceof Double) {

            Double sum = 1.00;

            for (int i = 0; i < arr.length; i++) {

                sum = sum / (Double) arr[i];
            }

            return (T) (sum);
        }
        else if (arr[0] instanceof Float) {

            Float sum = 1.0F;

            for (int i = 0; i < arr.length; i++) {

                sum = sum / (Float) arr[i];
            }

            return (T) (sum);
        }
        else if(arr[0] instanceof Long) {

            Long sum = (long) 1;

            for (int i = 0; i < arr.length; i++) {

                sum = sum / (long) arr[i];
            }

            return (T) (sum);
        }

        return null;
    }


    public static void main(String []args)
    {
        Generic_Calculator<String> calc = new Generic_Calculator<>();

        Generic_Calculator<String> calcArr = new Generic_Calculator<>();
        Generic_Calculator<Float> calcArr1 = new Generic_Calculator<>();



        System.out.println(calc.Add("A","B"));

        String[] str = {"A","B","C"};

        System.out.println(calcArr.Add(str));

        Float[] f = {1.2F,2.5F,6.5F};

        System.out.println(calcArr1.Add(f));
        System.out.println(calcArr1.Multiply(f));



    }
}
