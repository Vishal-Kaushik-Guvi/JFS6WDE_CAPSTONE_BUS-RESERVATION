package JFS6WDE.OnlineBusTicketBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import JFS6WDE.OnlineBusTicketBooking.Entities.Bus;
import JFS6WDE.OnlineBusTicketBooking.Services.BusServiceImpl;

@Controller
public class BusController {

    @Autowired
    private BusServiceImpl busService;

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @PostMapping("/addBus")
    public String addBus(Bus bus){
       busService.addBus(bus);
       return "adminindex";
    }
    
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/busList")
    public String showBusList(Model model) {
        List<Bus> buses = busService.viewAllBuses();
        model.addAttribute("buses", buses);
        return "buslist";
    }

    @GetMapping("/bus/{id}")
    public String showEditBusForm(@PathVariable("id") Integer id, Model model) {
        Bus bus = busService.viewBus(id);
        model.addAttribute("bus", bus);
        return "updatebus";
    }

    @PostMapping("/busUpdate")
    public String updateBus(@ModelAttribute("bus") Bus bus) {
        busService.updateBus(bus);
        return "redirect:/admin/bus/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable("id") Integer id) {
        busService.deleteBus(id);
        return "redirect:/admin/bus/list";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Bus> page = busService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Bus> listBus = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listBus", listBus);
        return "index";
    }
}