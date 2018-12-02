package common;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class InputUtil {

    private InputUtil() {
    }


    public static Stream<String> readLines(Class clazz) {
        try {
            return Files.lines(Paths.get(clazz.getResource("input.txt").toURI()));
        } catch (Exception ex) {
            throw new IllegalStateException("Error reading input file", ex);
        }
    }

    public static String read(Class clazz) {
        try {
            return new String(Files.readAllBytes(Paths.get(clazz.getResource("input.txt").toURI())));
        } catch (Exception ex) {
            throw new IllegalStateException("Error reading input file", ex);
        }
    }
}
