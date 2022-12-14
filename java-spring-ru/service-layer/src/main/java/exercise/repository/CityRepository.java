package exercise.repository;

import exercise.model.City;
import liquibase.pro.packaged.C;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findByNameStartsWithIgnoreCase(String name);

    List<City> findAllByOrderByNameAsc();
}
