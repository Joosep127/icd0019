package generics.converter;

public class StringToDouble implements Converter<String, Double> {
    @Override
    public Double convert(String input) {
        return Double.parseDouble(input);
    }
}
