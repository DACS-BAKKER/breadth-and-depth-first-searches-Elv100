/*
 * Node
 * October 22, 2019
 * Elven Shum
 */

public class Node<Item>{
    public Item data;
    public Node next;

    public Node(Item data) {
        this.data = data;
    }

    public Node() {

    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
