import java.util.List;

public interface Database {
    void setQueryAndResult(String s, List<String> asList);
    void executeQuery(String sql);
    List<String> getResultSets();
}
