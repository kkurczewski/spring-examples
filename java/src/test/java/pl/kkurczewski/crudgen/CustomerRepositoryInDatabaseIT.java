package pl.kkurczewski.crudgen;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * This test will try to connect to real database, example setup using docker:
 * <pre>
 * docker run -it \
 *     -e MYSQL_ROOT_PASSWORD=rootpass \
 *     -e MYSQL_USER=testuser \
 *     -e MYSQL_PASSWORD=testpass \
 *     -e MYSQL_DATABASE=testdb \
 *     -p 3306:3306 \
 *     mariadb:10.4.12
 * </pre>
 **/

@AutoConfigureTestDatabase(replace = NONE)
@TestInstance(PER_CLASS)
@Disabled
class CustomerRepositoryInDatabaseIT extends CustomerRepositoryInMemoryIT {

    private final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();

    @Autowired
    DataSource dataSource;

    @BeforeAll
    void beforeAll() {
        databasePopulator.addScripts(new ClassPathResource("drop-table.sql"));
        databasePopulator.addScripts(new ClassPathResource("test-schema.sql"));
        databasePopulator.execute(dataSource);
    }

    @AfterAll
    void afterAll() {
        databasePopulator.addScripts(new ClassPathResource("drop-table.sql"));
        databasePopulator.execute(dataSource);
    }
}