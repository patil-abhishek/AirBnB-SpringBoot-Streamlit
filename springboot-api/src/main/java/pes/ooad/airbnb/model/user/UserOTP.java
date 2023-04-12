package pes.ooad.airbnb.model.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOTP {
    private Integer user_id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private Integer inputOTP;
}
