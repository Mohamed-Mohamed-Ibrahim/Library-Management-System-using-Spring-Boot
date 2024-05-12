package org.example.Repository;
import org.example.Entities.Patron;
import org.springframework.data.repository.CrudRepository;

public interface PatronRepository extends CrudRepository<Patron, Integer>
{
}