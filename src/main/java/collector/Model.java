package collector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.TreeSet;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Model {
    private LinkedHashMap<String, LinkedHashMap<String, TreeSet<String>>> data;

    public Model() {
      data = new LinkedHashMap<>();
    }

    public String[] getListByPath(String[] path) {
        if (path.length == 3) {
            if (data.containsKey(path[1]) && data.get(path[1]).containsKey(path[2])) {
                return data.get(path[1]).get(path[2]).toArray(String[]::new);
            }
        }
        // empty array
        return new String[0];
    }

    public Boolean addUnique(String host, String key, String value) {
        Boolean updated = FALSE;
        // create empty map for new host
        if (!data.containsKey(host)) {
            data.put(host, new LinkedHashMap<>());
            updated = TRUE;
        }
        // add key with value if not exists
        if (!data.get(host).containsKey(key)) {
            data.get(host).put(key, new TreeSet<>());
            updated = TRUE;
        }
        // hashset add only unique values
        if (data.get(host).get(key).add(value)) {
            updated = TRUE;
        }
        return updated;
    }
}
