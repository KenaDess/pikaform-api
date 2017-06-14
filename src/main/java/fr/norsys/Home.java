package fr.norsys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "/", description = "Home of Pokemon Arena")
public class Home {

    @ApiOperation(value = "Home of Arena",
            notes = "This route only serves to validate the connection to the API",
            responseContainer = "String")
    @CrossOrigin(origins = "http://127.0.0.1:8081", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
    @GetMapping("/")
    String hello() {
        return "hello world";
    }
}
