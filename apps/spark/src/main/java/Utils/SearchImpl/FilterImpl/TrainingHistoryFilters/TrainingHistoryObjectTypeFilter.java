package Utils.SearchImpl.FilterImpl.TrainingHistoryFilters;

import Interfaces.Repository.ISportObjectRepository;
import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;
import Model.TrainingHistory;
import Utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TrainingHistoryObjectTypeFilter implements IFilter<TrainingHistory> {

    private ISportObjectRepository sportObjectRepository;
    public TrainingHistoryObjectTypeFilter(ISportObjectRepository sportObjectRepository) {
        this.sportObjectRepository = sportObjectRepository;
    }

    @Override
    public List<TrainingHistory> filter(List<TrainingHistory> objects, Map<String, String[]> params) {
        if(!params.containsKey("objectType") || params.get("objectType")[0].trim().equals("")) {
            return objects;
        }
        try {
            Constants.SportObjectType type = Constants.SportObjectType.valueOf(params.get("objectType")[0].toUpperCase());
            return filterByType(objects, type);
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }

    private List<TrainingHistory> filterByType(List<TrainingHistory> history, Constants.SportObjectType type) {
        List<TrainingHistory> filtered = new ArrayList<>();
        for(TrainingHistory hist : history) {
            SportObject sportObject = sportObjectRepository.findById(hist.getTrainingSession().getObjectId());
            if(sportObject.getType() == type) {
                filtered.add(hist);
            }
        }
        return filtered;
    }
}
