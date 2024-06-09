package JFS6WDE.OnlineBusTicketBooking.Services;

import JFS6WDE.OnlineBusTicketBooking.Dto.AdminDTO;
import JFS6WDE.OnlineBusTicketBooking.Entities.Admin;

import java.util.List;

public interface AdminService {
    Admin createAdmin(AdminDTO adminDTO);
    Admin updateAdmin(Integer adminID, AdminDTO adminDTO);
    void deleteAdmin(Integer adminID);
    List<Admin> getAllAdmins();
    Admin getAdminById(Integer adminID);
    Admin getAdminByEmail(String email);
}

