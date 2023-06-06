package rest.TicketBooking.services;

import rest.TicketBooking.model.Transportation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransportationFilterStrategy <T extends Transportation>{

    List<T> filterByDateAndTimeOfDeparture(LocalDateTime date_and_time_of_departure);
    List<T> filterByDateAndTimeOfArrival(LocalDateTime date_and_time_of_arrival);
    List<T> filterByDepartureCity(String departure_city);
    List<T> filterByArrivalCity(String arrival_city);
    List<T> filterByDateAndCity(LocalDateTime date_and_time_of_departure,
                                LocalDateTime date_and_time_of_arrival,
                                String departure_city,
                                String arrival_city);
    List<T> filterByPrice(BigDecimal price);

}
