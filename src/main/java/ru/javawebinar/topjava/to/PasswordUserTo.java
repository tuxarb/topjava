package ru.javawebinar.topjava.to;


import javax.validation.constraints.Size;

public class PasswordUserTo {
    @Size(min = 5, max = 20, message = " {error.userTo.password.size}")
    private String oldPassword;

    @Size(min = 5, max = 20, message = " {error.userTo.password.size}")
    private String newPassword;


    public PasswordUserTo() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
