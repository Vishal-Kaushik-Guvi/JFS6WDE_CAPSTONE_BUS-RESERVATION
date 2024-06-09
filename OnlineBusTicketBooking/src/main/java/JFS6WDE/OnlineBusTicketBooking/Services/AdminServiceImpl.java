package JFS6WDE.OnlineBusTicketBooking.Services;

import JFS6WDE.OnlineBusTicketBooking.Dto.AdminDTO;
import JFS6WDE.OnlineBusTicketBooking.Entities.Admin;
import JFS6WDE.OnlineBusTicketBooking.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin createAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setName(adminDTO.getName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Integer adminID, AdminDTO adminDTO) {
        Admin admin = adminRepository.findById(adminID).orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setName(adminDTO.getName());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Integer adminID) {
        adminRepository.deleteById(adminID);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Integer adminID) {
        return adminRepository.findById(adminID).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Admin not found"));
    }
}
