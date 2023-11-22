package ro.popescustefanradu.testcontainers.demo.persistence;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;

public interface DummyRepository extends MapIdCassandraRepository<Dummy> {
}
