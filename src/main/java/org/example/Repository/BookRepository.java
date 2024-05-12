package org.example.Repository;
import org.example.Entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer>
{
}