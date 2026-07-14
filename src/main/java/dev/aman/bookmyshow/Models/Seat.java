package dev.aman.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private int rowNumber;
    private int columnNumber;
    @OneToOne
    private SeatType seatType;
    private int seatNumber;
}
