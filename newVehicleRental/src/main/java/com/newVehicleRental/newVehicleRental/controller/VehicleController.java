package com.newVehicleRental.newVehicleRental.controller;

import com.newVehicleRental.newVehicleRental.model.vehicle;
import com.newVehicleRental.newVehicleRental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String showVehicles(Model model, Authentication authentication,
                               @ModelAttribute("message") String message) {
        List<vehicle> vehicles;

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            vehicles = vehicleService.getAllVehicles();
        } else {
            vehicles = vehicleService.getAvailableVehicles();
        }

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("newVehicle", new vehicle());

        model.addAttribute("bookingSuccess", message != null && !message.isEmpty());

        return "vehicles";
    }

    @PostMapping("/add")
    public String addVehicle(@ModelAttribute vehicle vehicle) {
        vehicleService.createVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @PostMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }

    @PostMapping("/book/{id}")
    public String bookVehicle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean booked = vehicleService.bookVehicle(id);

        if (booked) {
            redirectAttributes.addFlashAttribute("message", "✅ Vehicle successfully booked.");
        } else {
            redirectAttributes.addFlashAttribute("error", "❌ Booking failed. Vehicle may already be booked.");
        }

        return "redirect:/vehicles";
    }

    @PostMapping("/updateAvailability/{id}")
    public String updateAvailability(@PathVariable Long id, @RequestParam("available") boolean available) {
        vehicleService.updateAvailability(id, available);
        return "redirect:/vehicles";
    }
}
