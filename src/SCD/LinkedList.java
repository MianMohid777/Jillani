package SCD;
import static SCD.LinkedList.Node.createNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>{



    public static class Node <T> {

        private T data;
        private Node<T> next;


        public Node() {
            this.data = null;
            this.next = null;
        }

        public static <T> Node createNode(T data)
        {
            Node<T> n = new Node<>();

            n.data = data;
            n.next = null;

            return n;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new List_Iterator<>();
    }
    private class List_Iterator <T> implements Iterator<T> {


       private Node<T> curr;

        public List_Iterator() {
            this.curr = head;
        }

        public boolean hasNext()
       {
           if(curr != null)
              return true;

           return false;
       }

       public T next()
       {
           if(!hasNext())
           {
               throw new NoSuchElementException();
           }
           T currVal = curr.getData();

           curr = curr.getNext();

           return currVal;
       }

    }
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void add(T item)
    {
       if(head == null)
       {
           head = createNode(item);
           head.setNext(null);
           return;
       }

        Node<T> current = new Node<>();
        current = head;

        while(current.getNext() != null)
            current = current.getNext();

        current.setNext(createNode(item));
    }

    public void print()
    {
        Node<T> current = new Node<>();
        current = head;

        while(current != null)
        {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public static void main(String[] args)
    {
        LinkedList<String> myList = new LinkedList<>();

        myList.add("A");
        myList.add("B");
        myList.add("C");

        //myList.print();

        for(String item : myList)
        {
            System.out.println(item);
        }


    }
}
