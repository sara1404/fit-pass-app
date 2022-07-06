package Utils;

import Interfaces.Repository.ISportObjectRepository;
import Model.SportObject;
import Model.WorkDay;
import Model.WorkTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class BackgroundTaskThread extends Thread {

    public ISportObjectRepository sportObjectRepository;

    public BackgroundTaskThread(ISportObjectRepository sportObjectRepository) {
        this.sportObjectRepository = sportObjectRepository;
    }

    @Override
    public void run() {
        try {
            checkSportObjectsState();
            Thread.sleep(10000);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void checkSportObjectsState() {
        List<SportObject> objects = sportObjectRepository.findAll();
        for(SportObject obj : objects) {
            WorkDay day = findDayFromWorkDays(obj.getWorkTime());
            obj.setStatus(checkStatus(day));
            sportObjectRepository.update(obj);
        }
    }

    private WorkDay findDayFromWorkDays(List<WorkDay> workDays) {
        DayOfWeek today = LocalDateTime.now().getDayOfWeek();
        for(WorkDay day: workDays) {
            if (day.getDay().toLowerCase().equals(today.toString().toLowerCase())){
                return day;
            }
        }
        return null;
    }

    private Constants.SportObjectStatus checkStatus(WorkDay workDay) {
        if(workDay == null) return Constants.SportObjectStatus.CLOSED;

        LocalTime now = LocalTime.now();
        WorkTime workTime = workDay.getTime();

        if(workTime.getStarts().isBefore(now) && workTime.getEnds().isAfter(now)) {
            return Constants.SportObjectStatus.OPEN;
        }
        return Constants.SportObjectStatus.CLOSED;

    }
}
