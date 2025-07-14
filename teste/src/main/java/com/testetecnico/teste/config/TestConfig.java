package com.testetecnico.teste.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.testetecnico.teste.entities.Reclamacao;
import com.testetecnico.teste.entities.User;
import com.testetecnico.teste.repositories.ReclamacaoRepository;
import com.testetecnico.teste.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "988888888", "maria@gmail.com", "123456");
        User u2 = new User(null, "Alex Green", "977777777", "alex@gmail.com",  "123456");

        Reclamacao r1 = new Reclamacao(null, "Teste Title", "123456789","Teste Description", Instant.parse("2025-07-14T17:27:30Z"), u1);
        Reclamacao r2 = new Reclamacao(null, "Teste Title 2", "123456789","Teste Description 2", Instant.parse("2025-07-18T17:27:30Z"), u1);
        Reclamacao r3 = new Reclamacao(null, "Ola", "987654321","Mundo", Instant.parse("2025-10-30T17:27:30Z"), u2);

        userRepository.saveAll(Arrays.asList(u1, u2));
        reclamacaoRepository.saveAll(Arrays.asList(r1, r2, r3));
    }

}
