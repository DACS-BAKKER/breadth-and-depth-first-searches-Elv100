import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Stack Implementation w/ Iterable w/ TestClient
 * October 22, 2019
 * Elven Shum
 */

public class Stack<Item> implements Iterable<Item>{
    private Node<Item> first;
    private int size;

    public Stack() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void push(Item data){
        Node<Item> prevFirst = first;
        first = new Node<Item>();
        first.data = data;
        first.next = prevFirst;
        size++;
    }

    public Item pop(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        //save for return
        Item item = first.data;
        //eliminate first node
        first = first.next;
        size--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.data;
    }

    //Wow! This Iterator Sure is Convenient
    //This was initially sass, but its actually pretty nice
    public String toString(){
        String output = "";
        for (Item data: this){
            output = output + data + " ";
        }
        return output;
    }

    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.data;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) { // Interactive Console User Interface to test Stack methods
        Stack myStack = new Stack();
        StdOut.println("Elven's Stack Tester: \n");
        StdOut.println("1: Push");
        StdOut.println("2: Pop");
        StdOut.println("3: Length");
        StdOut.println("4: Exit");
        StdOut.println("Please type your next command's corresponding index:");
        int command = StdIn.readInt();
        while(command != 4) {
            switch(command){
                case (1):
                    StdOut.println("What would you like to Push to your Stack?");
                    String input = StdIn.readString();
                    myStack.push(input);
                    break;
                case (2):
                    Object popped = myStack.pop();
                    StdOut.println("You have Popped " + popped + " from the Stack");
                    break;
                case (3):
                    StdOut.println("Your Stack size is " + myStack.size +" items");
                    break;
                default:
                    StdOut.println("Please enter a legal command index");
            }
            StdOut.println("\n Your current Stack is:");
            StdOut.println(myStack.toString());
            StdOut.println("\n-----------------------------------------------------------------------");
            StdOut.println("\n 1:Push    2:Pop    3:Length    4:Exit");
            StdOut.println("Please type your next command's corresponding index:");
            command = StdIn.readInt();
        }
        StdOut.println("Ending Elven's Stack Tester...");
        StdOut.println("Your remaining Stack: " + myStack.toString());
    }

}
