package bad.xcl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackExpedienteApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackExpedienteApplication.class, args);
	}

	//Ejecutado antes de arrancar, genera pass para import.sql
	@Override
	public void run(String... args) throws Exception {
		String password = "grupo11Pass";
		for(int i = 0; i < 2; i++ ) {
			String passEncriptada = passEncoder.encode(password);
			System.out.println(passEncriptada);
		}
	}
}
