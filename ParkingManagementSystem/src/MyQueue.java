public class MyQueue {
    private Node front;
    private Node rear;
    private int size;
    public MyQueue() {
        front = null;
        rear = null;
        size = 0;
    }
    public void enqueue(Vehicle vehicle) {
        Node node = new Node(vehicle);
        if (isEmpty()) {
            front = node;
            rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }
    public Vehicle dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node node = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return (Vehicle) node.data;
    }
    public Vehicle peek() {
        if (isEmpty()) {
            return null;
        }
        return (Vehicle) front.data;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public Vehicle[] toArray() {
        Vehicle[] items = new Vehicle[size];
        Node current = front;
        int i = 0;
        while (current != null) {
            items[i] = (Vehicle) current.data;
            current = current.next;
            i++;
        }
        return items;
    }
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}