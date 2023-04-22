package basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static <T> List<List<T>> twoDArrayToList(T[][] arr) {
        return Arrays.stream(arr).map(List::of).collect(Collectors.toList());
    }
}
