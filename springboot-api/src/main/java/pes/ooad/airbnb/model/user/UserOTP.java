package pes.ooad.airbnb.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserOTP {
    private Integer user_id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String phone;
    private String email;
    private String password;
    private Integer inputOTP;
}
