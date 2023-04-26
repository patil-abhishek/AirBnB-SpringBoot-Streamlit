package pes.ooad.airbnb.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pes.ooad.airbnb.model.property.Property;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserProfile {
    private String firstname;
    private String lastname;
    private Integer age;
    private String phone;
    private String email;
    private String password;
}
