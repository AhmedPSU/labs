package exercise_3;

import java.util.ArrayList;
import java.util.List;

public class Pipeline<T> {

    // Steps stored as wildcards since each may have a different type
    private final List<Transformer<?, ?>> steps;

    private Pipeline(List<Transformer<?, ?>> steps) {
        this.steps = steps;
    }

    // Start a new pipeline with initial type T
    public static <T> Pipeline<T> start() {
        return new Pipeline<>(new ArrayList<>());
    }

    // Add a transformation — returns a NEW pipeline with the updated output type R
    public <R> Pipeline<R> add(Transformer<T, R> transformer) {
        List<Transformer<?, ?>> newSteps = new ArrayList<>(this.steps);
        newSteps.add(transformer);
        return new Pipeline<>(newSteps);
    }

    // Apply all transformations in order
    @SuppressWarnings("unchecked")
    public Object execute(Object input) {
        Object result = input;
        for (Transformer<?, ?> step : steps) {
            result = ((Transformer<Object, Object>) step).transform(result);
        }
        return result;
    }
}