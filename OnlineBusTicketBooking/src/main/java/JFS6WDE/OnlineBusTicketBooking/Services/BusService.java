package JFS6WDE.OnlineBusTicketBooking.Services;

import java.util.List;

import JFS6WDE.OnlineBusTicketBooking.Entities.Bus;
import JFS6WDE.OnlineBusTicketBooking.Exception.AdminException;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;

public interface BusService {
    //admin methods
    public Bus addBus(Bus bus, String key) throws ResourceNotFound , AdminException;
    public Bus updateBus(Bus bus, String key) throws ResourceNotFound, AdminException;
    public Bus deleteBus(Integer busId, String key) throws ResourceNotFound, AdminException;

    //admin + user methods
    public Bus viewBus(Integer busId) throws ResourceNotFound;
    public List<Bus> viewBusByBusType(String busType) throws ResourceNotFound;
    public List<Bus> viewAllBuses() throws ResourceNotFound;
}
