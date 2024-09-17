package io.paymeter.assessment.domain.repository;

public interface RepositoryWrapper<T> {

    /**
     * Finds the object by id. Otherwise then must return an error.
     *
     * @param key the key
     * @return respective entity.
     */
    T findById(String key);
}
