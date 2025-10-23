package uz.pdp.dbcontrol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ServiceTest {


    @ParameterizedTest
    @MethodSource(value = "serviceStream")
    void testServices(CrudService service) {
        Assertions.assertNotNull(service.getAll());
    }

    static Stream<CrudService> serviceStream() {
        return Stream.of(new AuthService(), new ProductService());
    }
}
