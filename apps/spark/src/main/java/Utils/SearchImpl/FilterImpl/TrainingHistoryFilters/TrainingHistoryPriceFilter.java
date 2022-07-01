package Utils.SearchImpl.FilterImpl.TrainingHistoryFilters;

import Interfaces.SearchFiltering.IFilter;
import Model.TrainingHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainingHistoryPriceFilter implements IFilter<TrainingHistory> {

    @Override
    public List<TrainingHistory> filter(List<TrainingHistory> objects, Map<String, String[]> params) {
        if(!params.containsKey("priceFrom") || !params.containsKey("priceTo")) {
            return objects;
        }
        float priceFrom = Float.parseFloat(params.get("priceFrom")[0]);
        float priceTo = Float.parseFloat(params.get("priceTo")[0]);
        return filterByPrice(objects, priceFrom, priceTo);
    }

    private List<TrainingHistory> filterByPrice(List<TrainingHistory> history, float priceFrom, float priceTo) {
        List<TrainingHistory> filtered = new ArrayList<>();
        for(TrainingHistory hist : history) {
            float price = hist.getTrainingSession().getPrice();
            if(price >= priceFrom && price <= priceTo) {
                filtered.add(hist);
            }
        }
        return filtered;
    }
}
