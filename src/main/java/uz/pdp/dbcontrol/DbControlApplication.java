package uz.pdp.dbcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbControlApplication.class, args);
    }

}


// GraphQL

// /grapgql

// Types
// Scalar types: Int, String, Float, Boolean
// Object types: Person, Product, {}
// Query type -> GET
// Mutation type -> POST/PUT/PATCH/DELETE
// Enum types -> Gender(MALE,FEMALE)
// Input -> ReqBody
// List type
// Interface type
// Subscription type
// Union
