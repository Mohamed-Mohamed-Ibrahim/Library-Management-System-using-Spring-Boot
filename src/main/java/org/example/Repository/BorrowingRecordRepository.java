package org.example.Repository;
import org.example.Entities.BorrowingRecord;
import org.springframework.data.repository.CrudRepository;

public interface BorrowingRecordRepository extends CrudRepository<BorrowingRecord, Integer>
{
}