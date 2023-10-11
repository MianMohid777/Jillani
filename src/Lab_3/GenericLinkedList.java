package Lab_3;
import static Lab_3.GenericLinkedList.Node.createNode;


public class GenericLinkedList<T> {

    static class Node<T> {

        private T data;
        private Node<T> next;


        public Node() {
            this.data = null;
            this.next = null;
        }

        public static <T> Node createNode(T data) {
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

    private Node head;

    public GenericLinkedList() {
        this.head = null;
    }

    public void addBack(T item) {
        if (head == null) {
            head = createNode(item);
            head.setNext(null);
            return;
        }

        Node<T> current = new Node<>();
        current = head;

        while (current.getNext() != null)
            current = current.getNext();

        current.setNext(createNode(item));
    }

    public void addFront(T item)
    {
        if(head == null)
        {
            head = createNode(item);
            head.setNext(null);
            return;
        }

        Node<T> n = createNode(item);
        n.setNext(head);
        head = n;

    }

    public void delete(T data)
    {
        if(head == null)
            return;

        if(head.getData().equals(data))
        {
            Node<T> temp = head;
            head = head.getNext();
            temp = null;

            return;
        }

        Node<T> curr = head;

        while(curr.getNext().getData() != data && curr.getNext() != null)
        {
            curr = curr.getNext();
        }
        if(curr.getNext().getData() == data)
        {
            Node<T> temp = curr.getNext();
            curr.setNext(curr.getNext().getNext());

            temp = null;
        }

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
        GenericLinkedList<String> myList = new GenericLinkedList<>();

        myList.addBack("A");
        myList.addBack("B");
        myList.addBack("C");
        myList.addFront("X");

        myList.print();

        System.out.println();

        myList.delete("B");
        myList.delete("X");

        myList.print();


    }
}
