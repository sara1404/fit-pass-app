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

        LocalDate dateFrom = LocalDate.parse(params.get("dateFrom")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateTo = LocalDate.parse(params.get("dateTo")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return filterByDate(objects, dateFrom ,dateTo);
    }

    private List<TrainingHistory> filterByDate(List<TrainingHistory> history, LocalDate dateFrom, LocalDate dateTo) {
        List<TrainingHistory> filtered = new ArrayList<>();
        for(TrainingHistory hist : history) {
            LocalDate date = hist.getApplication().toLocalDate();
            if(date.isAfter(dateFrom) && date.isBefore(dateTo)) {
                filtered.add(hist);
            }
        }
        return filtered;
    }
}
