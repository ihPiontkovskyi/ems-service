package ua.knu.gra.converter;

public interface Converter<SOURCE, TARGET> {
    TARGET convert(SOURCE source);
}
