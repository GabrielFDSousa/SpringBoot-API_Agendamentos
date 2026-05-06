package com.gabriel_sousa.api_scheduling_system.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping
    public ResponseEntity hello(){
      return ResponseEntity.ok().body("Hello");
    };
}
