package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> result = new ArrayList<>();
        List<UserMeal> temp = new ArrayList<>();
        HashMap<LocalDate,Integer> map = new HashMap<>();

        mealList.forEach(i-> {
                    LocalDate date = i.getDateTime().toLocalDate();
                    if(!map.containsKey(date))
                        map.put(date,i.getCalories());
                    else map.put(date,map.get(date)+i.getCalories());
                    if(TimeUtil.isBetween(i.getDateTime().toLocalTime(),startTime,endTime)){
                        temp.add(i);
                    }
                });

        temp.forEach(i-> {
            boolean exceed = false;
            for(Map.Entry<LocalDate,Integer> m : map.entrySet()){
                if(m.getKey().equals(i.getDateTime().toLocalDate()) && m.getValue()>=caloriesPerDay)
                    exceed=true;
            }
            result.add(new UserMealWithExceed(i.getDateTime(),i.getDescription(),i.getCalories(),exceed));
        });

        return result;
    }
}
