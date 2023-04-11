package pes.ooad.airbnb.converter;

import pes.ooad.airbnb.model.User;
import pes.ooad.airbnb.model.UserOTP;

public class UserConverter {
    public static User userOTPtoUser(UserOTP userOTP) {
        User user = new User();
        user.setFirstname(userOTP.getFirstname());
        user.setLastname(userOTP.getLastname());
        user.setPhone(userOTP.getPhone());
        user.setEmail(userOTP.getEmail());
        user.setPassword(userOTP.getPassword());
        return user;
    }

}
