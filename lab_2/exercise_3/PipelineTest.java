package exercise_3;

public class PipelineTest {

    public static void main(String[] args) {
        // 1. Same type: String -> String
        Pipeline<String> p1 = Pipeline.start();
        p1 = p1.add(String::trim)
               .add(s -> s.toUpperCase())
               .add(s -> s + "!");
        System.out.println(p1.execute("   hello generics   "));
        // Output: HELLO GENERICS!

        // 2. Type-changing: String -> Integer -> String
        Pipeline<String> p2 = Pipeline.start();
        p2 = p2.add((String s) -> s.length())   // String -> Integer
               .add(n -> "Length is: " + n);     // Integer -> String
        System.out.println(p2.execute("generics"));
        // Output: Length is: 8

        // 3. Type-changing: Integer -> Double -> String
        //    Each add() returns a NEW pipeline, so we need a new variable
        //    for the final type (Pipeline<String>).
        Pipeline<Integer> p3 = Pipeline.start();
        Pipeline<String> p3Out = p3.add((Integer n) -> n * 2.5)   // Integer -> Double
                                   .add(d -> "Result = " + d);    // Double -> String
        System.out.println(p3Out.execute(4));
        // Output: Result = 10.0

        // Original p3 is untouched - still an empty Pipeline<Integer>
        System.out.println(p3.execute(4));
        // Output: 4 (no transformations applied)
    }
}