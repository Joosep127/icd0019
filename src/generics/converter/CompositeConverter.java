package generics.converter;

public class CompositeConverter<T, U, S> implements Converter<T, S> {

    private final Converter<T, U> first;
    private final Converter<U, S> second;

    public CompositeConverter(Converter<T, U> first, Converter<U, S> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public S convert(T input) {
        return second.convert(first.convert(input));
    }
}
