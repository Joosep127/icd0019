package intro;

public class Program {

    public static void main(String[] args) {

        int integer = asInteger("11001101");

        System.out.println(integer); // 205
    }

    public static String asBinaryString(int input) {
        return "";
    }

    public static int asInteger(String input) {
        int num = 0;
        for (int i = input.length()-1; i >= 0; i--)  {
          if (input.charAt(i) == '1') {
            System.out.println(i);
            num += pow(2, i);
          }
        }
        return num;
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.
        int num = 1;
        for (int i = 1; i < power; i++) {
          num *= arg;
        }
        return num;
    }
}
