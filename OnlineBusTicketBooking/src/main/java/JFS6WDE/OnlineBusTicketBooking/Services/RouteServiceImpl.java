package JFS6WDE.OnlineBusTicketBooking.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JFS6WDE.OnlineBusTicketBooking.Entities.Bus;
import JFS6WDE.OnlineBusTicketBooking.Entities.Route;
import JFS6WDE.OnlineBusTicketBooking.Exception.AdminException;
import JFS6WDE.OnlineBusTicketBooking.Exception.ResourceNotFound;
import JFS6WDE.OnlineBusTicketBooking.Repository.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	private RouteRepository  routerepository;
	
	
	@Override
	public Route addRoute(Route route,String key) throws ResourceNotFound, AdminException {
		
		Route newRoute = routerepository.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
		
		if(newRoute != null) throw new ResourceNotFound("Route :"+ newRoute.getRouteFrom() +" to "+ newRoute.getRouteTo()+ " is already present !");

		List<Bus> buses = new ArrayList<>();	
	
			route.setBusList(buses);
			return routerepository.save(route);
	
}

	@Override
	public List<Route> viewAllRoute() throws ResourceNotFound {
			
		List<Route> routes=routerepository.findAll();
		
		if(routes.isEmpty())
			throw new ResourceNotFound("No route available");
		else
			return routes;	
			
	}
	@Override
	public Route viewRoute(int routeId) throws ResourceNotFound {
		 Optional<Route> opt=routerepository.findById(routeId);

		 return opt.orElseThrow(()->new ResourceNotFound("There is no route present of this  routeId :" + routeId));
	}

	@Override
	public Route updateRoute(Route route,String key) throws ResourceNotFound, AdminException {
		Optional<Route> existedRoute = routerepository.findById(route.getRouteID());
		if(existedRoute.isPresent()) {
			
			Route presentRoute = existedRoute.get();
			List<Bus> busList = presentRoute.getBusList();
			
			if(!busList.isEmpty()) throw new ResourceNotFound("Cannot update running route! Buses are already scheduled in the route.");
			
			return routerepository.save(route);
		}
		else
			throw new ResourceNotFound("Route doesn't exist of  this routeId : "+ route.getRouteID());

	}
	
	
	@Override
	public Route deleteRoute(int routeID,String key) throws ResourceNotFound, AdminException {
		Optional<Route> route=routerepository.findById(routeID);
		
		if(route.isPresent()) {
			Route existingRoute=route.get();
			routerepository.delete(existingRoute);
			return existingRoute;
		}
		else
			throw new ResourceNotFound("There is no route of this routeId : "+ routeID);

	}
}

