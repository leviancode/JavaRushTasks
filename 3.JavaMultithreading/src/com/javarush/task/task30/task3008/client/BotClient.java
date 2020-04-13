package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (!message.contains(": ")) return;

            String[] splitMessage = message.split(": ");
            String userName = splitMessage[0];
            String command = splitMessage[1].trim();
            String botResponse = createAnswer(command);
            if (botResponse != null) {
                sendTextMessage(String.format("Информация для %s: %s", userName, botResponse));
            }
        }

        private String createAnswer(String command) {
            Map<String, SimpleDateFormat> commandMap = new HashMap<>();
            commandMap.put("дата", new SimpleDateFormat("d.MM.YYYY", Locale.ENGLISH));
            commandMap.put("день", new SimpleDateFormat("d", Locale.ENGLISH));
            commandMap.put("месяц", new SimpleDateFormat("MMMM", Locale.ENGLISH));
            commandMap.put("год", new SimpleDateFormat("YYYY", Locale.ENGLISH));
            commandMap.put("время", new SimpleDateFormat("H:mm:ss", Locale.ENGLISH));
            commandMap.put("час", new SimpleDateFormat("H", Locale.ENGLISH));
            commandMap.put("минуты", new SimpleDateFormat("m", Locale.ENGLISH));
            commandMap.put("секунды", new SimpleDateFormat("s", Locale.ENGLISH));

            Calendar calendar = Calendar.getInstance();
            String response = null;
            if (commandMap.containsKey(command)){
                response = commandMap.get(command).format(calendar.getTime());
            }
           return response;
        }
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
