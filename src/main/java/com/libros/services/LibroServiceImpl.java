package com.libros.services;

import com.libros.data.model.CltLibro;
import com.libros.data.repository.CltLibroReactiveRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
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
    public Flowable<CltLibro> obtenerLibros() {
        return this.cltLibroReactiveRepository.findAll();
    }

    @Override
    public Observable<CltLibro> obtenerLibroPorCodigo(String codigo) {
        return this.cltLibroReactiveRepository.findAllByCodigo(codigo);
    }

    @Override
    public Single<CltLibro> registrarLibro(CltLibro libro) {
        return this.cltLibroReactiveRepository.save(libro);
    }

    @Override
    public Completable eliminarLibro(CltLibro libro) {
        return this.cltLibroReactiveRepository.delete(libro);
    }

}