package ro.popescustefanradu.testcontainers.demo.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ro.popescustefanradu.testcontainers.demo.persistence.Dummy;
import ro.popescustefanradu.testcontainers.demo.persistence.DummyRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DummyController {

    private final DummyRepository repository;

    public record DummyModel(String key, String data) {
    }

    @GetMapping("/data")
    public ResponseEntity<List<DummyModel>> getAllData() {
        List<DummyModel> list = repository.findAll().stream()
                .map(dummy -> new DummyModel(dummy.getKey(), dummy.getData()))
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/insertData")
    public ResponseEntity<Void> insertData() {
        repository.save(Dummy.builder().key("test").data("rest").build());
        repository.save(Dummy.builder().key("test1").data("rest2").build());
        return ResponseEntity.ok().build();
    }
}
