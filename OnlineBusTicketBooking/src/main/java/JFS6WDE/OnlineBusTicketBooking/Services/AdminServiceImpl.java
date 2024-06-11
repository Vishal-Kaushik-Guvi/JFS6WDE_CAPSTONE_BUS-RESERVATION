// package JFS6WDE.OnlineBusTicketBooking.Services;

// import JFS6WDE.OnlineBusTicketBooking.Dto.AdminDTO;
// import JFS6WDE.OnlineBusTicketBooking.Entities.Admin;
// import JFS6WDE.OnlineBusTicketBooking.Entities.Role;
// import JFS6WDE.OnlineBusTicketBooking.Repository.AdminRepository;
// import JFS6WDE.OnlineBusTicketBooking.Repository.RoleRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.Arrays;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Service
// public class AdminServiceImpl implements AdminService {

//     private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

//     @Autowired
//     private AdminRepository adminRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     RoleRepository roleRepository;

//     @Override
//     public void createAdmin(AdminDTO adminDTO) {
//         logger.info("Creating admin: {}", adminDTO.getEmail());
//         Admin admin = new Admin();
//         admin.setName(adminDTO.getName());
//         admin.setEmail(adminDTO.getEmail());
//         admin.setPassword(passwordEncoder.encode(adminDTO.getPassword())); // Encode password

//         try {
//             adminRepository.save(admin);
//         } catch (Exception e) {
//             logger.error("Error occurred while saving admin", e);
//             throw e;
//         }

//         Role role = roleRepository.findByName("ROLE_ADMIN");
//         if (role == null) {
//             role = checkRoleExist();
//         }
//         admin.setRoles(Arrays.asList(role));

//         try {
//             adminRepository.save(admin);
//             logger.info("Admin created successfully: {}", adminDTO.getEmail());
//         } catch (Exception e) {
//             logger.error("Error occurred while saving admin roles", e);
//             throw e;
//         }
//     }

//     @Override
//     public Admin findUserByEmail(String email) {
//         logger.info("Finding admin by email: {}", email);
//         return adminRepository.findByEmail(email);
//     }

//     @Override
//     public List<AdminDTO> getAllAdmins() {
//         logger.info("Retrieving all admins");
//         List<Admin> admins = adminRepository.findAll();
//         return admins.stream()
//                 .map(this::mapToUserDto)
//                 .collect(Collectors.toList());
//     }

//     private AdminDTO mapToUserDto(Admin admin) {
//         AdminDTO adminDto = new AdminDTO();
//         String[] str = admin.getName().split(" ");
//         adminDto.setName(str[0]);
//         adminDto.setEmail(admin.getEmail());
//         return adminDto;
//     }

//     private Role checkRoleExist() {
//         Role role = new Role();
//         role.setName("ROLE_ADMIN");
//         return roleRepository.save(role);
//     }
// }
