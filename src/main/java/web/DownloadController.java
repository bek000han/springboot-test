package web;

import model.Painting;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import service.PaintingService;

@RestController
public class DownloadController {

    private final PaintingService paintingService;

    public DownloadController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Painting painting = paintingService.get(id);
        if (painting == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = painting.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(painting.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(painting.getFilename())
                .build();
        headers.setContentDisposition(build);


        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
