package dev.aman.bookmyshow.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    @Column(name = "row_no")
    private int rowNumber;
    private int columnNumber;
    @OneToOne
    private SeatType seatType;
    private int seatNumber;
}
