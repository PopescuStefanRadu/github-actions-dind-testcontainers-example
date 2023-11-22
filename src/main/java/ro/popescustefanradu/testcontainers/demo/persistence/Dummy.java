package ro.popescustefanradu.testcontainers.demo.persistence;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(Dummy.DBNames.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Dummy {
    interface DBNames {
        String TABLE_NAME = "dummy";
        String FIELD_KEY = "key";
        String FIELD_DATA = "data";
    }

    @PrimaryKeyColumn(name = DBNames.FIELD_KEY, type = PrimaryKeyType.PARTITIONED)
    private String key;

    @Column(DBNames.FIELD_DATA)
    private String data;
}
