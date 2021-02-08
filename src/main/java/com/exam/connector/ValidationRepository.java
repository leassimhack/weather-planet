package com.exam.connector;

import com.exam.connector.model.ValidationJob;
import org.springframework.data.repository.CrudRepository;

public interface ValidationRepository extends CrudRepository<ValidationJob, Long> {

}
