package Utils.SearchImpl.FilterImpl.TrainingHistoryFilters;

import Interfaces.Repository.ISportObjectRepository;
import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;
import Model.TrainingHistory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TrainingHistorySortByObject   implements IFilter<TrainingHistory> {
    private ISportObjectRepository sportObjectRepository;

    public TrainingHistorySortByObject(ISportObjectRepository sportObjectRepository) {
        this.sportObjectRepository = sportObjectRepository;
    }

    @Override
    public List<TrainingHistory> filter(List<TrainingHistory> objects, Map<String, String[]> params) {
        if(!params.containsKey("sort") || !params.get("sort")[0].equals("object")) {
            return objects;
        }

        objects.sort(Comparator.comparing(history -> {
            SportObject sportObject = sportObjectRepository.findById(history.getTrainingSession().getObjectId());
            return sportObject.getName();
        }));
        return objects;
    }
}
