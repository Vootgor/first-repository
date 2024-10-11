package com.bankApp.bank_account_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Bank Account API",
		version = "1.0",
		description = "API для работы с банковскими счетами",
		contact = @Contact(name = "Ваше имя", email = "ваша.почта@example.com"),
		license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
	)
)
public class BankAccountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApiApplication.class, args);
	}

}
