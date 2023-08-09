package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.BookingDashboard;
import com.example.salonmanage.DTO.DashboardDTO;
import com.example.salonmanage.Entities.Booking;
import com.example.salonmanage.reponsitory.BookingRepository;
import com.example.salonmanage.reponsitory.BranchRepository;
import com.example.salonmanage.reponsitory.ServiceRepository;
import com.example.salonmanage.reponsitory.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardAPI {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private BranchRepository branchRepository;
    @Autowired private ServiceRepository serviceRepository;
    @Autowired private userRepository userRepository;

    @GetMapping
    @RolesAllowed("ROLE_ADMIN")
    public DashboardDTO get(){
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setBranch(branchRepository.countAllWithNotRemove());
        dashboardDTO.setService(serviceRepository.countAllWithNotRemove());
        dashboardDTO.setUser(userRepository.countAllWithNotRemove("ROLE_CUSTOMER"));
        dashboardDTO.setEmployee(userRepository.countAllWithNotRemove("ROLE_STAFF"));
        dashboardDTO.setTotalPrice(bookingRepository.sumAmount());
        dashboardDTO.setBooking(bookingRepository.countAllWithNotRemove());
        dashboardDTO.setIncrease((bookingRepository.countAllWithNotRemove()- bookingRepository.countAllWithNotRemove30DayBefore())/ bookingRepository.countAllWithNotRemove()*100);
        List<Booking> booking = bookingRepository.getTop10();
        List<BookingDashboard> bookingDashboards = new ArrayList<>();
        String name;
        for (int i = 0; i < booking.size(); i++) {
            if (booking.get(i).getUser()==null){
                name ="khánh hàng";
            }else{
                name = booking.get(i).getUser().getName();
            }
            bookingDashboards.add(new BookingDashboard(booking.get(i).getID(),name,booking.get(i).getDate(),booking.get(i).getTotalPrice()));
        }
        dashboardDTO.setBookingDashboards(bookingDashboards);
        return dashboardDTO;
    }
}
