package edu.pwr.pizzeria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pwr.pizzeria.exception.StandardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients
public abstract class ClientBasedTest {

    @Autowired
    private ObjectMapper mapper;

    public StandardException decodeException(String exceptionMessage) {
        try {
            return mapper.readValue(exceptionMessage, StandardException.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
