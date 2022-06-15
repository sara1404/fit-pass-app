package Interfaces.SearchFiltering;

import java.util.List;
import java.util.Map;

public interface IFilterPipeline<T> {
    public List<T> filterAll(List<T> objects, Map<String, String[]> params);
    public IFilterPipeline<T> addFilter(IFilter<T> filter);
}
