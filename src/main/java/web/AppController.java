package web;

import model.Painting;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import service.PaintingService;

import java.io.IOException;
import java.util.Collection;

@RestController
public class AppController {

    private final PaintingService paintingService;

    public AppController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/painting")
    public Collection<Painting> get() {
        return paintingService.get();
    }

    @GetMapping("/painting/{id}")
    public Painting get(@PathVariable String id) {
        Painting painting = paintingService.get(id) ;
        if (painting == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return painting;
    }

    @DeleteMapping("/painting/{id}")
    public void delete(@PathVariable String id) {
        Painting painting = paintingService.remove(id);
        if (painting == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/painting")
    public Painting create(@RequestPart("data") MultipartFile file) throws IOException {
        return paintingService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
