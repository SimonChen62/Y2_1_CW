package com.movie.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDataStorage {

    static String Path="data/users.csv";
    //加载csv
    public static void loadFromFile(Map<String, User> users) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Path));//加载
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                String password = data[1];

                List<String> watchlist = new ArrayList<>(List.of(data[2].split(";")));
                List<String> history = new ArrayList<>(List.of(data[3].split(";")));

                User user = new User(username, password);
                user.setWatchlist(watchlist);
                user.setHistory(history);

                users.put(username, user);//用户数据存入hashmap
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("加载用户数据失败：" + e.getMessage());//打印完整信息
        }
    }

    //保存编辑后的csv
    public static void saveToFile(Map<String, User> users) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Path));
            writer.write("username,password,watchlist,history\n");
            for (Map.Entry<String, User> entry : users.entrySet()) {
                User user = entry.getValue();//获取每一项,然后依次存入
                String watchlistStr = String.join(";", user.getWatchlist());
                String historyStr = String.join(";", user.getHistory());

                writer.write(user.getUsername() + "," + user.getPassword() + "," + watchlistStr + "," + historyStr + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("保存用户数据失败：" + e.getMessage());
        }

    }
}


