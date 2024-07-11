package SCD;

public class BST<T extends Comparable<T>> {

    private static class Node <T> {

        private T data;
        private Node<T> left;
        private Node<T> right;



        public Node() {
            this.data = null;
            this.left = null;
            this.right= null;
        }

    }

    private Node<T> root;


    public BST() {
        this.root = null;
    }

    private Node<T> add(Node<T > rootNode,T data)
    {
       if(rootNode == null)
       {
           Node<T> k = new Node<>();
           k.data = data;
           return k;
       }
       else if(data.compareTo(rootNode.data) < 0)
       {

           rootNode.left =  add(rootNode.left,data);
       }
       else {
           rootNode.right =  add(rootNode.right,data);
       }
        return rootNode;
    }

    public void insert(T data)
    {
        root = add(root,data);
    }

    private void inOrder(Node<T> rootNode)
    {
        if(rootNode == null)
            return;

        if(rootNode.left != null)
        {
             inOrder(rootNode.left);
        }
        System.out.println(rootNode.data);
        if(rootNode.right != null)
        {
             inOrder(rootNode.right);
        }



    }


    public void printTree()
    {
        inOrder(root);
    }


    public static void main(String[] args)
    {
        BST<Integer> tree = new BST<>();

        tree.insert(5);
        tree.insert(4);
        tree.insert(6);

        tree.printTree();

    }
}
