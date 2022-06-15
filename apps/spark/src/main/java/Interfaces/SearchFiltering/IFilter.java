package Interfaces.SearchFiltering;

import java.util.List;
import java.util.Map;

public interface IFilter<T> {
    public List<T> filter(List<T> objects, Map<String, String[]> params);
}
