package net.metradingplat.ms_escaner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsEscanerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEscanerApplication.class, args);
	}

}
