package com.gly.springboot.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 时间格式工具
 *
 * @author admin
 */
public class DateUtil {

    public static String patternDate = "yyyy-MM-dd";
    public static String patternDate1 = "yyyyMMdd";
    public static String patternDateTime = "yyyy-MM-dd HH:mm:ss";
    public static String patternDateTimeS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String patternDateTime1 = "yyyy/MM/dd HH:mm:ss";
    public static String patternDateTimeFull = "yyyyMMddHHmmss";
    public static String patternDateTimeLong = "yyyyMMddHHmmssSSS";

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date now() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static String today() {
        Date currentTime = new Date();
        return dateToStr(currentTime);
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStr() {
        SimpleDateFormat formatter = new SimpleDateFormat(patternDateTime);
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStr(Date now) {
        SimpleDateFormat formatter = new SimpleDateFormat(patternDateTime);
        String dateString = formatter.format(now);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStr(String pattern, Date now) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(now);
        return dateString;
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(patternDateTime);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss 或 yyyy/MM/dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String pattern, String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到分钟
     *
     * @return
     */
    public static String minutes() {
        String dateString = dateToStr();
        return dateString.substring(14, 16);
    }

    /**
     * 得到分钟
     *
     * @return
     */
    public static String minutes(String strDateTime) {
        return strDateTime.substring(14, 16);
    }

    /**
     * 得到分钟
     *
     * @param pattern     yyyy-MM-dd HH:mm:ss
     * @param strDateTime
     * @return
     */
    public static String minutes(String pattern, String strDateTime) {
        Date date = strToDate(pattern, strDateTime);
        String dateTime = dateToStr(date);
        return dateTime.substring(14, 16);
    }

    /**
     * 获取两时间点内 时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> twoTimeHour(Date startTime, Date endTime) {
        List<String> hourList = new ArrayList<>();
        try {
            long from = startTime.getTime();
            long to = endTime.getTime();
            int hours = (int) ((to - from) / (1000 * 60 * 60));
            for (int i = 1; i <= hours; i++) {
                String t = dateToStr(DateUtils.addHours(startTime, i));
                String[] dateTime = t.split(" ");
                String[] time = dateTime[1].split(":");
                hourList.add(time[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hourList;
    }

    /**
     * 获取固定间隔时刻集合
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param interval 时间间隔(单位：分钟)
     * @return
     */
    public static List<String> intervalTimeList(String start, String end, int interval) {
        Date startDate = strToDate("HH:mm:ss", start);
        Date endDate = strToDate("HH:mm:ss", end);
        List<String> list = new ArrayList<>();
        while (startDate.getTime() <= endDate.getTime()) {
            list.add(dateToStr("HH:mm", startDate));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MINUTE, interval);
            if (calendar.getTime().getTime() > endDate.getTime()) {
                if (!startDate.equals(endDate)) {
                    list.add(dateToStr("HH:mm", endDate));
                }
                startDate = calendar.getTime();
            } else {
                startDate = calendar.getTime();
            }
        }
        return list;
    }

    /**
     * 获取n个小时整点小时时间
     *
     * @param date
     * @return
     */
    public static String getNHourTime(Date date, int n) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat(patternDateTime);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) + n);
        date = ca.getTime();
        return sdf.format(date);
    }

    /**
     * 获取指定年份 第一天及 月份最后一天
     *
     * @param date
     * @return
     */
    public static Map<String, Object> getYearFirstDay(String date) {
        Map<String, Object> timeMap = new HashMap<>();
        try {
            String[] arr = date.split("-");

            // 获取当年的第一天
            SimpleDateFormat formatterDay = new SimpleDateFormat(patternDate);
            Date startTime = formatterDay.parse(arr[0] + "-01-01");
            String firstDay = formatterDay.format(startTime);
            timeMap.put("firstDay", firstDay);

            //获取当月最后一天
            Calendar cal = Calendar.getInstance();
            String yearStr = arr[0];
            int year = Integer.valueOf(yearStr);
            String monthStr = arr[1];
            Integer month = Integer.valueOf(monthStr);
            //设置年份
            cal.set(Calendar.YEAR, year);
            //设置月份
            cal.set(Calendar.MONTH, month - 1);
            //获取某月最大天数
            int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, maxDay);
            Date lastDayD = cal.getTime();
            String lastDay = formatterDay.format(lastDayD);
            timeMap.put("lastDay", lastDay);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return timeMap;
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     */
    public static int daySub(String beginDateStr, String endDateStr) {
        long day = 0;
        Date beginDate = null;
        Date endDate = null;
        try {
            SimpleDateFormat formatterDay = new SimpleDateFormat(patternDate);
            beginDate = formatterDay.parse(beginDateStr);
            endDate = formatterDay.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000) + 1;
        return (int) day;
    }

    /**
     * <li>功能描述：时间相减得到小时差值
     *
     * @param beginTime
     * @param endTime
     * @return long
     */
    public static double hourSub(String beginTime, String endTime) {
        double hour = 0.0;
        SimpleDateFormat format = new SimpleDateFormat(patternDateTime);
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(beginTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hour = ((double) endDate.getTime() - (double) beginDate.getTime()) / (60 * 60 * 1000);
        double hour2 = new BigDecimal(hour).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return hour2;
    }

    /**
     * <li>功能描述：时间相减得到分钟差值
     *
     * @param beginTime
     * @param endTime
     * @return long
     */
    public static double minuteSub(String beginTime, String endTime) {
        double minute = 0.0;
        SimpleDateFormat format = new SimpleDateFormat(patternDateTime);
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(beginTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        minute = ((double) endDate.getTime() - (double) beginDate.getTime()) / (60 * 1000);
        double minute2 = new BigDecimal(minute).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return minute2;
    }

    /**
     * <li>功能描述：时间相减得到分钟差值
     *
     * @param beginDate
     * @param endDate
     * @return long
     */
    public static double minuteSub(Date beginDate, Date endDate) {
        double minute = 0.0;
        SimpleDateFormat format = new SimpleDateFormat(patternDateTime);
        minute = ((double) endDate.getTime() - (double) beginDate.getTime()) / (60 * 1000);
        double minute2 = new BigDecimal(minute).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return minute2;
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description:(日期比较，如果s>=e 返回true 否则返回false)
     */
    public static boolean compareDate(String s, String e) {
        if (strToDate(s) == null || strToDate(e) == null) {
            return false;
        }
        return strToDate(s).getTime() >= strToDate(e).getTime();
    }

    /**
     * <li>功能描述：时间相减得到小时+分钟差值
     *
     * @param beginDate
     * @param endDate
     * @return long
     */
    public static String hourMinuteSub(Date beginDate, Date endDate) {
        double hour = 0.0;
        double minute = 0.0;
        SimpleDateFormat format = new SimpleDateFormat(patternDateTime);
        hour = ((double) endDate.getTime() - (double) beginDate.getTime()) / (60 * 60 * 1000);
        BigDecimal hour2 = new BigDecimal(hour).setScale(0, BigDecimal.ROUND_DOWN);

        minute = ((double) endDate.getTime() - (double) beginDate.getTime()) % (60 * 60 * 1000) / (60 * 1000);
        BigDecimal minute2 = new BigDecimal(minute).setScale(0, BigDecimal.ROUND_DOWN);
        return hour2 + ":" + minute2;
    }

    /**
     * <li>功能描述：时间相减得到秒钟差值
     *
     * @param beginTime
     * @param endTime
     * @return long
     */
    public static double secondSub(String beginTime, String endTime) {
        double second = 0.0;
        SimpleDateFormat format = new SimpleDateFormat(patternDateTime);
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(beginTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        second = ((double) endDate.getTime() - (double) beginDate.getTime()) / (1000);
        double second2 = new BigDecimal(second).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        return second2;
    }

    /**
     * <li>功能描述：时间相减得到秒钟差值
     *
     * @return long
     */
    public static double secondSub(Date beginDate, Date endDate) {
        double second = 0.0;
        second = ((double) endDate.getTime() - (double) beginDate.getTime()) / (1000);
        double second2 = new BigDecimal(second).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        return second2;
    }

    /**
     * 得到今日的n天之后的日期 yyyy-MM-dd
     *
     * @param n
     * @return
     */
    public static String getNDay(int n) {
        Date date = new Date();//取当前时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternDate);
        return formatter.format(date);
    }

    /**
     * 得到n天之后的日期 yyyy-MM-dd
     *
     * @param n
     * @param date
     * @return
     */
    public static String getNDay(int n, Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternDate);
        return formatter.format(date);
    }

    /**
     * 得到n天之后的日期 yyyy-MM-dd
     *
     * @param n
     * @param dateStr
     * @return
     */
    public static String getNDay(int n, String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(patternDate);
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(formatter.parse(dateStr));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, n);
        Date date = calendar.getTime();
        return formatter.format(date);
    }

    /**
     * 得到n天之后的时间 yyyy-MM-dd HH:mm:ss
     *
     * @param n
     * @return
     */
    public static String getNTime(Integer n) {
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, n); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(patternDateTime);
        String dateStr = sdfd.format(date);
        return dateStr;
    }

    /**
     * 得到n小时之后的日期 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @param n
     * @return
     */
    public static String getNHour(Date date, int n) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, n);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternDateTime);
        return formatter.format(date);
    }

    /**
     * 得到n分钟之后的日期 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @param n
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String getNMinute(Date date, int n) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, n);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternDateTime);
        return formatter.format(date);
    }

    /**
     * 得到上个月最后一天的日期
     *
     * @return
     */
    public static String getLastMonthDay() {
        SimpleDateFormat format = new SimpleDateFormat(patternDate);
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String date = format.format(cale.getTime());
        return date;
    }

    /**
     * 得到日期转化周几
     *
     * @param days
     * @return
     */
    public static String getWeekDays(String days) {
        Date date = strToDate(days);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        date = cal.getTime();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getWeekDays(String days, int n) {
        Date date = strToDate(days);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        date = cal.getTime();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取某段时间内的所有日期 (Date类型)
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> intervalDate(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 获取某段时间内的所有日期 (Sting 类型)
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<String> intervalDateStr(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat(patternDate);

        String begin = sdf.format(dBegin);
        lDate.add(begin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            String after = sdf.format(calBegin.getTime());
            lDate.add(after);
        }
        return lDate;
    }

    /**
     * 获取指定月份的所有日期
     *
     * @param month
     * @return
     */
    public static List<String> intervalMonthDateStr(int year, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat(patternDate);

        Calendar c = Calendar.getInstance();
        Date begin = null;
        Date end = null;
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, 1);
        begin = c.getTime();
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        end = c.getTime();
        return intervalDateStr(begin, end);
    }

    /**
     * 获取指定年月的第一天和最后一天日期
     *
     * @param monthDate 2018-09
     * @return
     */
    public static Map<String, String> yearFirstDayAndEndDay(String monthDate) {
        String[] split = monthDate.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        SimpleDateFormat sdf = new SimpleDateFormat(patternDate);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date begin = c.getTime();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 0);
        Date end = c.getTime();
        Map<String, String> map = new HashMap<String, String>();
        map.put("beginDate", sdf.format(begin));
        map.put("endDate", sdf.format(end));
        return map;
    }

    /**
     * 功能描述:获取系统当前所处的月份
     *
     * @param: []
     * @return: int
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取上一自然周开始 结束日期
     * type 1:上自然周 2:滚动上七日
     *
     * @return
     */
    public static Map<String, Object> getLastWeek(Integer type, Integer num) {
        Map<String, Object> resultMap = new HashMap<>();
        if (1 == type) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, 2);//当前周第一天，默认每周第一天是周日，所以这里改成每周第二天即周一为第一天
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date lastDay = calendar.getTime();
            calendar.add(Calendar.WEEK_OF_MONTH, -1);//获取上一周第一天
            calendar.set(Calendar.DAY_OF_WEEK, 2);
            Date firstDay = calendar.getTime();
            resultMap.put("firstDay", dateToStr(firstDay));
            resultMap.put("lastDay", dateToStr(lastDay));
        } else if (2 == type) {
            Calendar first = Calendar.getInstance();
            first.set(Calendar.HOUR_OF_DAY, 0);
            first.set(Calendar.MINUTE, 0);
            first.set(Calendar.SECOND, 0);
            first.add(Calendar.DATE, num);
            String firstDay = dateToStr(first.getTime());

            Calendar last = Calendar.getInstance();
            last.setTime(new Date());
            last.set(Calendar.HOUR_OF_DAY, 0);
            last.set(Calendar.MINUTE, 0);
            last.set(Calendar.SECOND, 0);
            String lastDay = dateToStr(last.getTime());

            resultMap.put("firstDay", firstDay);
            resultMap.put("lastDay", lastDay);
        }
        return resultMap;
    }

    /**
     * 解析Excel中传入的日期
     *
     * @param date
     * @return
     */
    public static Date parseExcelDate(Integer date) {
        Calendar calendar = new GregorianCalendar(1900, 0, -1);
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(patternDateTime);
        Date dd = DateUtils.addDays(d, date);
        return dd;
    }

    /**
     * 指定月份，判断季度
     *
     * @param month
     * @return
     */
    public static String checkQuarter(String month) {
        String quarter = "";
        switch (month) {
            case "01":
                quarter = "1";
                break;
            case "02":
                quarter = "1";
                break;
            case "03":
                quarter = "1";
                break;
            case "04":
                quarter = "2";
                break;
            case "05":
                quarter = "2";
                break;
            case "06":
                quarter = "2";
                break;
            case "07":
                quarter = "3";
                break;
            case "08":
                quarter = "3";
                break;
            case "09":
                quarter = "3";
                break;
            case "10":
                quarter = "4";
                break;
            case "11":
                quarter = "4";
                break;
            case "12":
                quarter = "4";
        }
        return quarter;
    }

    /**
     * 获取当年 某季度的第一天和最后一天
     *
     * @param num 第几季度
     */
    public static String[] currQuarter(int num) {
        String[] s = new String[2];
        String str = "";
        // 设置本年的季
        Calendar quarterCalendar = null;
        switch (num) {
            case 1: // 本年到现在经过了一个季度，在加上前4个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 3);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = dateToStr(patternDate, quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "01-01";
                s[1] = str;
                break;
            case 2: // 本年到现在经过了二个季度，在加上前三个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 6);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = dateToStr(patternDate, quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "04-01";
                s[1] = str;
                break;
            case 3:// 本年到现在经过了三个季度，在加上前二个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 9);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = dateToStr(patternDate, quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "07-01";
                s[1] = str;
                break;
            case 4:// 本年到现在经过了四个季度，在加上前一个季度
                quarterCalendar = Calendar.getInstance();
                str = dateToStr(patternDate, quarterCalendar.getTime());
                s[0] = str.substring(0, str.length() - 5) + "10-01";
                s[1] = str.substring(0, str.length() - 5) + "12-31";
                break;
        }
        return s;
    }

    /**
     * 获取去年年份
     *
     * @return
     */
    public static String lastYear() {
        SimpleDateFormat formats = new SimpleDateFormat("yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        Date date = c.getTime();
        return formats.format(date);
    }

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, patternDateTimeFull);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

}
