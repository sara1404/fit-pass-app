package Utils.SearchImpl.FilterImpl.TrainingHistoryFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;
import Model.TrainingHistory;
import Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainingHistoryTrainingTypeFilter implements IFilter<TrainingHistory> {
    @Override
    public List<TrainingHistory> filter(List<TrainingHistory> objects, Map<String, String[]> params) {
        if(!params.containsKey("trType")) {
            return objects;
        }

        Constants.TrainingType type = Constants.TrainingType.valueOf(params.get("trType")[0].toUpperCase());
        System.out.println(type);
        return filterByType(objects, type);
    }

    private List<TrainingHistory> filterByType(List<TrainingHistory> history, Constants.TrainingType type) {
        List<TrainingHistory> filtered = new ArrayList<>();
        for(TrainingHistory hist : history) {
            System.out.println(hist.getTrainingSession().getType());
            if(hist.getTrainingSession().getType() == type) {
                filtered.add(hist);
            }
        }
        return filtered;
    }
}
