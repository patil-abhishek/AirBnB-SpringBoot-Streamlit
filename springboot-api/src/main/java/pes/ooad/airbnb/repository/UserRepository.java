package pes.ooad.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pes.ooad.airbnb.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);
}
