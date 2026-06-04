package com.careeraccelerator.controller;

import com.careeraccelerator.entity.User;
import com.careeraccelerator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(
            Model model){

        model.addAttribute(
                "userName",
                "Harithaa"
        );

        return "dashboard/dashboard";
    }

}