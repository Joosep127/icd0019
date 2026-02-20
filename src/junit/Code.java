package junit;

import java.security.KeyStore;

public class Code {

    public static void main(String[] args) {
        System.out.println(longestStreak("a"));
    }

    public static boolean isSpecial(int candidate) {
        int temp = candidate % 11;
        return temp == 3 || temp == 0;
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return 0;
        }

        char temp_char = 0;
        int count = 0;
        int ret = 0;

        for (char c : inputString.toCharArray()) {
            if (c == temp_char) {
                count++;

            } else {
                count = 1;
                temp_char = c;
            }
            if (ret < count) {
                ret = count;
            }
        }
        return ret;
    }

    public static Character mode(String input) {
        // Eeldus, et meil on ainul 128 erinevat sümbolit, sest ma ei taha importida Hashmappe
        if (input == null || input.isEmpty()) {
            return null;
        }
        int[] array = new int[128];
        for (char c : input.toCharArray()) {
            array[c] += 1;
        }
        int highestIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0 && array[highestIndex] <= array[i]) {
                highestIndex = i;
            }
        }
        return (char) highestIndex;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        if (allCharacters == null || allCharacters.isEmpty()) {
            return 0;
        }
        int num = 0;
        for (char c : allCharacters.toCharArray()) {
            if (c == targetCharacter) {
                num += 1;
            }
        }
        return num;
    }

    public static boolean listContains(int[] integers, int i) {
        for (int temp : integers) {
            if (temp == i) {
                return true;
            }
        }
        return false;
    }

    public static int[] removeDuplicates(int[] integers) {
        int[] newList = new int[integers.length];
        int size = 0;

        for (int i : integers) {
            if (!listContains(newList, i)) {
                newList[size] = i;
                size++;
            }
        }

        int[] retList = new int[size];

        for (int i = 0; i < size; i++) {
            retList[i] = newList[i];
        }

        return retList;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int count = 0;
        for (int i : removeDuplicates(integers)) {
            count += i;
        }
        return count;
    }

}
