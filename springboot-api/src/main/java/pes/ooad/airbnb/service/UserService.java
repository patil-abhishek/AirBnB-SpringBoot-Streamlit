package pes.ooad.airbnb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.converter.PropertyConverter;
import pes.ooad.airbnb.model.property.Property;
import pes.ooad.airbnb.model.property.PropertyDisplay;
import pes.ooad.airbnb.model.user.LoginCredentials;
import pes.ooad.airbnb.model.user.User;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.PropertyRepository;
import pes.ooad.airbnb.repository.UserRepository;
import pes.ooad.airbnb.util.Helpers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public boolean verifyLogin(String email, String password) {
        if (userRepository.findByEmail(email).isPresent() &&
                Objects.equals(userRepository.findByEmail(email).get().getPassword(), password))
        {
            CurrentUser.user_id = userRepository.findByEmail(email).get().getUser_id();
            return true;
        }
        else
            return false;
    }

    public boolean checkUser(String registerEmail, String registerPhone) {
        return userRepository.findByEmail(registerEmail).isEmpty()
                && userRepository.findByPhone(registerPhone).isEmpty();
    }

    public void addUserToDB(User user) {
        userRepository.save(user);
    }


    public void updateUser(User user) {
        User userToUpdate = userRepository.findById(CurrentUser.user_id).get();
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setImage(user.getImage());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setAge(user.getAge());
        userRepository.save(userToUpdate);
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).get();
    }

    public void addPropertyToFav(Integer property_id) {
        userRepository.findById(CurrentUser.user_id).get().getFavoriteProperties().add(
                propertyRepository.findById(property_id).get()
        );
    }

    public void removePropertyFav(Integer property_id) {
        userRepository.findById(CurrentUser.user_id).get().getFavoriteProperties().remove(
                propertyRepository.findById(property_id).get()
        );
    }

}