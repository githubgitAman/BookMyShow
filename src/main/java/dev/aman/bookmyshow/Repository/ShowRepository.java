package dev.aman.bookmyshow.Repository;

import dev.aman.bookmyshow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    Optional<Show> findById(Long showId);
}
