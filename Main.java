package org.example;

import com.google.gson.JsonParseException;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

    public class Main {
    static UserService userService = new UserServiceImpl();
    static Scanner scStr = new Scanner(System.in);
        static Scanner scInt = new Scanner(System.in);
    public static void main(String[] args) throws IOException, InterruptedException, JsonParseException, MessagingException {
        while (true) {
            printMenu();
            int index = scInt.nextInt();
            if (index == 1) {
                System.out.print("Enter email - ");
                String email = scStr.nextLine();
                var reg = register(email);
                if(!reg) {
                    break;
                }
            } else if (index == 2) {
                if(!User()) {
                    System.out.println("Please try again!");
                }
            }
            System.out.print("Enter month - ");
            String month = scStr.nextLine();
            System.out.print("Enter day - ");
            String day = scStr.nextLine();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://numbersapi.p.rapidapi.com/month/day/date"))
                    .header("X-RapidAPI-Key", "38aea60a28mshe3fee286d8e5567p1d5caajsn6b44a87da71d")
                    .header("X-RapidAPI-Host", "numbersapi.p.postmanapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println(request);
        }
    }
    public static boolean User() {
        System.out.print("Enter your username - ");
        String name = scStr.nextLine();
        System.out.print("Enter your password - ");
        String password = scStr.nextLine();
        userService.login(name,password);
        return true;
    }
    private static boolean register(String email) throws MessagingException {
        userService.register(email);
        return true;
    }
    private static void printMenu() {
        System.out.println("""
                1.Register
                2.Login
                
                0.Exit""");
    }
    }