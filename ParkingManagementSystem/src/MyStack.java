public class MyStack {
    private Node top;
    private int size;
    public MyStack() {
        top = null;
        size = 0;
    }
    public void push(Action action) {
        Node node = new Node(action);
        node.next = top;
        top = node;
        size++;
    }
    public Action pop() {
        if (isEmpty()) {
            return null;
        }
        Node node = top;
        top = top.next;
        size--;
        return (Action) node.data;
    }
    public Action peek() {
        if (isEmpty()) {
            return null;
        }
        return (Action) top.data;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public Action[] toArray() {
        Action[] items = new Action[size];
        Node current = top;
        int i = 0;
        while (current != null) {
            items[i] = (Action) current.data;
            current = current.next;
            i++;
        }
        return items;
    }
    public void clear() {
        top = null;
        size = 0;
    }
}