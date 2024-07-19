import java.util.Arrays;

public class Stack implements StackQueueOperations {
    Stack(){
        stack = new int[0];
    }
    private int [] stack;
    @Override
    public int pop() {
        if(stack.length > 0){
            int [] newStack = new int[stack.length-1];
            int key = stack[stack.length-1];
            System.arraycopy(stack, 0, newStack, 0, newStack.length);
            stack = newStack;
            return key;
        }
        else
            throw new NullPointerException("stack is null");
    }

    @Override
    public void push(int data) {
        int [] newStack = new int[stack.length+1];
        System.arraycopy(stack, 0, newStack, 0, newStack.length - 1);
        newStack[newStack.length-1] = data;
        stack = newStack;
    }

    public void print(){
        System.out.println(Arrays.toString(stack));
    }
}
