package com.orange.logindemo.email;

public interface EmailSender {
    void send(String to, String email);
    void testAsync();
}
