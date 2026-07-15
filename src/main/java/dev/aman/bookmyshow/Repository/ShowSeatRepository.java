package dev.aman.bookmyshow.Repository;

import dev.aman.bookmyshow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findAllByShowIdIn(List<Long> showId);
}
