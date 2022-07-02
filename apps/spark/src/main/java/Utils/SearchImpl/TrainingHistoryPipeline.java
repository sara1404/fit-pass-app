package Utils.SearchImpl;

import Interfaces.SearchFiltering.IFilter;
import Interfaces.SearchFiltering.IFilterPipeline;
import Model.TrainingHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainingHistoryPipeline implements IFilterPipeline<TrainingHistory> {
    private List<IFilter<TrainingHistory>> filters;

    public TrainingHistoryPipeline() {
        filters = new ArrayList<>();
    }
    @Override
    public List<TrainingHistory> filterAll(List<TrainingHistory> objects, Map<String, String[]> params) {
        List<TrainingHistory> filtered = new ArrayList<>(objects);
        for(IFilter<TrainingHistory> filter : filters) {
            filtered = filter.filter(filtered, params);
        }
        return filtered;
    }

    @Override
    public IFilterPipeline<TrainingHistory> addFilter(IFilter<TrainingHistory> filter) {
        filters.add(filter);
        return this;
    }
}
