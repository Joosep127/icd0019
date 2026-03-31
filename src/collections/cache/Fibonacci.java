package collections.cache;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

  Map<Integer, Integer> map = new HashMap<Integer, Integer>();

  public Integer fib(Integer n) {
    if (map.containsKey(n)) {
      return map.get(n);
    }

    if (n <= 1) {
      return n;
    }

    int result = fib(n - 1) + fib(n - 2);

    map.put(n, result);

    return result;
  }

}
