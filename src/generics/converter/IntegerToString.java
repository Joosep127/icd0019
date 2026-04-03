package generics.converter;

public class IntegerToString implements Converter<Integer, String> {
    @Override
    public String convert(Integer input) {
        return String.valueOf(input);
    }
}
