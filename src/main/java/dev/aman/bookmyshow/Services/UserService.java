package dev.aman.bookmyshow.Services;

import dev.aman.bookmyshow.Exceptions.UserNotFoundException;
import dev.aman.bookmyshow.Models.User;
import dev.aman.bookmyshow.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String name, String username, String password) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isPresent()) {
            throw new UserNotFoundException("User with given username " + username + " already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(username);
        //TODO : We should encrypt password before saving in DB
        user.setPassword(password);

        return userRepository.save(user);
    }
}
