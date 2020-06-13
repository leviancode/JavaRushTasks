package com.javarush.task.task39.task3913;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class LogEntry implements Comparable<LogEntry>{
    private String ip;
    private String userName;
    private Date date;
    private SimpleDateFormat formatter;  // dd.MM.yyyy HH:mm:ss
    private Event event;
    private int taskNumber;       // only for event SOLVE_TASK and DONE_TASK
    private Status status;

    public LogEntry(String ip, String userName, Date date, Event event, int taskNumber, Status status) {
        this.ip = ip;
        this.userName = userName;
        this.date = date;
        this.event = event;
        this.taskNumber = taskNumber;
        this.status = status;
        formatter = new SimpleDateFormat();
        formatter.applyPattern("dd.MM.yyyy HH:mm:ss");
    }

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return userName;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public Status getStatus() {
        return status;
    }

    public Object universalGetter(String fieldName){
        String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        try {
            Method method = getClass().getMethod(methodName);
            return method.invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return ip + '\t'
                + userName + '\t'
                + formatter.format(date) + '\t'
                + event.toString()
                + (taskNumber == 0 ? '\t' : " " + taskNumber + '\t')
                + status.toString() + '\n';
    }

    @Override
    public int compareTo(LogEntry logEntry) {
        return date.compareTo(logEntry.date);
    }
}
