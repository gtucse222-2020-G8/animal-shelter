package cse222.group8.userapp;


import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

/**
 * The type Request handler.
 */
public class RequestHandler {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String baseUrl = "http://localhost:8080/";

    /**
     * Login string.
     *
     * @param user the user
     * @return the string
     * @throws ConnectionError the connection error
     */
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

    /**
     * Register boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws ConnectionError the connection error
     */
    public Boolean Register(User user) throws ConnectionError {
        Gson gson = new Gson();

        var values = new HashMap<String, String>() {{
            put("UserName", user.getUserName());
            put ("Password", user.getPassword());
            put("Email", user.getEmail());
            put("City",user.getCity());
            put("Town",user.getTown());
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

    /**
     * Get all animals by filter string [ ].
     *
     * @param filter the filter
     * @return the string [ ]
     * @throws ConnectionError the connection error
     */
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

    /**
     * Get favorite pets string [ ].
     *
     * @param user the user
     * @return the string [ ]
     * @throws ConnectionError the connection error
     */
    public String[] GetFavoritePets(User user) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/favoritePets");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", user.getUserName())
                .setHeader("Mail", user.getEmail())
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


    /**
     * Update user boolean.
     *
     * @param userToUpdate   the user to update
     * @param updateUserWith the update user with
     * @return the boolean
     * @throws ConnectionError the connection error
     */
    public Boolean updateUser(User userToUpdate, User updateUserWith) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/account/update");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", userToUpdate.getUserName())
                .setHeader("Name", userToUpdate.getName())
                .setHeader("Mail", userToUpdate.getEmail())
                .setHeader("NewUsername", updateUserWith.getUserName())
                .setHeader("NewMail", updateUserWith.getEmail())
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

    /**
     * Get all ownership string [ ].
     *
     * @param user the user
     * @return the string [ ]
     * @throws ConnectionError the connection error
     */
    public String[] getAllOwnership(User user) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/ownages/get");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", user.getUserName())
                .setHeader("Mail", user.getEmail())
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

    /**
     * Get all ownership request list string [ ].
     *
     * @param user the user
     * @return the string [ ]
     * @throws ConnectionError the connection error
     */
    public String[] getAllOwnershipRequestList(User user) throws ConnectionError {
        URI uri = URI.create(baseUrl + "/user/ownages/requests/get");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Username", user.getUserName())
                .setHeader("Mail", user.getEmail())
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

    /**
     * Request ownership boolean.
     *
     * @param user      the user
     * @param animal    the animal
     * @param shelterId the shelter ıd
     * @return the boolean
     * @throws ConnectionError the connection error
     */
    public Boolean requestOwnership(User user, Animal animal,String shelterId) throws ConnectionError {
        Gson gson = new Gson();

        var values = new HashMap<String, String>() {{
            put("UserName", user.getUserName());
            put("Name", user.getName());
            put ("Password", user.getPassword());
            put("Email", user.getEmail());
            put("AnimalId", animal.getId());
            put("ShelterID", shelterId);
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
