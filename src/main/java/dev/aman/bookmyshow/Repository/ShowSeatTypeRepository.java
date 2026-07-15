package dev.aman.bookmyshow.Repository;

import dev.aman.bookmyshow.Models.Show;
import dev.aman.bookmyshow.Models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);
}
