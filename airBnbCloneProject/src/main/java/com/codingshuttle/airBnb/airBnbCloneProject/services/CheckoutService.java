package com.codingshuttle.airBnb.airBnbCloneProject.services;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Booking;

public  interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl , String failureUrl);
}
