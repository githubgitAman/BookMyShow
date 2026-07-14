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

### 1. Temporary Seat Locking (Distributed Cache)
When a user selects a seat and proceeds to payment, the system applies a **Distributed Lock (e.g., Redis)** for a fixed TTL (Time-To-Live), typically 10 minutes. 
* **State Change:** The seat status changes from `AVAILABLE` to `LOCKED`.
* **Exclusivity:** If User B tries to select a seat locked by User A, the system immediately rejects the request.
* **Timeout:** If User A's payment fails or times out, the lock expires, and the seat reverts to `AVAILABLE`.

### 2. Database-Level Isolation (Pessimistic Locking)
For the final transaction commit, row-level locking ensures absolute data consistency.
* During the final booking transaction, a `SELECT ... FOR UPDATE` query is executed.
* This guarantees that even if cache-level locks fail, the database prevents concurrent transactions from modifying the same seat row simultaneously. 
* **Optimistic Locking** (using version numbers) is used for less critical read/write entities to maintain high throughput.
