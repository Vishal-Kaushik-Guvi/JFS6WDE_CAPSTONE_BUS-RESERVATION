package JFS6WDE.OnlineBusTicketBooking.Services;

import JFS6WDE.OnlineBusTicketBooking.Entities.Admin;
import JFS6WDE.OnlineBusTicketBooking.Exception.AdminException;

public interface AdminService {
    public Admin createAdmin(Admin admin) throws AdminException;
}

