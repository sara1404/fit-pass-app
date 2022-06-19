package Utils.SearchImpl.FilterImpl;

import Interfaces.SearchFiltering.IFilter;
import Model.SportObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SportObjectAverageMarkFilter implements IFilter<SportObject> {
    @Override
    public List<SportObject> filter(List<SportObject> objects, Map<String, String[]> params) {
        System.out.println("working");
        if(!params.containsKey("averageMark") || !isValidInput(params.get("averageMark")[0])){
            System.out.println("No average mark");
            return objects;
        }
        String filteredInput = params.get("averageMark")[0].trim();

        return matchAverageMarkCase(objects, filteredInput);
    }

    private List<SportObject> matchAverageMarkCase(List<SportObject> objects, String filteredInput) {
        List<SportObject> filteredSportObjects = new ArrayList<>();
        for (SportObject object : objects){
            if(object.getAverageMark() == Double.parseDouble(filteredInput)){
                filteredSportObjects.add(object);
            }
        }
        return filteredSportObjects;
    }

    private boolean isValidInput(String input){
        try{
            Double.parseDouble(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
