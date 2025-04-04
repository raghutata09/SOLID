//Concurrency we can use Optimistic lock that works on version read 
which uses synchronised block. Don=t use Pessimistick lock
jo read krte hi immediate lock krde.
see LLD 14 if any doubt is there



User

MovieController
Map<City,List<Movie>>
List<Movie>


Movie
-id
-name
-duration

TheaterController
Map<City,List<Theater>>
List<Theater>


Theater
List<Movie>
City
List<Screen> // Audi 1, Audi 2
List<Show> // 



Screen
int id
List<Seat>


Booking
-id
-Show show
-List<Seat>
-Payment


Payment
-id
-status

Seat
-Type of Seat - Silver,. Gold, Recliner
-int row
-int id
-int price

Show
showId
Movie movie
Screen screen
int startTime
List<int> bookedSeats;

