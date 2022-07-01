package Utils.SearchImpl.FilterImpl.TrainingHistoryFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.TrainingHistory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TrainingHistorySortByPrice  implements IFilter<TrainingHistory> {
    @Override
    public List<TrainingHistory> filter(List<TrainingHistory> objects, Map<String, String[]> params) {
        if(!params.containsKey("sort") || !params.get("sort")[0].equals("price")) {
            return objects;
        }

        objects.sort(Comparator.comparingDouble(history -> history.getTrainingSession().getPrice()));
        return objects;
    }
}

