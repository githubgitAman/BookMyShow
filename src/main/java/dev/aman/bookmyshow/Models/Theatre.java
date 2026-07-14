package dev.aman.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    @ManyToOne
    private Regions region;
    private String theatreName;
    @OneToMany
    private List<Screen> screen;
}
