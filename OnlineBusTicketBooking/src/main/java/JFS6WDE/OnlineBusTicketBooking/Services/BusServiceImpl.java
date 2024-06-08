package JFS6WDE.OnlineBusTicketBooking.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.OnlineBusTicketBooking.Entities.Bus;
import JFS6WDE.OnlineBusTicketBooking.Entities.Route;
import JFS6WDE.OnlineBusTicketBooking.Exception.AdminException;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;
import JFS6WDE.OnlineBusTicketBooking.Repository.BusRepository;
import JFS6WDE.OnlineBusTicketBooking.Repository.RouteRepository;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepo;
    @Autowired(required = false)
    private RouteRepository routeRepo;

    // admin access methods
    @Override
    public Bus addBus(Bus bus, String key) throws ResourceNotFound, AdminException {
        // Check if the route already exists
        Route existingRoute = routeRepo.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
        Route route;

        if (existingRoute == null) {
            // Create a new route if it doesn't exist
            route = new Route(bus.getRouteFrom(), bus.getRouteTo(), bus.getRoute().getDistance());
            routeRepo.save(route);
        } else {
            route = existingRoute;
        }

        // Adding route for this new bus
        bus.setRoute(route);

        // Adding this new bus to the route
        if (route.getBusList() == null) {
            route.setBusList(new ArrayList<>());
        }
        route.getBusList().add(bus);

        // Saving bus
        return busRepo.save(bus);
    }

    @Override
    public Bus updateBus(Bus bus, String key) throws ResourceNotFound {
        Optional<Bus> busOptional = busRepo.findById(bus.getBusId());
        if (!busOptional.isPresent()) {
            throw new ResourceNotFound("Bus doesn't exist with busId: " + bus.getBusId());
        }
        Bus existingBus = busOptional.get();

        Route route = routeRepo.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
        if (route == null) {
            route = new Route(bus.getRouteFrom(), bus.getRouteTo(), bus.getRoute().getDistance());
            routeRepo.save(route);
        }

        existingBus.setRoute(route);
        // Update other bus fields if necessary
        existingBus.setBusName(bus.getBusName());
        existingBus.setBusType(bus.getBusType());
        // Add other fields that need to be updated

        return busRepo.save(existingBus);
    }

    @Override
    public Bus deleteBus(Integer busId, String key) throws ResourceNotFound, AdminException {
        Optional<Bus> bus = busRepo.findById(busId);

        if (bus.isPresent()) {
            Bus existingBus = bus.get();
            // checking if current date is before journey date it means bus scheduled so
            // can't delete / or seats are reserved or not
            if (LocalDate.now().isBefore(existingBus.getBusJourneyDate())
                    && existingBus.getAvailableSeats() != existingBus.getSeats()) {
                throw new ResourceNotFound("Can't delete scheduled and reserved bus.");
            }

            busRepo.delete(existingBus);
            return existingBus;

        } else
            throw new ResourceNotFound("Bus not found with busId: " + busId);

    }

    // admin + user access methods
    @Override
    public List<Bus> viewAllBuses() throws ResourceNotFound {
        List<Bus> busList = busRepo.findAll();
        if (busList.isEmpty()) {
            throw new ResourceNotFound("No bus found at this moment. Try again later!");
        }
        return busList;
    }

    @Override
    public Bus viewBus(Integer busId) throws ResourceNotFound {
        Optional<Bus> existingBus = busRepo.findById(busId);
        if (!existingBus.isPresent()) {
            throw new ResourceNotFound("No bus exist with this busId: " + busId);
        }
        return existingBus.get();
    }

    @Override
    public List<Bus> viewBusByBusType(String busType) throws ResourceNotFound {
        List<Bus> busListType = busRepo.findByBusType(busType);
        if (busListType.isEmpty()) {
            throw new ResourceNotFound("There are no buses with bus type: " + busType);
        }
        return busListType;
    }

}
