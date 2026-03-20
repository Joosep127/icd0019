package inheritance.stack;

import java.util.Stack;

public class LoggingStack extends Stack<Integer> {

    @Override
    public Integer push(Integer item) {
        System.out.println("Did stuff with pushing.");
        return super.push(item);
    }

    @Override
    public synchronized Integer pop() {
        System.out.println("OMG ITS POPPING.");
        return super.pop();
    }
}
