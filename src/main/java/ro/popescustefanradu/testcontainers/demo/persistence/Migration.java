package ro.popescustefanradu.testcontainers.demo.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraConnectionDetails;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class Migration extends AbstractCassandraConfiguration {
    private final CassandraProperties props;
    private final CassandraConnectionDetails connectionDetails;

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return List.of(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName()).ifNotExists().withSimpleReplication());
    }

    @Override
    protected String getKeyspaceName() {
        return props.getKeyspaceName();
    }


    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getContactPoints() {
        return connectionDetails.getContactPoints().stream()
                .map(node -> node.host() + ":" + node.port())
                .collect(Collectors.joining(","));
    }

    @Override
    protected String getLocalDataCenter() {
        return connectionDetails.getLocalDatacenter();
    }
}
