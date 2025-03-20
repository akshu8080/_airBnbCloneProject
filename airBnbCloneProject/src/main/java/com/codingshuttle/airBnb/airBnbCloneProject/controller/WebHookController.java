package com.codingshuttle.airBnb.airBnbCloneProject.controller;

 import com.codingshuttle.airBnb.airBnbCloneProject.services.BookingService;
import com.stripe.exception.SignatureVerificationException;
 import com.stripe.model.Event;
 import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("webhook")
@RequiredArgsConstructor
public class WebHookController {

    private  final BookingService bookingService;

    @Value("${stripe.webhook.secret}")
    private  String endpointSecret;

    @PostMapping("/payment")
    public ResponseEntity<Void> capturePayments(@RequestBody String payload , @RequestHeader("Stripe-Signature")String signHeader){
            try {
                 Event event =  Webhook.constructEvent(payload,signHeader,endpointSecret);
                bookingService.capturePayment(event);
                return ResponseEntity.noContent().build();


            }catch (SignatureVerificationException e){
                throw  new RuntimeException(e);

            }
    }

}
