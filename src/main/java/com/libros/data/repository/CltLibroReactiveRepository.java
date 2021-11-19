package com.libros.data.repository;

import com.libros.data.model.CltLibro;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CltLibroReactiveRepository extends RxJava3CrudRepository<CltLibro, String> {

    Observable<CltLibro> findAllByCodigo(String codigo);
}