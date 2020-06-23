package io.javalin.example.java.user;
package cse222.group8.server;

import io.javalin.example.java.ErrorResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import io.javalin.plugin.openapi.annotations.*;

// This is a controller, it should contain logic related to client/server IO
public class UserController {
    @OpenApi(
            summary = "Get cities",
            operationId = "getAllCities",
            path = "/cities",
            method = HttpMethod.GET,
            tags = {"Cities"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User[].class)})
            }
    )
    public static void getCit(Context ctx) {
        ctx.json(User.getCity());
    }
    @OpenApi(
            summary = "Get all town",
            operationId = "getAllTown",
            path = "/cities/towns",
            method = HttpMethod.GET,
            tags = {"Cities"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = City[].class)})
            }
    )
    public static void getTow(Context ctx) {
        ctx.json(City.getTowns());
    }
    @OpenApi(
            summary = "Get diseases",
            operationId = "getDisesase ById",
            path = "/shelter/diseases",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"Shelter"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Disease.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )

    public static void getDiseases(Context ctx) {
        PriorityQueue<Disease> dis =Shelter.getDiseasedAnimals();
        if (dis == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(dis);
        }
    }
    @OpenApi(
            summary = "Create disease",
            operationId = "creaeteDisease",
            path = "/shelter/diseases",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Disease.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void createDisease(Context ctx) {
       Disease dis = ctx.bodyAsClass(Disease.class);
        SystemRequest.addDiseasedAnimal(dis);
        ctx.status(201);
    }
   /* @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/shelter/adoption-request/approve",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create15(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/

    @OpenApi(
            summary = "Get all users",
            operationId = "getAllUsers",
            path = "/shelter/adoption-request",
            method = HttpMethod.GET,
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User[].class)})
            }
    )
    public static void getAdopReq(Context ctx) {
        ctx.json(User.getRequests());
    }
   /* @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/shelter/update/name",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void shelterUpdateName(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/
  /*  @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/shelter/update/capacity",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create13(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/

  /*  @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/shelter/update/password",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create12(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/
    @OpenApi(
            summary = "Create dogs",
            operationId = "createDogs",
            path = "/shelter/animals/dogs",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Animal.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void createDog(Context ctx) {
        Animal dog = ctx.bodyAsClass(Animal.class);
       SystemRequest.addDog(dog);
        ctx.status(201);
    }
    @OpenApi(
            summary = "Create cats",
            operationId = "createCats",
            path = "/shelter/animals/cats",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Animal.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void createCat(Context ctx) {
        Animal cat = ctx.bodyAsClass(Animal.class);
        SystemRequest.addCat(cat);
        ctx.status(201);
    }
    @OpenApi(
            summary = "Get dogs",
            operationId = "getDogs",
            path = "/shelter/animals/dogs",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "dogId", type = Integer.class, description = "The dog ID")},
            tags = {"Shelter"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )

    public static void getDo(Context ctx) {
        Animal shel =Shelter.getDog( validPathParamUserId(ctx));
        if (shel == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(shel);
        }
    }
    @OpenApi(
            summary = "Get cat by ID",
            operationId = "getCatById",
            path = "/Shelter/Animals/cats",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "catId", type = Integer.class, description = "The cat ID")},
            tags = {"Shelter"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Shelter.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )

    public static void getCa(Context ctx) {
        Animal shel =Shelter.getCat( validPathParamUserId(ctx));
        if (shel == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(shel);
        }
    }

 /*   @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/shelter/animals/update/picture",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Animal.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void createPic(Context ctx) {
        File fi = ctx.bodyAsClass(Animal.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/
    @OpenApi(
            summary = "Create update Animal",
            operationId = "createUpdateAnimal",
            path = "/shelter/animals/update",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Animal.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void upAnimal(Context ctx) {
       Animal an = ctx.bodyAsClass(Animal.class);
        SystemRequest. updateAnimal(an);
        ctx.status(201);
    }
   /* @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/shelter/animals",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"Shelter"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )

    public static void getOne6(Context ctx) {
        int a = SystemRequest.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(user);
        }
    }*/
  /*  @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/shelter/login",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"Shelter"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )

    public static void getOne5(Context ctx) {
        User user = UserService.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(user);
        }
    }*/
   /* @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/shelter/registeration",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"Shelter"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )

    public static void getOne4(Context ctx) {
        User user = UserService.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(user);
        }
    }*/
  /*  @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/shelter",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from =Town.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void createShelt(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/
    @OpenApi(
            summary = "Get shelter users",
            operationId = "getAllShelter",
            path = "/shelter",
            method = HttpMethod.GET,
            tags = {"Shelter"},
    responses = {
        @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Town[].class)})
    }
    )
    public static void getShelt(Context ctx) {
        ctx.json(Town.getShelters());
    }

   /* @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/shelter/daily-task/approve",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create6(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/
    @OpenApi(
            summary = "Create task",
            operationId = "createTask",
            path = "/shelter/daily-task",
            method = HttpMethod.POST,
            tags = {"Shelter"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = Task.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void createTask(Context ctx) {
        Task task = ctx.bodyAsClass(Task.class);
        Shelter shel=ctx.bodyAsClass(Shelter.class);
        SystemRequest.addTask(task,shel);
        ctx.status(201);
    }
    @OpenApi(
            summary = "Get Task",
            operationId = "getTask",
            path = "/shelter/daily-task",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "taskId", type = Integer.class, description = "The task ID")},
            tags = {"Shelter"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Task.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )

    public static void getTask(Context ctx) {
        String t = Task.getTask();
        if (t == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(t);
        }
    }
   /* @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/animal/filter/get",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"Animal"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void getOne1(Context ctx) {
        User user = UserService.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(user);
        }
    }*/
   /* @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/users/favoritePets",
            method = HttpMethod.POST,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create4(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/
  /*  @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/users/account/update",
            method = HttpMethod.POST,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create3(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/
    /*@OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/users/ownages/request/add",
            method = HttpMethod.POST,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create2(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }*/

  /*  @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/users/register",
            method = HttpMethod.POST,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create1(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }

    @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/users/login",
            method = HttpMethod.POST,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void create(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        UserService.save(user.name, user.email);
        ctx.status(201);
    }

    @OpenApi(
            summary = "Get all users",
            operationId = "getAllUsers",
            path = "/users/ownages/get",
            method = HttpMethod.GET,
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User[].class)})
            }
    )
    public static void getAll(Context ctx) {
        ctx.json(UserService.getAll());
    }

    @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/users/ownages/request/get",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = ErrorResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = ErrorResponse.class)})
            }
    )
    public static void getOne(Context ctx) {
        User user = UserService.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse("User not found");
        } else {
            ctx.json(user);
        }
    }*/



    // Prevent duplicate validation of userId
    private static int validPathParamUserId(Context ctx) {
        return ctx.pathParam("userId", Integer.class).check(id -> id > 0).get();
    }

}