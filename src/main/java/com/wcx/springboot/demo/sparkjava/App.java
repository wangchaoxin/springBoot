package com.wcx.springboot.demo.sparkjava;

import com.wcx.springboot.demo.boot.model.User;

import javax.servlet.MultipartConfigElement;
import java.io.InputStream;
import java.util.function.Consumer;

import static spark.Spark.*;

public class App {


    public static void main(String[] args) {
        App app = new App();

        //you can also manually start the server by calling init().
//        init();
        //You can specify what should happen if initialization fails:
        initExceptionHandler((e) -> System.out.println("Uh-oh"));

        //By default, Spark runs on port 4567. If you want to set another port, use port().
        // This has to be done before declaring routes and filters:
        port(8080);

        app.start();
    }

    //start the server
    void start() {
        get("/hello", (req, res) -> {
            Object foo = req.attribute("foo");//params
//            res.type("text/xml");  //set response type
//            res.body("");  //set response body
//            res.redirect("/bar");  //redirect
//            return res;
            return new User();
        }, new JsonTransformer());
        post("/", (request, response) -> {
            // Create something
            return "post";
        });

        put("/", (request, response) -> {
            // Update something
            return "put";
        });

        delete("/", (request, response) -> {
            // Annihilate something
            return "delete";
        });

        options("/", (request, response) -> {
            // Appease something
            return "option";
        });
        //Route patterns can include named parameters, accessible via the params() method on the request object:
        // matches "GET /hello/foo" and "GET /hello/bar"
        // request.params(":name") is 'foo' or 'bar'
        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });

        //Path groups
        /*path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Received api call"));
            path("/email", () -> {
                post("/add",       EmailApi.addEmail);
                put("/change",     EmailApi.changeEmail);
                delete("/remove",  EmailApi.deleteEmail);
            });
            path("/username", () -> {
                post("/add",       UserApi.addUsername);
                put("/change",     UserApi.changeUsername);
                delete("/remove",  UserApi.deleteUsername);
            });
        });*/

        //upload file
        post("/yourUploadPath", (request, response) -> {
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
                // Use the input stream to create a file
            }
            return "File uploaded";
        });

    }

    //stop the server
    void stop() {
        stop();
    }

    private Consumer<Exception> initExceptionHandler = (e) -> {
        System.out.println(e.getMessage());
        System.exit(100);
    };


}
