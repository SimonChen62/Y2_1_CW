package com.movie.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements IUser {
    //实现登录注册，修改密码，并提供保存账户方法

    //用户私有变量,其中历史和观看列表都使用list，hash意义不大
    private String username;
    private String password;
    private List<String> watchlist; // 用户的电影收藏列表
    private List<String> history;   // 用户的观看历史
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser;

    //只要开始就自动加载
    static {
        UserDataStorage.loadFromFile(users);
    }

    //构造方法
    public User() {}

    public User(String name, String password) {
        this.username = name;
        this.password = password;
        this.watchlist = new ArrayList<>();
        this.history = new ArrayList<>();
    }
    // 获取用户名
    public String getUsername() {
        return username;
    }

    // 获取当前登录用户
    public static User getCurrentUser() {
        return currentUser;
    }

    // 设置当前登录用户
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // 获取密码
    public String getPassword() {
        return password;
    }

    // 修改密码
    public void setPassword(String password) {
        this.password = password;
    }



    public void setWatchlist(List<String> watchlist) {
        this.watchlist = watchlist;
    }

    //返回watchlist
    public List<String> getWatchlist() {
        return watchlist;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    // 返回history
    public List<String> getHistory() {
        return history;
    }

    //退出登录
    public static void logout() {
        currentUser = null; //没有存
        UserDataStorage.saveToFile(users);
    }

    //登录，判断是否有这个人，有的话并且密码正确返回t，没有或者密码错误返回f，并且设置登录信息
    @Override
    public boolean login(String username, String password) {
        if(users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            setCurrentUser(users.get(username)); //将currentuser设置成登陆的
            //users就是hash，因为是引用类型，后面就不用重复操作了
            return true;
        }
        return false;
    }

    //注册新用户
    @Override
    public boolean register(String username, String password1, String password2) {
        if(users.containsKey(username)){
            return false;//用户已存在，返回错误
        }
        if(password1.equals(password2)) {
            User newUser = new User(username, password1); //确保密码输入无误，双重验证
            users.put(username, newUser);
            UserDataStorage.saveToFile(users);
            return true;
        }
        return false;
    }

    //修改密码
    @Override
    public boolean changePassword(String oldPassword, String newPassword1, String newPassword2) {
        if(currentUser != null) {
            if(currentUser.getPassword().equals(oldPassword)) {
                if(newPassword2.equals(newPassword1)) {
                    currentUser.setPassword(newPassword2);
                    UserDataStorage.saveToFile(users);
                    return true;
                }
            }
        }
        return false;
    }



}
