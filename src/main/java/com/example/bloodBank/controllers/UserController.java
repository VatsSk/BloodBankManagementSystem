package com.example.bloodBank.controllers;

import com.example.bloodBank.dtos.UserRequestDto;
import com.example.bloodBank.exceptionHandlers.GlobalException;
import com.example.bloodBank.models.BloodRequestModel;
import com.example.bloodBank.models.UserModel;
import com.example.bloodBank.services.AuthServices;
import com.example.bloodBank.services.EmailService;
import com.example.bloodBank.services.UserServices;
import javax.servlet.http.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailService emailService;

    @GetMapping("/userDashboard")
    public String userDashboard(HttpServletRequest request, Model model,HttpServletResponse response,
                                RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || !"ENDUSER".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("error", "Session Invalid");
            return "redirect:/login";
        }
        String userName = (String) session.getAttribute("userName");
        UserModel user = authServices.getUserByUsername(userName);
        model.addAttribute("user",user);
        return "userDashboard";
    }

    @GetMapping("/userRequest")
    public String getRequestpage(Model model){

        model.addAttribute("userRequestDto",new UserRequestDto());
        return "userRequest";
    }
    @PostMapping("/userRequest")
    public String userRequest(@ModelAttribute("userRequestDto") @Valid UserRequestDto userRequestDto, BindingResult bindingResult, HttpServletRequest request, Model model,
                              HttpServletResponse response,RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession(false);
        if (session == null || !"ENDUSER".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("error", "Session Invalid");
            return "redirect:/login";
        }
        String userName = (String) session.getAttribute("userName");
        if (bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "userDashboard";
        }
        try {
            userServices.requestBlood(userRequestDto, userName);
            model.addAttribute("message", "Request initiated successfully");
            String msg=userRequestDto.getType()+" request Initiated successfully";
            String subject="Request Confirmation";
            emailService.sendMailWithMessage(authServices.getEmailFromUserName(userName),
                    subject,msg);
        }catch (GlobalException exception){
            model.addAttribute("message",exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "userRequest";
    }

    @GetMapping("/userHistory")
    public String userHistory(HttpServletRequest request, Model model,HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session == null || !"ENDUSER".equalsIgnoreCase((String) session.getAttribute("role"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            session.setAttribute("error", "Session Invalid");
            return "redirect:/login";
        }
        String userName = (String) session.getAttribute("userName");
        List<BloodRequestModel> historyList = userServices.getHistoryByUser(userName);
        model.addAttribute("historyList", historyList);
        return "userHistory";
    }

}
