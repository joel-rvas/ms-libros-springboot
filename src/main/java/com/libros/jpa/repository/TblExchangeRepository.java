package com.libros.jpa.repository;

import com.libros.jpa.model.TblExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblExchangeRepository extends JpaRepository<TblExchange, Long> {

    List<TblExchange> findByMndaOrignAndMndaDestAndIndEstado(String mndaOrign, String mndaDest, int indEstado);
    List<TblExchange> findByIndEstado(int indEstado);

}