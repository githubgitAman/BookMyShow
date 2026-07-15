package dev.aman.bookmyshow.Services;

import dev.aman.bookmyshow.Models.Show;
import dev.aman.bookmyshow.Models.ShowSeat;
import dev.aman.bookmyshow.Models.ShowSeatType;
import dev.aman.bookmyshow.Repository.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class PriceCalculatorService {
//   TODO : We are assuming that our System has price calculation strategy
    private final ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public double calculatePrice(Show show, List<ShowSeat> showSeats) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        double amount = 0.0;
        for(ShowSeat showSeat : showSeats){
            for(ShowSeatType showSeatType : showSeatTypes){
                //Getting seat type of each showSeat
                //We are using SeatType as class
                if(showSeat.getSeat().getSeatType()
                        .equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                }
            }
        }

        return 0.0;
    }
}
