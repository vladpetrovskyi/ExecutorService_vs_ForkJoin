package mt.hw;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListCreator {
    public static List<Integer> createList(int size) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            intList.add((int) (Math.random() * 10));
        }
        return intList;
    }
}
