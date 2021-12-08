package com.appsdeveloperblog.rentalapp.api.invoices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RentalAppApiInvoicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalAppApiInvoicesApplication.class, args);
	}

}
