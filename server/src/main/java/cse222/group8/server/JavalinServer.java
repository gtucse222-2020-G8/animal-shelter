package cse222.group8.server;

public class JavalinServer implements Runnable {

	@Override
	public void run() {
		 Javalin.create(config -> {
            config.registerPlugin(getConfiguredOpenApiPlugin());
            config.defaultContentType = "application/json";
        }).routes(() -> {
            path("users", () -> {
                path("login", () -> {
                   // post(UserController::create);

                });
                path("register", () -> {
                    //post(UserController::create);

                });
                path("ownages", () -> {
                    path("get", () -> {
                        //get(UserController::getAll);

                    });
                    path("request", () -> {
                            path("add", () -> {
                               // post(UserController::create2);

                            });
                            path("get", () -> {
                                //get(UserController::getOne);

                            });



                    });

                });
                path("account", () -> {
                    path("update", () -> {
                        //post(UserController::create);

                    });

                });
                path("favoritePets", () -> {
                   // post(UserController::create);

                });


            });
            path("animal", () -> {
                path("filter", () -> {
                    path("get", () -> {
                       // get(UserController::getOne1);

                    });

                });

            });
            path("shelters", () -> {
                get(UserController::getShelt);
               // post(UserController::create);
                path("daily-task", () -> {
                    get(UserController::getTask);
                    post(UserController::createTask);
                    path("approve", () -> {

                        //post(UserController::create6);

                    });

                });
                path("registeration", () -> {

                    //get(UserController::getOne);

                });
                path("login", () -> {

                    //get(UserController::getOne);

                });
                path("animals", () -> {

                   // get(UserController::getOne);
                    path("update", () -> {

                        post(UserController::upAnimal);
                        path("picture", () -> {

                          //  post(UserController::create);

                        });


                    });

                    path("cats", () -> {
                        post(UserController::createCat);
                        get(UserController::getCa);

                    });
                    path("dogs", () -> {
                        post(UserController::createDog);
                        get(UserController::getDo);

                    });


                });
                path("adoption-request", () -> {

                    get(UserController::getAdopReq);
                    path("approve", () -> {

                        //post(UserController::create);

                    });

                });
                path("update", () -> {

                    path("password", () -> {

                        //post(UserController::create);

                    });
                    path("capacity", () -> {

                       // post(UserController::create);

                    });
                    path("name", () -> {

                      //  post(UserController::create);

                    });

                });
                path("diseases", () -> {
                    get(UserController::getDiseases);
                    post(UserController:: createDisease);

                });

            });
            path("cities", () -> {
                get(UserController::getCit);
                path("towns", () -> {
                    get(UserController::getTow);


                });

            });


        }).start(7019);

        System.out.println("Check out ReDoc docs at http://localhost:7019/redoc");
        System.out.println("Check out Swagger UI docs at http://localhost:7019/swagger-ui");
		
	}
	private static OpenApiPlugin getConfiguredOpenApiPlugin() {
        Info info = new Info().version("1.0").title("User API").description("Demo API with 5 operations");
        OpenApiOptions options = new OpenApiOptions(info)
                .activateAnnotationScanningFor("io.javalin.example.java")
                .path("/swagger-docs") // endpoint for OpenAPI json
                .swagger(new SwaggerOptions("/swagger-ui")) // endpoint for swagger-ui
                .reDoc(new ReDocOptions("/redoc")) // endpoint for redoc
                .defaultDocumentation(doc -> {
                    doc.json("500", ErrorResponse.class);
                    doc.json("503", ErrorResponse.class);
                });
        return new OpenApiPlugin(options);
    }

}
