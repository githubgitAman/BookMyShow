package dev.aman.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    //Using ORDINAL Spring JPA will add numbers inside database instead of complete strings for ENUMS
    //Issue is that if we change the order in ENUMS then whole data will be corrupted in DB
    @Enumerated(EnumType.ORDINAL)
    private BOOKINGSTATUS bookingStatus;
    @ManyToOne
    private User user;
    private Date bookingDate;
    private double amount;
    @ManyToOne
    private Show show;
    //If user cancels then same show seat can be booked again
    @ManyToMany
    private List<ShowSeat> showSeats;
    @OneToMany
    private List<Payment> payments;
}
