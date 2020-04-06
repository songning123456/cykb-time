package com.sn.time.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: songning
 * @date: 2020/4/5 20:22
 */
public class DateUtil {

    /**
     * String => java.util.Date
     *
     * @param str        表示日期的字符串
     * @param dateFormat 传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
     * @return java.util.Date类型日期对象（如果转换失败则返回null）
     */
    public static Date strToDate(String str, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static List<Date> stepTime(Date oldTime, Date newTime, int size) {
        List<Date> result = new ArrayList<>();
        Long oldVal = oldTime.getTime();
        Long newVal = newTime.getTime();
        long step = (newVal - oldVal) / size;
        for (int i = 0; i < size - 1; i++) {
            result.add(new Date(oldVal + step * (i + 1)));
        }
        result.add(newTime);
        return result;
    }

    public static void main(String[] args) {
        Date start = new Date();
        try {
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date end = new Date();
        DateUtil.stepTime(start, end, 10);
    }
}
