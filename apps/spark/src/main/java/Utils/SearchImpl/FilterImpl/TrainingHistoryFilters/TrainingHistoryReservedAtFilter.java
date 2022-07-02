package Utils.SearchImpl.FilterImpl.TrainingHistoryFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.TrainingHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainingHistoryReservedAtFilter implements IFilter<TrainingHistory> {

    @Override
    public List<TrainingHistory> filter(List<TrainingHistory> objects, Map<String, String[]> params) {
        if(!params.containsKey("dateFrom") || !params.containsKey("dateTo")) {
            return objects;
        }

        LocalDateTime dateFrom = LocalDateTime.parse(params.get("dateFrom")[0], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        LocalDateTime dateTo = LocalDateTime.parse(params.get("dateTo")[0], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        return filterByDate(objects, dateFrom ,dateTo);
    }

    private List<TrainingHistory> filterByDate(List<TrainingHistory> history, LocalDateTime dateFrom, LocalDateTime dateTo) {
        List<TrainingHistory> filtered = new ArrayList<>();
        for(TrainingHistory hist : history) {
            LocalDateTime date = hist.getApplication();
            if(date.isAfter(dateFrom) && date.isBefore(dateTo)) {
                filtered.add(hist);
            }
        }
        return filtered;
    }
}
