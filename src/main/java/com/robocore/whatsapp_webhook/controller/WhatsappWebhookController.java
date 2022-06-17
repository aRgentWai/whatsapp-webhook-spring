package com.robocore.whatsapp_webhook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/whatsapp", produces = MediaType.APPLICATION_JSON_VALUE)
public class WhatsappWebhookController {
    private static final Logger log = LoggerFactory.getLogger(WhatsappWebhookController.class);

    @Value("${whatsapp.token}")
    String whatsappToken;

    @GetMapping(path = "/")
    public ResponseEntity<String> getHi() {
            return ResponseEntity.ok("hi");
    }

    @GetMapping(path = "/webhooks")
    public ResponseEntity<String> getWebhooks(
            @RequestParam(name = "hub.mode") String hubMode,
            @RequestParam(name = "hub.challenge") int hubChallenge,
            @RequestParam(name = "hub.verify_token") String hubVerifyToken
            ) {
        if (hubMode.equals("subscribe") && hubVerifyToken.equals(whatsappToken))
            return ResponseEntity.ok("" + hubChallenge);
        else
            return ResponseEntity.badRequest().build();
    }
}
