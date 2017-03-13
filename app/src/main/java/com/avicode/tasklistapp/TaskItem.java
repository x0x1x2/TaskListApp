package com.avicode.tasklistapp;

/**
 * Created by T_YoramS1 on 06/03/2017.
 */

public class TaskItem {
    private String titleStr;
    private String dateStr;
    private String detailsStr;

    public TaskItem(String title,String date, String details) {
        titleStr = title;
        dateStr = date;
        detailsStr = details;
    }
    public String toString() {
        return String.format("%s %s%s%s", dateStr, titleStr, System.getProperty("line.separator"), detailsStr);
    }


}
