import java.util.*;

public class MockDatabase implements Database {
    private Map<String,List<String>> queryMap;
    private List<String> resultSet;
    public MockDatabase() {
        queryMap = new HashMap<>();
    }

    @Override
    public void setQueryAndResult(String s, List<String> asList) {
        queryMap.put(s, asList);
    }

    @Override
    public void executeQuery(String sql) {
        resultSet = (queryMap.get(sql) == null) ? new ArrayList<>() :  queryMap.get(sql);
    }

    @Override
    public List<String> getResultSets() {
        return resultSet;
    }
}
