package service;

import model.Painting;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaintingService {

    private Map<String, Painting> db = new HashMap<>() {{
        put("1", new Painting("1", "hello.jpg"));
    }};

    public Collection<Painting> get() {
        return db.values();
    }

    public Painting get(String id) {
        return db.get(id);
    }

    public Painting remove(String id) {
        return db.remove(id);
    }

    public Painting save(String filename, String contentType, byte[] data) {
        Painting painting = new Painting();
        painting.setContentType(contentType);
        painting.setId(UUID.randomUUID().toString());
        painting.setFilename(filename);
        painting.setData(data);
        db.put(painting.getId(), painting);
        return painting;
    }
}
