package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<LogEntry> logEntries;
    private SimpleDateFormat dateFormat;
    private static final String LOG_REGEX = "(\\d+\\.\\d+\\.\\d+\\.\\d+)(\t|\\s+)(.+)(\t|\\s+)(\\d+\\.\\d+\\.\\d+\\s+\\d+:\\d+:\\d+)(\t|\\s+)(\\w+_?\\w*)(\\s\\d+)?(\t|\\s+)(\\w+)";
    private static final String DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";

    public LogParser(Path logDir) {
        this.logDir = logDir;
        logEntries = new ArrayList<>();
        dateFormat = new SimpleDateFormat(DATE_PATTERN);
        List<String> logs = readLogFiles();
        parseStrings(logs);
    }

    private List<String> readLogFiles() {
        List<String> logStrings = new ArrayList<>();
        try {
            List<Path> paths = Files.list(logDir)
                    .filter(path -> path.getFileName().toString().endsWith(".log"))
                    .collect(Collectors.toList());
            for (Path path : paths) {
                logStrings.addAll(Files.readAllLines(path));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return logStrings;
    }

    private void parseStrings(List<String> logs) {
        for (String log : logs) {
            try {
                LogEntry logEntry = parseLog(log);
                logEntries.add(logEntry);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private LogEntry parseLog(String line) throws ParseException {
        Pattern pattern = Pattern.compile(LOG_REGEX);
        Matcher matcher = pattern.matcher(line);
        LogEntry logEntry = null;
        while (matcher.find()) {
            String ip = matcher.group(1).trim();
            String name = matcher.group(3).trim();
            Date date = dateFormat.parse(matcher.group(5).trim());
            Event event = Event.valueOf(matcher.group(7).trim());
            int numberOfTask = (matcher.group(8) != null ? Integer.parseInt(matcher.group(8).trim()) : 0);
            Status status = Status.valueOf(matcher.group(10).trim());
            logEntry = new LogEntry(ip, name, date, event, numberOfTask, status);
        }
        return logEntry;
    }

    private Stream<LogEntry> getLogStreamForTimePeriod(Date after, Date before) {
        return logEntries.stream()
                .filter(logEntry -> {
                    if (after != null && before != null)
                        return logEntry.getDate().after(after) && logEntry.getDate().before(before);
                    else if (before != null)
                        return logEntry.getDate().before(before);
                    else if (after != null)
                        return logEntry.getDate().after(after);
                    else return true;
                });
    }

    private Stream<LogEntry> getFilteredLogStream(String fieldName, String value, Date after, Date before) {
        return getLogStreamForTimePeriod(after, before)
                .filter(logEntry -> {
                    Object o = logEntry.universalGetter(fieldName);
                    if (fieldName.equals("date")) {
                        try {
                            Date date = (Date) o;
                            return date.equals(dateFormat.parse(value));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    return o.toString().equals(value);
                });
    }

    private Stream<LogEntry> getLogStreamForIP(String ip, Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).filter(logEntry -> logEntry.getIp().equals(ip));
    }

    private Stream<LogEntry> getLogStreamForUser(String user, Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).filter(logEntry -> logEntry.getUser().equalsIgnoreCase(user));
    }

    private Stream<LogEntry> getLogStreamForEvent(Event event, Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).filter(logEntry -> logEntry.getEvent().equals(event));
    }

    private Stream<LogEntry> getLogStreamForStatus(Status status, Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).filter(logEntry -> logEntry.getStatus().equals(status));
    }


    @Override
    public Set<String> getAllUsers() {
        return logEntries.stream().map(LogEntry::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).map(LogEntry::getEvent).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).map(LogEntry::getIp).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getLogStreamForUser(user, after, before).map(LogEntry::getIp).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getLogStreamForEvent(event, after, before).map(LogEntry::getIp).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getLogStreamForStatus(status, after, before).map(LogEntry::getIp).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).map(LogEntry::getUser).collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getLogStreamForUser(user, after, before).map(LogEntry::getEvent).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getLogStreamForIP(ip, after, before).map(LogEntry::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getLogStreamForEvent(Event.LOGIN, after, before).map(LogEntry::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getLogStreamForEvent(Event.DOWNLOAD_PLUGIN, after, before).map(LogEntry::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getLogStreamForEvent(Event.WRITE_MESSAGE, after, before).map(LogEntry::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getLogStreamForEvent(Event.SOLVE_TASK, after, before).map(LogEntry::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getLogStreamForEvent(Event.SOLVE_TASK, after, before)
                .filter(logEntry -> logEntry.getTaskNumber() == task)
                .map(LogEntry::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getLogStreamForEvent(Event.DONE_TASK, after, before).map(LogEntry::getUser).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getLogStreamForEvent(Event.DONE_TASK, after, before)
                .filter(logEntry -> logEntry.getTaskNumber() == task)
                .map(LogEntry::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getLogStreamForUser(user, after, before)
                .filter(logEntry -> logEntry.getEvent().equals(event))
                .map(LogEntry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getLogStreamForStatus(Status.FAILED, after, before).map(LogEntry::getDate).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getLogStreamForStatus(Status.ERROR, after, before).map(LogEntry::getDate).collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return getLogStreamForUser(user, after, before)
                .filter(logEntry -> logEntry.getEvent().equals(Event.LOGIN))
                .map(LogEntry::getDate)
                .min(Date::compareTo).orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return getLogStreamForUser(user, after, before)
                .filter(logEntry -> logEntry.getEvent().equals(Event.SOLVE_TASK))
                .filter(logEntry -> logEntry.getTaskNumber() == task)
                .map(LogEntry::getDate)
                .min(Date::compareTo).orElse(null);

    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return getLogStreamForUser(user, after, before)
                .filter(logEntry -> logEntry.getEvent().equals(Event.DONE_TASK))
                .filter(logEntry -> logEntry.getTaskNumber() == task)
                .map(LogEntry::getDate)
                .min(Date::compareTo).orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getLogStreamForUser(user, after, before)
                .filter(logEntry -> logEntry.getEvent().equals(Event.WRITE_MESSAGE))
                .map(LogEntry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getLogStreamForUser(user, after, before)
                .filter(logEntry -> logEntry.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                .map(LogEntry::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getLogStreamForTimePeriod(after, before).map(LogEntry::getEvent).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getLogStreamForIP(ip, after, before).map(LogEntry::getEvent).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getLogStreamForUser(user, after, before).map(LogEntry::getEvent).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getLogStreamForStatus(Status.FAILED, after, before).map(LogEntry::getEvent).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getLogStreamForStatus(Status.ERROR, after, before).map(LogEntry::getEvent).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getLogStreamForEvent(Event.SOLVE_TASK, after, before)
                .filter(logEntry -> logEntry.getTaskNumber() == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getLogStreamForEvent(Event.DONE_TASK, after, before)
                .filter(logEntry -> logEntry.getTaskNumber() == task)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getLogStreamForEvent(Event.SOLVE_TASK, after, before)
                .collect(Collectors.toMap(LogEntry::getTaskNumber, logEntry -> 1, Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getLogStreamForEvent(Event.DONE_TASK, after, before)
                .collect(Collectors.toMap(LogEntry::getTaskNumber, logEntry -> 1, Integer::sum));
    }

    // get field1 for field2 = "value1" and date between "after" and "before"
    @Override
    public Set<Object> execute(String query) {
        final String QUERY_PATTERN = "get (?<field1>\\w+)" +
                "( for (?<field2>\\w+) = \"(?<value>.+?)\")?" +
                "( and date between \"(?<after>.+)\" and \"(?<before>.+)\")?";
        Matcher matcher = Pattern.compile(QUERY_PATTERN).matcher(query);
        String field1 = null;
        String field2 = null;
        String value = null;
        Date after = null;
        Date before = null;
        if (matcher.find()) {
            try {
                field1 = matcher.group("field1");
                field2 = matcher.group("field2");
                value = matcher.group("value");
                after = dateFormat.parse(matcher.group("after"));
                before = dateFormat.parse(matcher.group("before"));
            } catch (Exception ignored) {}
        }
        return collectSet(field1, field2, value, after, before);
    }

    private Set<Object> collectSet(String field1, String field2, String value, Date after, Date before) {
        Stream<LogEntry> stream;
        if (field2 != null)
            stream = getFilteredLogStream(field2, value, after, before);
        else
            stream = logEntries.stream();
        return stream.map(logEntry -> logEntry.universalGetter(field1)).collect(Collectors.toSet());
    }
}