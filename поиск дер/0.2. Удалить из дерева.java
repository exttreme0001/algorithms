import java.util.*;
import java.io.*;
import java.util.Comparator;

class Tree<T>{
    class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    Node root;
    Comparator<T> comparator;

    Tree(T value, Comparator<T> comparator) {
        root = new Node(value);
        this.comparator = comparator;
    }


    Tree(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }
    Tree(Node root, Comparator<T> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    T add(T value) {
        if (root == null) {
            root = new Node(value);
            return value;
        }
        Node previous = null;
        Node current = root;

        while (current != null) {
            previous = current;
            int compare = comparator.compare(value, current.data);
            if (compare == 0){
                return null;}
            else if (compare < 0) {
                current = current.left;
            } else
                current = current.right;
        }

        Node newNode = new Node(value);
        int compare = comparator.compare(value, previous.data);

        if (compare < 0) {
            previous.left = newNode;
        } else
            previous.right = newNode;
        return value;}


    //PRINT : Root  - Left  - Right

    void printRLeftRight(PrintWriter pw){
        printRLeftRight(root,pw);
    }

    private void printRLeftRight(Node current,PrintWriter pw){
        if(current == null)
            return ;
        if (current.data == null) return;
        pw.println(current.data);
        printRLeftRight(current.left,pw);
        printRLeftRight(current.right,pw);
    }
    //PRINT : Left  - Right - Root


    private T findMostLeftAtRight(Node current){
        if(current.left == null){
            return current.data;
        }
        else return findMostLeftAtRight(current.left);
    }

    void remove(T value){
        root = remove(value,root);
    }

    private Node remove(T value, Node current) {
        if(current == null){
            return null;
        }
        int compare = comparator.compare(value, current.data);
        if(compare < 0){ current.left = remove(value, current.left);}
        else if(compare > 0){
            current.right = remove(value, current.right);}
        else
        {
            //leaf
            if (current.left == null && current.right == null) {
                return null;

            }

            //  child
            if(current.left == null){
                return current.right;
            }
            if(current.right == null){
                return current.left;
            }
            // 2 children
            T mostLeftAtRight = findMostLeftAtRight(current.right);
            current.data = mostLeftAtRight;
            current.right = remove(mostLeftAtRight, current.right);
        }
        return current;
    }


}

public class Main implements Runnable {
    public static void main(String[] args)throws FileNotFoundException {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            PrintWriter pw = new PrintWriter(new File("output.txt"));

            Tree<Integer> tree = new Tree<>(Integer::compareTo);

            if (!sc.hasNextInt()) {
                sc.close();
                pw.close();
                return;
            }

            int del = sc.nextInt();
            sc.nextLine();

            while (sc.hasNextInt()) {
                tree.add(sc.nextInt());
            }
            sc.close();


            tree.remove(del);

            //sout
            if (tree.root != null) {
                tree.printRLeftRight(pw);
            }
            pw.flush();
            pw.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
    }
}
}