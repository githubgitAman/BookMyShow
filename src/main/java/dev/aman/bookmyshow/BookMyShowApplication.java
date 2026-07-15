package dev.aman.bookmyshow;

import dev.aman.bookmyshow.Controllers.BookingController;
import dev.aman.bookmyshow.Controllers.UserController;
import dev.aman.bookmyshow.DTOs.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
//To use CLI in Spring we use CommandLineRunner
public class BookMyShowApplication implements CommandLineRunner {
    @Autowired
    private UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    //All context is available in run method not in main
    @Override
    public void run(String... args) throws Exception {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your name: ");
        String name = scanner.next();
        System.out.println("Please enter your email: ");
        String username = scanner.next();
        System.out.println("Please enter your password: ");
        String password = scanner.next();

        userRequestDTO.setUsername(username);
        userRequestDTO.setPassword(password);
        userRequestDTO.setName(name);

        userController.signUp(userRequestDTO);
    }
}
