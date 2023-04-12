package pes.ooad.airbnb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.model.user.LoginCredentials;
import pes.ooad.airbnb.model.user.User;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.UserRepository;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean verifyLogin(LoginCredentials loginCredentials) {
        String loginEmail = loginCredentials.getLoginEmail();
        String loginPassword = loginCredentials.getLoginPassword();
        if (userRepository.findByEmail(loginEmail).isPresent() &&
                Objects.equals(userRepository.findByEmail(loginEmail).get().getPassword(), loginPassword))
        {
            CurrentUser.user = userRepository.findByEmail(loginEmail).get();
            return true;
        }
        else
            return false;
    }

    public boolean checkUser(String registerEmail, String registerPhone) {
        return userRepository.findByEmail(registerEmail).isEmpty() && userRepository.findByPhone(registerPhone).isEmpty();
    }

    public void addUserToDB(User user) {
        CurrentUser.user = userRepository.save(user);
    }
}