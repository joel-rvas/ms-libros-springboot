package com.libros.services;

import com.libros.data.model.CltLibro;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface ILibroService {
    Flowable<CltLibro> obtenerLibros();

    Observable<CltLibro> obtenerLibroPorCodigo(String codigo);

    Single<CltLibro> registrarLibro(CltLibro libro);

    Completable eliminarLibro(CltLibro libro);

}
