package com.movie.user;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请选择操作:");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline character

            if (choice == 1) {
                // 登录
                System.out.print("请输入用户名: ");
                String username = scanner.nextLine();
                System.out.print("请输入密码: ");
                String password = scanner.nextLine();

                boolean loggedIn = new User(username, password).login(username, password);
                if (loggedIn) {
                    System.out.println("登录成功! 欢迎 " + username);
                    loggedInOperations();
                    break;
                } else {
                    System.out.println("登录失败，用户名或密码错误。");
                }

            } else if (choice == 2) {
                // 注册
                System.out.print("请输入用户名: ");
                String username = scanner.nextLine();
                System.out.print("请输入密码: ");
                String password1 = scanner.nextLine();
                System.out.print("请再次输入密码: ");
                String password2 = scanner.nextLine();

                boolean registered = new User(username,password1).register(username, password1, password2);
                if (registered) {
                    System.out.println("注册成功!");
                } else {
                    System.out.println("注册失败，密码不一致或用户名已存在。");
                }

            } else if (choice == 3) {
                // 退出
                System.out.println("退出系统。");
                User.logout();
                break;
            } else {
                System.out.println("无效选择，请重新输入。");
            }
        }
        scanner.close();
    }

    // 登录后用户操作
    private static void loggedInOperations() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("请选择操作:");
            System.out.println("1. 修改密码");
            System.out.println("2. 查看收藏夹");
            System.out.println("3. 查看观看历史");
            System.out.println("4. 登出");
            System.out.println("5. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                // 修改密码
                System.out.print("请输入旧密码: ");
                String oldPassword = scanner.nextLine();
                System.out.print("请输入新密码: ");
                String newPassword1 = scanner.nextLine();
                System.out.print("请再次输入新密码: ");
                String newPassword2 = scanner.nextLine();

                boolean passwordChanged = User.getCurrentUser().changePassword( oldPassword, newPassword1,newPassword2);
                if (passwordChanged) {
                    System.out.println("密码修改成功!");
                } else {
                    System.out.println("密码修改失败，用户名或旧密码错误。");
                }

            } else if (choice == 2) {
                // 查看收藏夹
                System.out.println("您的收藏夹: " + String.join(", ", User.getCurrentUser().getWatchlist()));

            } else if (choice == 3) {
                // 查看观看历史
                System.out.println("您的观看历史: " + String.join(", ", User.getCurrentUser().getHistory()));

            } else if (choice == 4) {
                // 登出
                User.logout();
                System.out.println("您已成功登出。");
                loggedIn = false;

            } else if (choice == 5) {
                // 退出
                System.out.println("退出系统。");
                loggedIn = false;
                User.logout();
                break;
            } else {
                System.out.println("无效选择，请重新输入。");
            }
        }
    }
}