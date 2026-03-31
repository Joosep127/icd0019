package collections.set;

import org.junit.jupiter.api.Test;

import java.util.*;


public class Birthday {

    @Test
    public void runCode() {

        Random r = new Random();

        int randomDayOfYear = r.nextInt(365);

        int count = 0;

        while (randomDayOfYear != r.nextInt(365)) {
            count++;
        }

        System.out.println(count);
        // pick random day in a loop
        // find how many iterations till first collision (got the same number)



    }

}
