package com.company;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import com.google.gson.Gson;

public class RequestHandler {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String baseUrl = "http://localhost:8080/";

    public  String Login(User user) throws ConnectionError {
        Gson gson = new Gson();

        var values = new HashMap<String, String>() {{
            put("UserName", user.getUserName());
            put ("Password", user.getPassword());
        }};

        var requestBody = gson.toJson(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/user/login"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                return response.body();
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }

    }

    public Boolean Register(User user) throws ConnectionError {
        Gson gson = new Gson();

        var values = new HashMap<String, String>() {{
            put("UserName", user.getUserName());
            put ("Password", user.getPassword());
            put("Email", user.getEmail());
            put("PhoneNumber", user.getPhoneNumber());
        }};

        var requestBody = gson.toJson(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/user/register"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                return response.body().equals("OK");
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }

    }

    public String[] GetAllAnimalsByFilter(Filter filter) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/animals/filter/get");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("City",filter.getCity())
                .setHeader("Town",filter.getTown())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,String[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    public String[] GetFavoritePets(User user) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/favoritePets");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", user.getUserName())
                .setHeader("Mail", user.getEmail())
                .setHeader("PhoneNumber", user.getPhoneNumber())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,String[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }


    public Boolean updateUser(User userToUpdate, User updateUserWith) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/account/update");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", userToUpdate.getUserName())
                .setHeader("Mail", userToUpdate.getEmail())
                .setHeader("PhoneNumber", userToUpdate.getPhoneNumber())
                .setHeader("NewUsername", updateUserWith.getUserName())
                .setHeader("NewMail", updateUserWith.getEmail())
                .setHeader("NewPhoneNumber", updateUserWith.getPhoneNumber())
                .setHeader("NewPassword", updateUserWith.getPassword())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                return response.body().equals("OK");
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    public String[] getAllOwnership(User user) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/ownages/get");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", user.getUserName())
                .setHeader("Mail", user.getEmail())
                .setHeader("PhoneNumber", user.getPhoneNumber())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,String[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    public String[] getAllOwnershipRequestList(User user) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/ownages/requests/get");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", user.getUserName())
                .setHeader("Mail", user.getEmail())
                .setHeader("PhoneNumber", user.getPhoneNumber())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                String jsonBody = response.body();
                Gson gson = new Gson();
                return gson.fromJson(jsonBody,String[].class);
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }

    public Boolean requestOwnership(User user, Animal animal) throws ConnectionError {
        Gson gson = new Gson();

        var values = new HashMap<String, String>() {{
            put("UserName", user.getUserName());
            put ("Password", user.getPassword());
            put("Email", user.getEmail());
            put("AnimalId", animal.getId());
        }};

        var requestBody = gson.toJson(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/user/ownages/requests/add"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                return response.body().equals("OK");
            }
            else {
                System.out.println(response.statusCode());
                System.out.println(response.body());
                throw new ConnectionError();
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionError();
        }
    }


}
