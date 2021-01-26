package ramzet89.dictionary.util;

import java.util.*;

public class CustomCollectionRandomizer {
    private static final Random rand = new Random();
    
    public static Set<Long> getRandomItemIds(Set<Long> ids, int count) {
        if (ids.size() <= count) {
            return ids;
        }
        List<Long> modifiableList = new ArrayList<>(ids);

        Set<Long> resultSet = new HashSet<>();
        while (resultSet.size() < count) {
            resultSet.add(
                    modifiableList.remove(
                            rand.nextInt(modifiableList.size()))
            );
        }
        return resultSet;
    }
}
