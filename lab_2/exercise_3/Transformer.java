package exercise_3;

public interface Transformer<T, R> {
    R transform(T input);
}