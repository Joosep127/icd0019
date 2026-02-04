package intro;

public class Program {

    public static void main(String[] args) {

        int integer = asInteger("11001101");

        System.out.println(integer); // 205
        System.out.println(asBinaryString(integer));
    }

    public static String asBinaryString(int input) {
        int temp;
        StringBuilder sb = new StringBuilder("");
        while (0 < input) {
          temp = input % 2;
          input = input / 2;
          sb.insert(0, temp);
        }
        return sb.toString();
    }

    public static int asInteger(String input) {
        int num = 0;
        for (int i = input.length()-1; i >= 0; i--)  {
          if (input.charAt(input.length()-1-i) == '1') {
            num += pow(2, i);
          }
        }
        return num;
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.
        int num = 1;
        for (int i = 1; i < power+1; i++) {
          num *= arg;
        }
        return num;
    }
}
