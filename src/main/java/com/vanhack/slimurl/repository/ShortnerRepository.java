package com.vanhack.slimurl.repository;

import com.vanhack.slimurl.model.Shortner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortnerRepository extends CrudRepository<Shortner, String> {
}
