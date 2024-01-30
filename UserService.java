package org.example.service;

import org.example.domain.User;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.util.ArrayList;
import java.util.List;

public interface UserService {
    List<User> USERS = new ArrayList<>();
    boolean register(String email) throws MessagingException;
    boolean login(String name, String password);
    boolean confirm(String gmail) throws MessagingException, InterruptedException;
    boolean sendEmail(String email, Session session, Double code) throws MessagingException, InterruptedException;
}
