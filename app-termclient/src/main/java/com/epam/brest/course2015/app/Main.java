package com.epam.brest.course2015.app;

import com.epam.brest.course2015.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

/**
 * Created by bendar on 26.10.15.
 */
@Component
public class Main {

    @Value("${user.protocol}://${user.host}:${user.port}/${user.prefix}/")
    private String url;

    @Value("${point.users}")
    private String urlUsers;

    @Value("${point.user}")
    private String urlUser;

    Scanner sc = new Scanner(System.in);
    ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    {
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        Main main = ctx.getBean(Main.class);
        main.menu();
    }

    private void menu() {

        int swValue = 0;

        System.out.println("=================================");
        System.out.println("|   MENU SELECTION DEMO         |");
        System.out.println("=================================");
        System.out.println("| Options:                      |");
        System.out.println("|        1. Get all users       |");
        System.out.println("|        2. Get user by login   |");
        System.out.println("|        3. Get user by id      |");
        System.out.println("|        4. Add user            |");
        System.out.println("|        5. Delete user         |");
        System.out.println("|        6. Updete user         |");
        System.out.println("|        7. Exit                |");
        System.out.println("=================================");
        while (swValue != 7) {
            System.out.print("Select option: ");
            if (sc.hasNextInt()) {
                swValue = sc.nextInt();
                checkValue(swValue);
            } else {
                System.out.println("Bad value." +sc.next());
            }
        }
    }

    private void checkValue(int item) {
        switch (item) {
            case 1:
                getAllUsers();
                break;
            case 2:
                getUserByLogin();
                break;
            case 3:
                getUserById();
                break;
            case 4:
                addUser();
                break;
            case 5:
                deleteUser();
                break;
            case 6:
                updatedUser();
                break;
            case 7:
                System.out.println("Exit.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void getAllUsers() {
        ResponseEntity responseEntity = restTemplate.getForEntity(url + urlUsers, Object.class);
        System.out.println("user: " + responseEntity.getBody());
    }

    private void getUserByLogin() {
        String userLogin = "";
        System.out.print("    Enter user login: ");
        if(sc.hasNextLine()) {
            userLogin = sc.next();
        }

        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url + urlUser + "/" + userLogin, Object.class);
            System.out.println("    User: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void getUserById() {
        Integer userId = 0;
        System.out.print("    Enter user id: ");
        if(sc.hasNextLine()) {
            userId = Integer.parseInt(sc.next());
        }

        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url + urlUser + "/id/" + userId, Object.class);
            System.out.println("    User: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void addUser() {
        String login="";
        String passwrod="";
        System.out.print("    Enter user login: ");
        if(sc.hasNextLine()) {
            login =sc.next();
        }
        System.out.print("    Enter user password: ");
        if(sc.hasNextLine()) {
            passwrod =sc.next();
        }
        User user = new User(login,passwrod);
        try {
            restTemplate.postForObject(url + urlUser , user, Object.class);
            System.out.println("    User created: ");
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void updatedUser() {
        Integer userId=0;
        String password="";
        System.out.print("    Enter user id: ");
        if(sc.hasNextLine()) {
            userId = Integer.parseInt(sc.next());
        }
        System.out.print("    Enter user changed password: ");
        if(sc.hasNextLine()) {
            password =sc.next();
        }

        try {
           restTemplate.put(url + urlUser + "/" + userId + "/" + password, Object.class);

            System.out.println("    User updated: ");
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }



    private void deleteUser() {
        Integer userId = 0;
        System.out.print("    Enter user id: ");
        if(sc.hasNextLine()) {
            userId = Integer.parseInt(sc.next());
        }


        try {
            restTemplate.delete(url + urlUser + "/" + userId, Object.class);
            System.out.println("    User deleted");
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }
}