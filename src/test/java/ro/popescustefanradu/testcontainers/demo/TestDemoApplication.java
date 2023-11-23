package ro.popescustefanradu.testcontainers.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ro.popescustefanradu.testcontainers.demo.persistence.Dummy;
import ro.popescustefanradu.testcontainers.demo.persistence.DummyRepository;

import java.util.List;

@SpringBootTest
@Testcontainers
@TestConfiguration(proxyBeanMethods = false)
public class TestDemoApplication {
    @Container
    @ServiceConnection
    static CassandraContainer<?> cassandra = new CassandraContainer<>(DockerImageName.parse("cassandra:3"));
    @Autowired
    private DummyRepository repository;

    @Test
    public void test() {
        Assertions.assertTrue(cassandra.isRunning());

        Dummy expected = Dummy.builder().key("test").data("data").build();
        repository.save(expected);
        List<Dummy> all = repository.findAll();

        Assertions.assertIterableEquals(List.of(expected), all);
    }

}
