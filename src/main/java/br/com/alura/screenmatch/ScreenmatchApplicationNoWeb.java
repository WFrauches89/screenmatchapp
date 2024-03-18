package br.com.alura.screenmatch;
/**
import br.com.alura.screenmatch.principal.Principal;
import br.com.alura.screenmatch.repository.SerieRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Data
@Builder
@SpringBootApplication
public class ScreenmatchApplicationNoWeb implements CommandLineRunner { //implements CommandLineRunner CommandLinRunner é uma implementação para rodar com linhas de comando no run - Realizar a implements e Add methods


	private SerieRepository serieRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplicationNoWeb.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(serieRepository);
		principal.menu();

	}
}
**/