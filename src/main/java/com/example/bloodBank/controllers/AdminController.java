package com.example.bloodBank.controllers;

import com.example.bloodBank.dtos.BloodReportDTO;
import com.example.bloodBank.exceptionHandlers.GlobalException;
import com.example.bloodBank.models.BloodRequestModel;
import com.example.bloodBank.models.BloodStockModel;
import com.example.bloodBank.models.UserModel;
import com.example.bloodBank.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.*;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private SortingServices sortingServices;

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private EmailService emailService;

    @GetMapping("/requests")
    public String getAllRequests(@RequestParam(value = "sortBy", required = false, defaultValue = "all") String sortBy, HttpServletRequest request, Model model, HttpServletResponse response,
                                 RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("message", "Invalid Session");
            return "redirect:/login";
        }

        List<BloodRequestModel> bloodRequestModelList = adminServices.getAllRequests();
        if ("requestdate".equalsIgnoreCase(sortBy)) {
            bloodRequestModelList = sortingServices.sortByRequestDate(bloodRequestModelList);
        } else if ("status".equalsIgnoreCase(sortBy)) {
            bloodRequestModelList = adminServices.getRequestListByStatus();
        } else if ("bloodgroup".equalsIgnoreCase(sortBy)) {
            bloodRequestModelList = sortingServices.sortByBloodGroup(bloodRequestModelList);
        }

        model.addAttribute("sortBy", sortBy);
        model.addAttribute("bloodRequestList", bloodRequestModelList);
        return "requests";
    }

    @Transactional
    @PostMapping("/updateRequestStatus")
    public String updateRequestStatus(@RequestParam("requestId") Long requestId,
                                      @RequestParam("status") String status,
                                      @RequestParam("bloodGroup") String bloodGroup,
                                      @RequestParam("quantity") Double quantity,
                                      @RequestParam("type") String type,
                                      RedirectAttributes redirectAttributes,
                                      HttpServletResponse response) {
      try {
          if (status.equalsIgnoreCase("approved")) {
              adminServices.updateIntoBloodStockDB(bloodGroup, quantity, type);
          }
              adminServices.updateRequestStatus(requestId, status);
              String username= adminServices.getUserNameFromId(requestId);
              String email=authServices.getEmailFromUserName(username);
              String subject="Request Confirmation";
              String msg="Your Blood "+type+" request has been "+status;
              emailService.sendMailWithMessage(email,subject,msg);
      }catch (GlobalException exception){
          redirectAttributes.addFlashAttribute("message",exception.getMessage());
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
        return "redirect:/requests";
    }

    @GetMapping("/stock")
    public String getStock(HttpServletRequest request, Model model, HttpServletResponse response,
                           RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("message", "Invalid Session");
            return "redirect:/login";
        }
        List<BloodStockModel> returnModel = adminServices.getBloodStock();
        model.addAttribute("bloodStockList", returnModel);
        return "stock";
    }

    @GetMapping("/bloodReport")
    public String getBloodReport(HttpServletRequest request, Model model, HttpServletResponse response,
                                 RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("message", "Invalid Session");
            return "redirect:/login";
        }
        List<BloodReportDTO> bloodReport = adminServices.getBloodReport();
        model.addAttribute("bloodReportList", bloodReport);
        return "bloodReport";
    }
    
    @GetMapping("/adminDashboard")
    public String getDashboard(HttpServletRequest request, Model model, HttpServletResponse response,
                               RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("message", "Invalid Session");
            return "redirect:/login";
        }

        model.addAttribute("activeUsers", authServices.countActiveUsers(false)-1); //-1 for admin count
        model.addAttribute("allAvailableUnits", adminServices.getAvailableUnits());
        model.addAttribute("totalDonatedUnits", adminServices.getTotalDonatedUnits());
        model.addAttribute("totalReceivedUnits", adminServices.getTotalReceivedUnits());
        model.addAttribute("lastLoginDate", adminServices.getLastloginDate());
        System.out.println(adminServices.getLastloginDate());
        return "adminDashboard";
    }

    @GetMapping("/allUsers")
    public String getUserList(@RequestParam(value = "statusFilter", required = false, defaultValue = "all") String status,
                              @RequestParam(value = "sortBy", required = false, defaultValue = "none") String sortby,
                              HttpServletRequest request, Model model, HttpServletResponse response,
                              RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("message", "Invalid Session");
            return "redirect:/login";
        }

        List<UserModel> userList = authServices.getAllUsers(status);
        if (userList != null && !userList.isEmpty()) {
            if (sortby.equalsIgnoreCase("name")) {
                userList = sortingServices.sortByName(userList);
            } else if (sortby.equalsIgnoreCase("username")) {
                userList = sortingServices.sortByUserName(userList);
            } else if (sortby.equalsIgnoreCase("dob")) {
                userList = sortingServices.sortByDob(userList);
            } else if (sortby.equalsIgnoreCase("createdDate")) {
                userList = sortingServices.sortByCreatedDate(userList);
            }
        }
        model.addAttribute("userList", userList);
        return "allUsers";
    }
}
