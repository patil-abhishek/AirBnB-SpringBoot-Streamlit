package pes.ooad.airbnb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pes.ooad.airbnb.model.User;
import pes.ooad.airbnb.principal.CurrentUser;
import pes.ooad.airbnb.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean verifyLogin(String loginEmail, String loginPassword) {
        if (userRepository.findByEmail(loginEmail).isPresent() && userRepository.findByEmail(loginEmail).get().getPassword() == loginPassword)
        {
            CurrentUser.user = userRepository.findByEmail(loginEmail).get();
            return true;
        }
        else
            return false;
    }

    public boolean checkUser(String registerEmail, String registerPhone) {
        if(userRepository.findByEmail(registerEmail).isPresent() || userRepository.findByPhone(registerPhone).isPresent())
            return false;
        else
            return true;
    }

    public void addUserToDB(User user) {
        CurrentUser.user = userRepository.save(user);
    }
}
