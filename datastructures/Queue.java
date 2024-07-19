import java.util.Arrays;

public class Queue implements StackQueueOperations{
    Queue(){
        queue = new int[0];
    }
    private int[] queue;
    @Override
    public int pop() {
        int[] newQueue = new int[queue.length-1];
        int key = queue[queue.length-1];
        System.arraycopy(queue, 0, newQueue, 0, newQueue.length);
        queue = newQueue;
        return key;
    }

    @Override
    public void push(int data) {
        int[] newQueue = new int[queue.length+1];
        newQueue[0] = data;
        for(int i = 0 ; i < queue.length ; i++)
            newQueue[i+1] = queue[i];
        queue = newQueue;
    }

    public void print(){
        System.out.println(Arrays.toString(queue));
    }
}
