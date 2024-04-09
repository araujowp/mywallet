package br.com.araujowp.mywallet.mywalletapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class MyWalletapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWalletapiApplication.class, args);
	}

}
