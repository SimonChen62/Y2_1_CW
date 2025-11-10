package com.movie.user;

public interface IUser {
    boolean login(String username, String password);
    boolean register(String username, String password1, String password2);
    boolean changePassword(String oldPassword, String newPassword1, String newPassword2);
}
