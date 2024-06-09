package JFS6WDE.OnlineBusTicketBooking.Controller;

import JFS6WDE.OnlineBusTicketBooking.Dto.AdminDTO;
import JFS6WDE.OnlineBusTicketBooking.Services.AdminServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/saveAdmin")
    public String showRegistrationForm(Model model) {
        model.addAttribute("adminDTO", new AdminDTO());
        return "adminregister";
    }

    @PostMapping("/saveAdmin")
    public String registerAdmin(@Valid AdminDTO adminDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "adminregister";
        }
        adminService.createAdmin(adminDTO);
        return "redirect:/adminlogin";
    }

    @GetMapping("/adminLogin")
    public String showLoginForm() {
        return "adminlogin";
    }

}
