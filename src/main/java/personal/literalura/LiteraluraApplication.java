package personal.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import personal.literalura.principal.Principal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	// Usar Ids da API

	@Override
	public void run(String... args) throws Exception {
		var principal = new Principal();
		principal.exibeMenu();
	}
}
