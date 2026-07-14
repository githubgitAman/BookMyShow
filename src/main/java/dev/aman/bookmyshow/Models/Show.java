package dev.aman.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel{
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Screen screen;
    @ManyToOne
    private Movie movie;
    @Enumerated(EnumType.ORDINAL)
    //Using @ElementCollection Spring JPA will create a mapping table in DB
    @ElementCollection
    private List<FEATURES> features;
}
