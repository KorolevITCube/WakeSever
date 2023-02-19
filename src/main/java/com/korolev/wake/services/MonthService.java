package com.korolev.wake.services;

import com.korolev.wake.model.MonthDTO;
import com.korolev.wake.model.MonthTrace;
import com.korolev.wake.repository.IRepositoryMonth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@Component
@Slf4j
public class MonthService implements IMonthService{

    @Value("${spring.time.gmt}")
    private String GMT;
    @Autowired
    IRepositoryMonth<MonthTrace,Integer> monthRepository;

    @Override
    public void update(MonthDTO month){
        MonthTrace curMonth = monthRepository.getById(month.getId());
        curMonth.setMask(month.getMask());
        monthRepository.update(curMonth);
    }

    @Override
    public MonthTrace get(Integer month, Integer year) {
        MonthTrace curMonth = monthRepository.getByMonthYear(String.valueOf(month),String.valueOf(year));
        if(curMonth == null){
            GregorianCalendar date = new GregorianCalendar(TimeZone.getTimeZone(GMT), new Locale("ru", "RUS"));
            date.set(year,month-1,1,0,0,0);
            Integer dayOfWeek = date.get(GregorianCalendar.DAY_OF_WEEK);
            Integer maxDays = date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            String mask = "";
            for(int curDay = 1; curDay <= maxDays; curDay++,dayOfWeek++){
                if(dayOfWeek > 7){
                    dayOfWeek = 1;
                }
                if(dayOfWeek == 7 || dayOfWeek == 1){
                    mask += "2";
                }else{
                    mask += "1";
                }
            }
            curMonth = new MonthTrace();
            curMonth.setMonth(String.valueOf(month));
            curMonth.setYear(String.valueOf(year));
            curMonth.setMask(mask);
            curMonth.setMaxDays(maxDays);
            curMonth.setStartWeekDay(date.get(GregorianCalendar.DAY_OF_WEEK));
            Integer id = monthRepository.save(curMonth);
            if(id != -1){
                curMonth.setId(id);
            }else{
                curMonth = null;
            }
        }
        return curMonth;
    }

}
