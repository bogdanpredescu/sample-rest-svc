package io.github.bogdanpredescu.restsample;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.path;
import static spark.Spark.post;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import io.github.bogdanpredescu.service.DBService;
import io.github.bogdanpredescu.service.UserService;
import io.github.bogdanpredescu.service.UserServiceImp;
import spark.Request;
import spark.Response;
import spark.ResponseTransformer;
import spark.Spark;

public class ApplicationMain {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationMain.class);

    private static final Gson GSON = new Gson();
    private static final ResponseTransformer JSON_TRANSFORMER = GSON::toJson;
    private static final String JSON = "application/json";
    
    public static void main(String[] Args) {
        startServer();
    }
    
    private static UserService userSvc;

    public static int startServer() {
    	
    	DBService.start();
    	
        Spark.init();

        userSvc = new UserServiceImp();

        initializeRoutes();

        exception(JsonSyntaxException.class, ApplicationMain::handleInvalidInput);
        LOG.debug("Created exception handlers");

        Spark.awaitInitialization();
        LOG.debug("Ready");
        return Spark.port();
    }

    private static void initializeRoutes() {
        // Set response type to always be JSON
        before((request, response) -> response.type(JSON));

        path("/user", () -> {  
        	post("/:add", (req, res) -> {
        				userSvc.createUser(req.params("userid"), req.params("name"), req.params("email"));
        				res.status(200);
        	            return "success";
        		}, JSON_TRANSFORMER);
        });
               
        LOG.debug("Initialised routes");
    }

    private static void handleInvalidInput(Exception e, Request request, Response response) {
        response.status(400);
        errorResponse(e, request, response);
    }

    private static void errorResponse(Exception e, Request request, Response response) {
        response.type(JSON);
        response.body(GSON.toJson(Collections.singletonMap("error", e.getMessage())));
    }

    /**
     * For testing, as we want to start and stop the server.
     */
    public static void stopServer() {
    	
    	DBService.stop();
        LOG.debug("Asking server to stop");
        Spark.stop();
    }

}
