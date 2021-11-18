package com.libros.services;

import com.libros.jpa.model.CltLibro;
import com.libros.jpa.repository.CltLibroReactiveRepository;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements ILibroService {

    private static final Logger logger = LoggerFactory.getLogger(LibroServiceImpl.class);

    @Autowired
    private CltLibroReactiveRepository cltLibroReactiveRepository;

    @Override
    public Flowable<CltLibro> findAll() {
        return this.cltLibroReactiveRepository.findAll();
    }

    @Override
    public Observable<CltLibro> obtenerLibroPorCodigo(String codigo) {
        return this.cltLibroReactiveRepository.findAllByCodigo(codigo);
    }

}