package com.libros.services;

import com.libros.jpa.model.CltLibro;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface ILibroService {
    Flowable<CltLibro> findAll();

    Observable<CltLibro> obtenerLibroPorCodigo(String codigo);
/*
    Flux<TblExchange> obtenerTipoCambio(String mndaOrign, String mndaDest, double valorMndaOrigen) throws Exception;

    Flux<TblExchange> listarTiposCambioActivos() throws Exception;

    Mono<TblExchange> actualizarTipoCambio(ExchangeBean bean) throws Exception;

 */

}
