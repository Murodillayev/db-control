package uz.pdp.dbcontrol;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<AuthUser, String> {
}
