import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Queue Implementation w/ Iterable w/ TestClient
 * October 22, 2019
 * Elven Shum
 */

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Queue(){
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.data;
    }

    public void enqueue(Item data){
        Node<Item> prevLast = last;
        last = new Node<Item>();
        last.data = data;
        last.next = null;
        if (isEmpty()){
            first = last;
        } else {
            prevLast.next = last;
        }
        size++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item temp = first.data;
        first = first.next;
        size--;
        return temp;
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

    public Iterator<Item> iterator(){
        return new QueueIterator(first);
    }

    private class QueueIterator implements Iterator<Item>{
        private Node<Item> current;
        public QueueIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {throw new UnsupportedOperationException();}

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item temp = current.data;
            current = current.next;
            return temp;
            //note: can't simply use dequeue, because exceptions.
        }
    }

    public static void main(String[] args) { // Interactive Console User Interface to test Stack methods
        Queue myQueue = new Queue();
        StdOut.println("Elven's Queue Tester: \n");
        StdOut.println("1: Enqueue");
        StdOut.println("2: Dequeue");
        StdOut.println("3: Length");
        StdOut.println("4: Exit");
        StdOut.println("Please type your next command's corresponding index:");
        int command = StdIn.readInt();
        while(command != 4) {
            switch(command){
                case (1):
                    StdOut.println("What would you like to Enqueue to the Queue?");
                    String input = StdIn.readString();
                    myQueue.enqueue(input);
                    break;
                case (2):
                    Object dequeued = myQueue.dequeue();
                    StdOut.println("You have Dequeued " + dequeued + " from the Queue");
                    break;
                case (3):
                    StdOut.println("Your Queue size is " + myQueue.size +" items");
                    break;
                default:
                    StdOut.println("Please enter a legal command index");
            }
            StdOut.println("\n Your current Queue is:");
            StdOut.println(myQueue.toString());
            StdOut.println("\n-----------------------------------------------------------------------");
            StdOut.println("\n 1:Enqueue    2:Dequeue    3:Length    4:Exit");
            StdOut.println("Please type your next command's corresponding index:");
            command = StdIn.readInt();
        }
        StdOut.println("Ending Elven's Stack Tester...");
        StdOut.println("Your remaining Stack: " + myQueue.toString());
    }
}
