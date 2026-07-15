package dev.aman.bookmyshow.Services;

import dev.aman.bookmyshow.Exceptions.ShowNotFoundException;
import dev.aman.bookmyshow.Exceptions.UserNotFoundException;
import dev.aman.bookmyshow.Models.*;
import dev.aman.bookmyshow.Repository.BookingRepository;
import dev.aman.bookmyshow.Repository.ShowRepository;
import dev.aman.bookmyshow.Repository.ShowSeatRepository;
import dev.aman.bookmyshow.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final PriceCalculatorService priceCalculatorService;
    private final BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository, ShowRepository showRepository
    , ShowSeatRepository showSeatRepository, PriceCalculatorService priceCalculatorService,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    //TODO : starting transaction at method level but can also start in between as mentioned below in comments
    @Transactional
    public Booking issueTicket(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException {
        /*
            1. Get the user details from the DB
            2. Get the show details from the DB
            ----------- START TRANSACTION ----------------
            3. Get the show seats from the DB
            4. Check if the seats are available
            5. If not, throw exception
            6. If yes, change the status to locked and update lockedAt with current time
            ----------- END TRANSACTION ------------------
            7. Return booking object
         */
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User with given id " + userId + "not found");
        }

        User bookedBy = userOptional.get();
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()) {
            throw new ShowNotFoundException("Show with given id " + showId + "not found");
        }

        Show show = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllByShowIdIn(showSeatIds);

        for(ShowSeat showSeat : showSeats) {
            boolean isAvailable = showSeat.getShowSeatStatus() == SHOWSEATSTATUS.AVAILABLE;
            boolean isActivelyBlocked = showSeat.getShowSeatStatus() == SHOWSEATSTATUS.BLOCKED
                    //Checking time between lockedAt instance if less then 15 minutes
                    && Duration.between(showSeat.getLockedAt().toInstant(), Instant.now()).toMinutes() > 15;

            if(!(isAvailable || isActivelyBlocked)) {
                throw new RuntimeException(); //TODO : show throw custom exception here
            }
        }

        //If the seat is available or blocked but lockedAt is > 15 min then we book the seats
        Date currentDate = new Date();
        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(SHOWSEATSTATUS.BLOCKED);
            //Every ticket should have same time
            showSeat.setLockedAt(currentDate);
            showSeatRepository.save(showSeat);
        }

        Booking booking = new Booking();
        booking.setBookingStatus(BOOKINGSTATUS.PENDING);
        booking.setUser(bookedBy);
        booking.setBookingDate(new Date());
        booking.setShowSeats(showSeats);
        booking.setShow(show);
        booking.setAmount(priceCalculatorService.calculatePrice(show, showSeats));

        //Saving to DB
        return bookingRepository.save(booking);
    }
}
