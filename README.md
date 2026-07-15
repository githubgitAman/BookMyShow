# BookMyShow - Movie Ticket Booking System

A highly scalable backend service for a movie ticket booking platform, handling end-to-end user journeys and robust concurrent seat booking.

## 🚀 The User Journey

1. **City Selection:** Users select their current or preferred city to filter available shows.
2. **Movie Discovery:** Browse active movies playing in the selected city.
3. **Theatre & Show Selection:** Choose a specific cinema and a time slot for the selected movie.
4. **Seat Selection:** View the real-time seating arrangement and select available seats.
5. **Checkout & Payment:** Lock the selected seats temporarily and complete the payment process. 
6. **Ticket Generation:** Receive a confirmed booking ID and ticket details.

## ✨ Key Features
* **Location-based Filtering:** Fast retrieval of movies and theatres based on city.
* **Real-time Seat Matrix:** Accurate representation of available, locked, and booked seats.
* **Payment Gateway Integration:** Secure payment processing and transaction state handling.
* **Scalable Architecture:** Designed to handle high traffic and concurrent booking requests.

## 🔒 Handling Concurrency (Seat Locking Mechanism)

To prevent the "double-booking" problem where multiple users attempt to book the exact same seat simultaneously, this system implements a strict concurrency control mechanism:

🔒 Handling Concurrency (Seat Locking Mechanism)
1. To prevent the "double-booking" problem where multiple users attempt to book the exact same seat simultaneously, this system implements a strict concurrency control mechanism using Spring's @Transactional annotation.
2. This ensures that the seat locking and booking database operations are executed within a single, isolated transaction. If two users try to book the same seat, the transaction isolation prevents both from succeeding, rolling back the overlapping request and maintaining data integrity.
