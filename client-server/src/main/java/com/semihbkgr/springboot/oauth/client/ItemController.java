package com.semihbkgr.springboot.oauth.client;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
@RequestMapping("/item")
public class ItemController {

    private final WebClient webClient;

    public ItemController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public String items(@RegisteredOAuth2AuthorizedClient("item-client-authorization-code")
                                OAuth2AuthorizedClient authorizedClient, Model model) {
        List<Item> itemList = webClient.get()
                .uri("/item")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .exchangeToFlux(r -> r.bodyToFlux(Item.class))
                .collectList()
                .block();
        model.addAttribute("items", itemList);
        return "items";
    }

    @PostMapping
    public String save(@ModelAttribute Item item, @RegisteredOAuth2AuthorizedClient("item-client-authorization-code")
            OAuth2AuthorizedClient authorizedClient, Model model) {
        List<Item> itemList = webClient.post()
                .uri("/item")
                .body(BodyInserters.fromValue(item))
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .exchangeToFlux(r -> r.bodyToFlux(Item.class))
                .collectList()
                .block();
        model.addAttribute("items", itemList);
        return "items";
    }


}
