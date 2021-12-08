package com.appsdeveloperblog.rentalapp.api.invoices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {
	
	@Autowired
	private Environment environment;
	
	@GetMapping("status/check")
	public String status() {
		return "Working on  " + environment.getProperty("local.server.port");
	}
}

