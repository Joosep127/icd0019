package generics.converter;

@FunctionalInterface
public interface Converter<T, U> {
    U convert(T input);
}
