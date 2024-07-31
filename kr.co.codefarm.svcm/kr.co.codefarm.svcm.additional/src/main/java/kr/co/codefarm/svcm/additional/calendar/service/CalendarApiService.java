package kr.co.codefarm.svcm.additional.calendar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;

@Service
public class CalendarApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.calendarApi";

	// 달력 조회
	public List<Map<String, Object>> getCalendarList(Map<String, Object> paramMap) {
		String dt = paramMap.get("pCurdate").toString();
		int year = Integer.parseInt(dt.substring(0,4));
		int month = Integer.parseInt(dt.substring(4));
		List<Map<String, Object>> list = new ArrayList<>();
		String[] week = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		LocalDate calenderDay = LocalDate.of(year, month, 1);
        int dayOfWeek = calenderDay.getDayOfWeek().getValue()%7;
        int lastDay = calenderDay.lengthOfMonth();

        Map<String,Object> calendarMap = new HashMap<>();
		for (int i = 0; i < dayOfWeek; i++) {
			calendarMap.put(week[i],"");
		}

		for (int i = 1; i <= lastDay ; i++) {
			calendarMap.put(week[dayOfWeek%7],i);
			if (dayOfWeek % 7 == 6) {
				list.add(calendarMap);
				calendarMap = new HashMap<>();
			}
			dayOfWeek++;
		}
		if(dayOfWeek % 7 != 6) {
			while(dayOfWeek % 7 != 6) {
				calendarMap.put(week[dayOfWeek%7],"");
				dayOfWeek++;
			}
			list.add(calendarMap);
			calendarMap = new HashMap<>();
		}
		
		return list;
	}

	// 일정 조회
	public List<Map<String, Object>> selectCalendarList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectCalendarList", paramMap);
		return list;
	}

}