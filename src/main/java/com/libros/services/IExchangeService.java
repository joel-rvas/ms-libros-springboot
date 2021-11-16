package com.libros.services;

import com.libros.entity.ExchangeBean;
import com.libros.jpa.model.TblExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IExchangeService {

    Flux<TblExchange> obtenerTipoCambio(String mndaOrign, String mndaDest, double valorMndaOrigen) throws Exception;

    Flux<TblExchange> listarTiposCambioActivos() throws Exception;

    Mono<TblExchange> actualizarTipoCambio(ExchangeBean bean) throws Exception;

}
