package kim.jaehoon.dsmforest.repository;

import kim.jaehoon.dsmforest.domain.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

}
