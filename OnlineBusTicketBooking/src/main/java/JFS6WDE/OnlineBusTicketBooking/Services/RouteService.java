package JFS6WDE.OnlineBusTicketBooking.Services;

import java.util.List;

import JFS6WDE.OnlineBusTicketBooking.Entities.Route;
import JFS6WDE.OnlineBusTicketBooking.Exception.AdminException;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;

public interface RouteService {
    
	public Route addRoute(Route route,String key) throws ResourceNotFound, AdminException;
	public List<Route> viewAllRoute() throws ResourceNotFound;
	public Route viewRoute(int routeId) throws ResourceNotFound;
	public Route updateRoute(Route route,String key) throws ResourceNotFound, AdminException;
	public Route deleteRoute(int routeID,String key) throws ResourceNotFound, AdminException;
	
	
	
}