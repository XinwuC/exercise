package basic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static <T> List<List<T>> twoDArrayToList(T[][] arr) {
        return Arrays.stream(arr).map(List::of).collect(Collectors.toList());
    }

    // public static <T> T[][] twoDListToArray(List<List<T>> list) {
    //     // @SuppressWarnings("unchecked")
    //     // T[][] array = (T[][]) Array.newInstance(T.getClass(), list.size(),
    //     // list.get(0).size());

    //     return list.stream().map(l -> l.toArray(T[]::new)).toArray(T[][]::new);
    // }

    public static Integer[][] twoDListToArray(List<List<Integer>> list) {
        // @SuppressWarnings("unchecked")
        // T[][] array = (T[][]) Array.newInstance(T.getClass(), list.size(),
        // list.get(0).size());

        return list.stream().map(l -> l.toArray(Integer[]::new)).toArray(Integer[][]::new);
    }

}
