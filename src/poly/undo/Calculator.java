package poly.undo;

import namespace.A;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private double value;
    private List<String> history = new ArrayList<String>();

    public void input(double value) {
        history.add("S%.5f".formatted(this.value));
        this.value = value;
    }

    public void add(double addend) {
        history.add("A%.5f".formatted(-addend));
        value += addend;
    }

    public void multiply(double multiplier) {
        history.add("M%.5f".formatted(1/multiplier));
        value *= multiplier;
    }

    public double getResult() {
        return value;
    }

    public void undo() {
        String func = history.removeLast();
        double value = Double.parseDouble(func.substring(1));

        switch (func.charAt(0)) {
            case 'A':
                this.value += value;
                break;
            case 'S':
                this.value = value;
                break;
            case 'M':
                this.value *= value;
                break;
            default:
                System.out.println("Unknown operation: " + func.charAt(0));
                break;
        }
    }
}
