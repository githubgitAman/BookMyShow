package dev.aman.bookmyshow.Controllers;

import dev.aman.bookmyshow.DTOs.UserRequestDTO;
import dev.aman.bookmyshow.Exceptions.UserNotFoundException;
import dev.aman.bookmyshow.Models.User;
import dev.aman.bookmyshow.Services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String signUp(UserRequestDTO userRequestDTO) throws UserNotFoundException {
        User user = userService.signUp(userRequestDTO.getName()
                , userRequestDTO.getUsername()
                , userRequestDTO.getPassword());

        if(user == null) {
            throw new UserNotFoundException("User not found");
        }

        return "User SignUp Successfull";
    }
}

