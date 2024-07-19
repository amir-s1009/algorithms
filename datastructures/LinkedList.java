import java.util.NoSuchElementException;

public class LinkedList {
    LinkedList(){
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    private Node sentinel;

    public static class Node{
        Node(){
            this.data = 0;
        }
        Node(int data){
            this.data = data;
        }
        public Node next;
        public Node prev;
        public int data;

    }
    public boolean contains(int key){
        Node node = search(key);
        return node != null;
    }
    public Node search(int key){
        Node x = sentinel;
        if(x.next == sentinel)
            return null;
        while (x.data != key && x.next != sentinel)
            x = x.next;
        if(x.next == sentinel && x.data != key)
            return null;
        else
            return x;
    }
    public void insertFirst(Node item){
        item.next = sentinel.next;
        item.prev = sentinel;
        if(sentinel.next != null)
            sentinel.next.prev = item;
        sentinel.next = item;
        if(sentinel.prev == null)
            sentinel.prev = item;
    }
    public void insertLast(Node item){
        item.next = sentinel;
        item.prev = sentinel.prev;
        sentinel.prev.next = item;
        sentinel.prev = item;
    }
    public void insertAfter(Node source, Node item){
        if(source.next == sentinel)
            insertLast(item);
        else{
            item.next = source.next;
            item.prev = source;
            source.next.prev = item;
            source.next = item;
        }
    }
    public void insertBefore(Node source, Node item){
        if(source.prev == sentinel)
            insertFirst(item);
        else{
            item.next = source;
            item.prev = source.prev;
            source.prev.next = item;
            source.prev = item;
        }
    }
    public void delete(Node item){
        item.next.prev = item.prev;
        item.prev.next = item.next;
    }
    public int first(){
        return sentinel.next.data;
    }
    public int last(){
        return sentinel.prev.data;
    }
    public int size(){
        int size = 0;
        Node temp = sentinel;
       while (temp.next != sentinel){
           size++;
           temp = temp.next;
       }
       return size;
    }
    private void print(){
        Node temp = sentinel;
        System.out.print("list:[");
        while (temp.next != sentinel){
            if(temp.next.next != sentinel)
                System.out.print(temp.next.data+", ");
            else
                System.out.print(temp.next.data);
            temp = temp.next;
        }
        System.out.println("]");
    }
    public void info(){
        print();
        System.out.println("size: "+size());
        System.out.println("first: "+first());
        System.out.println("last: "+last());
        System.out.println("minimum: "+minimum());
        System.out.println("maximum: "+maximum());
        System.out.println("average: "+average());
        System.out.println("standard deviation: "+std());
    }
    private int maximum(){
        int maximum = sentinel.next.data;
        if(sentinel.next == sentinel)
            return 0;
        else if(sentinel.next.next == sentinel)
            return maximum;
        else {
            Node temp = sentinel.next.next;
            while (temp != sentinel) {
                if (temp.data > maximum)
                    maximum = temp.data;
                temp = temp.next;
            }
            return maximum;
        }
    }
    private int minimum(){
        int minimum = sentinel.next.data;
        if(sentinel.next == sentinel)
            return 0;
        else if(sentinel.next.next == sentinel)
            return minimum;
        else {
            Node temp = sentinel.next.next;
            while (temp != sentinel) {
                if (temp.data < minimum)
                    minimum = temp.data;
                temp = temp.next;
            }
            return minimum;
        }
    }
    private float average(){
        if(sentinel.next == sentinel)
            return 0;
        else{
            Node node = sentinel.next;
            int sum = 0;
            while (node != sentinel){
                sum += node.data;
                node = node.next;
            }
            return (float) sum/size();
        }
    }
    private double std(){
        if(sentinel.next == sentinel)
            return 0;
        else{
            Node node = sentinel.next;
            float average = average();
            int sum = 0;
            while(node != sentinel){
                sum += Math.pow(node.data-average, 2);
                node = node.next;
            }
            return Math.sqrt(sum/(float)size());
        }
    }

    public void insertion_sort(){
        if(sentinel.next == sentinel)
            throw new NullPointerException("your list is empty!");
        else {
            if(sentinel.next.next != sentinel){
                Node key = sentinel.next.next;
                while (key != sentinel){
                    Node temp = key.prev;
                    int value = key.data;
                    while (temp != sentinel){
                        if(temp.data > value)
                            temp.next.data = temp.data;
                        else
                            break;
                        temp = temp.prev;
                    }
                    temp.next.data = value;
                    key = key.next;
                }
            }
        }
    }
}
