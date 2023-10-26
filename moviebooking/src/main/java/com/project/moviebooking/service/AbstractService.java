package com.project.moviebooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.moviebooking.exception.ExceptionType;
import com.project.moviebooking.exception.MovieException;


/**
 *
 * @author uday
 * @param <T>
 */
public interface AbstractService<T> {

    public JpaRepository getJpaRepository();

    default Object save(Object entity) {
        return getJpaRepository().save(entity);
    }

    default List saveAll(List<Object> entityList) {
        return getJpaRepository().saveAll(entityList);
    }

//    default Object delete(Object entity) {
//		((BaseEntity) entity).setDeleted(Boolean.TRUE);
//        return getJpaRepository().save(entity);
//    }
    
    default void deleteEntity(T entity) {
		getJpaRepository().delete(entity);
	}
    
    default T findById(Long id) {
        Optional<T> optional = getJpaRepository().findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    default T findByIdWithException(Long id) throws MovieException {
		Optional<T> optional = getJpaRepository().findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new MovieException(404, ExceptionType.INFO, "Not Found");
    }

    default List<T> findAll() {
        return getJpaRepository().findAll();
    }

    default Page<T> findAll(Pageable pageable) {
        return getJpaRepository().findAll(pageable);
    }

    default boolean existsById(Long id) {
		return getJpaRepository().existsById(id);
	}
}
