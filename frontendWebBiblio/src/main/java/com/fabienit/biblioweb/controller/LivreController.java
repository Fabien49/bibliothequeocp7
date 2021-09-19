package com.fabienit.biblioweb.controller;

import com.fabienit.biblioapi.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LivreController {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

/*    @GetMapping("/livres")
    public String livres(Model model) {

        RestTemplate restTemplate = new RestTemplate();


            restTemplate = restTemplateBuilder.build();
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin", "admin2021"));

        // "livre"
        Livre livre = restTemplate.getForObject("http://localhost:8080/api/livres", Livre.class);

        final Livre responseEntity = restTemplate.getForObject("http://localhost:8080/api/livres", Livre.class);
            final String body = responseEntity.getTitre();
            System.out.println("body = " + body);
            model.addAttribute("body",body);

        return "livres";
    }*/

    @GetMapping("/livres")
    public String livres(Model model, Livre livre) {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate = restTemplateBuilder.build();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin", "admin2021"));

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/livres", String.class);
        final String body = responseEntity.getBody();
        System.out.println("body = " + body);
        model.addAttribute("body",body);

       livre = restTemplate.getForObject("http://localhost:8080/api/livres" + "/6", Livre.class);
        String titre = livre.getTitre();
        System.out.println(titre);
        model.addAttribute("titre", titre);

        return "livres";
    }

    @GetMapping("/livrePage")
    public String livrePage(Model model, Livre livre) {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate = restTemplateBuilder.build();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin", "admin2021"));

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/livres", String.class);
        final String body = responseEntity.getBody();
        System.out.println("body = " + body);
        model.addAttribute("body",body);

        livre = restTemplate.getForObject("http://localhost:8080/api/livres" + "/6", Livre.class);
        model.addAttribute("livrePage", livre);
        String titre = livre.getTitre();
        String auteur = livre.getAuteur();
        String categorie = livre.getCategorie();
        String description = livre.getDescription();
        System.out.println(titre + auteur + categorie + description);
        model.addAttribute("titre", titre);
        model.addAttribute("auteur", auteur);
        model.addAttribute("categorie", categorie);
        model.addAttribute("description", description);

        return "livrePage";
    }
}
