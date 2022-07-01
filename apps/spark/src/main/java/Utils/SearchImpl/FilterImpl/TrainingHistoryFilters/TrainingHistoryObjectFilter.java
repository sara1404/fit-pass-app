package Utils.SearchImpl.FilterImpl.TrainingHistoryFilters;

import Interfaces.Repository.ISportObjectRepository;
import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;
import Model.TrainingHistory;
import Repository.SportObjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TrainingHistoryObjectFilter implements IFilter<TrainingHistory> {

    private ISportObjectRepository sportObjectRepository;
    public TrainingHistoryObjectFilter(ISportObjectRepository sportObjectRepository) {
        this.sportObjectRepository = sportObjectRepository;
    }
    @Override
    public List<TrainingHistory> filter(List<TrainingHistory> objects, Map<String, String[]> params) {
        if(!params.containsKey("object")) {
            return objects;
        }
        String object = params.get("object")[0];
        return filterByObject(objects, object);
    }

    private List<TrainingHistory> filterByObject(List<TrainingHistory> history, String object) {
        List<TrainingHistory> filtered = new ArrayList<>();

        for(TrainingHistory hist : history) {
            SportObject sportObject = sportObjectRepository.findById(hist.getTrainingSession().getObjectId());
            if(sportObject.getName().toLowerCase().contains(object.toLowerCase())) {
                filtered.add(hist);
            }
        }
        return filtered;
    }
}
