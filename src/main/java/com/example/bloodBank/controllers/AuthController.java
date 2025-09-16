package com.example.bloodBank.controllers;

import com.example.bloodBank.dtos.ForgotPasswordDto;
import com.example.bloodBank.dtos.UserLoginDto;
import com.example.bloodBank.dtos.UserRegisterDto;
import com.example.bloodBank.models.UserModel;
import com.example.bloodBank.services.AuthServices;
import javax.servlet.http.*;
import javax.validation.Valid;
import com.example.bloodBank.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private AuthServices authServices;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model,HttpServletResponse response) {
        if (model.containsAttribute("errorStatus")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        model.addAttribute("userLoginDto",new UserLoginDto());
        return "login";
    }
    @GetMapping("/verifyOtp")
    public String otpVerifier(){
        return "otpverification";
    }
    @PostMapping("/verifyOtp")
    public String otpVerification(@RequestParam("otp") Integer otp,
            RedirectAttributes redirectAttributes, HttpServletResponse response,
                                  HttpSession session){

      UserRegisterDto userRegisterDto=(UserRegisterDto) session.getAttribute("userRegisterDto");
      Integer sendOtp=(Integer) session.getAttribute("otp");
        authServices.signupUser(userRegisterDto);
      if(!sendOtp.equals(otp)){
          redirectAttributes.addFlashAttribute("message","Invalid otp, try again");
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          return "redirect:/verifyOtp";
      }
      session.invalidate();
      redirectAttributes.addFlashAttribute("successMessage","User registered successfully");
      return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println((String) session.getAttribute("userName"));
            authServices.updateLastLoginDetail((String) session.getAttribute("userName"));
            session.invalidate();
        return "/index";
    }

    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model){
        model.addAttribute("forgotPasswordDto",new ForgotPasswordDto());
        return "forgotPassword";
    }
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "signup";
    }
    @PostMapping("/signupHandler")
    public String signupHandler(@ModelAttribute("userRegisterDto") @Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, Model model
                               , HttpServletResponse response, HttpSession session,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "signup";
        }
        try {
            authServices.verifyExisting(userRegisterDto);
            int otp=emailService.sendVerificationEmail(userRegisterDto.getEmail());
           session.setAttribute("userRegisterDto",userRegisterDto);
            session.setAttribute("otp",otp);
            return "redirect:/verifyOtp";
        }catch (RuntimeException exception){
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "redirect:/signup";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute @Valid UserLoginDto userLoginDto, BindingResult bindingResult,
                        Model model, HttpServletRequest request, HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {

        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "login";
        }
        try {
            authServices.loginUser(userLoginDto);
            UserModel returnModelDto = authServices.getUserByUsername(userLoginDto.getUserName());
            if (returnModelDto == null) {
                model.addAttribute("message", "User not found.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return "login";
            }
            String username=returnModelDto.getUserName();
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("userName", username);
            newSession.setAttribute("role", returnModelDto.getRole());
            System.out.println(username);
            return "redirect:/dashboard";
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorStatus", 400);
            redirectAttributes.addFlashAttribute("message", exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "redirect:/login";
        }
    }

    @PostMapping("/forgotPassword")
    public String forgotPasswordHandler(@ModelAttribute @Valid ForgotPasswordDto forgotPasswordDto, BindingResult bindingResult, Model model,
                                        RedirectAttributes redirectAttributes,HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "forgotPassword";
        }
        try{
            authServices.updatePassword(forgotPasswordDto);
            redirectAttributes.addFlashAttribute("successMessage","Password updated successfully");
            return "redirect:/login";
        }catch (RuntimeException exception){
            redirectAttributes.addFlashAttribute("message",exception.getMessage() );
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "redirect:/forgotPassword";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model, HttpServletResponse response,
                            RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role")==null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            redirectAttributes.addFlashAttribute("message","Invalid Session");
            return "redirect:/login";
        }
        String role = (String) session.getAttribute("role");
        System.out.println(role);
        String page=role.equalsIgnoreCase("ENDUSER") ? "userDashboard" : "adminDashboard";
        return "redirect:/"+page;
    }
}
