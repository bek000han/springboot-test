package bek000han.gallery_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    private Map<String, Painting> db = new HashMap<>() {{
        put("1", new Painting("1", "hello.jpg"));
    }};

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/painting")
    public Collection<Painting> get() {
        return db.values();
    }

    @GetMapping("/painting/{id}")
    public Painting get(@PathVariable String id) {
        Painting painting = db.get(id);
        if (painting == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return painting;
    }
}
