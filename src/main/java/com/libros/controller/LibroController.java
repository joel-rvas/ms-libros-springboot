package com.libros.controller;

import com.libros.data.model.CltLibro;
import com.libros.services.ILibroService;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin()
@RequestMapping("/libro")
public class LibroController {
    private static final Logger logger = LoggerFactory.getLogger(LibroController.class);

    @Autowired
    private ILibroService iLibroService;

    @GetMapping
    private Flowable<CltLibro> findAll() {
        return this.iLibroService.obtenerLibros();
    }

    @GetMapping(value = "/{codLibro}")
    private Observable<CltLibro> obtenerLibro(@PathVariable String codLibro) {
        return this.iLibroService.obtenerLibroPorCodigo(codLibro);
    }

    @PostMapping
    public Single<CltLibro> registrarLibro(@RequestBody CltLibro libro) throws Exception {
        return iLibroService.registrarLibro(libro);
    }

    @PutMapping(value = "/{codLibro}")
    public Observable<CltLibro> actualizarLibro(@PathVariable String codLibro, @RequestBody CltLibro libro) {
        return iLibroService.obtenerLibroPorCodigo(codLibro).doOnNext(u -> {
            libro.setId(u.getId());
            iLibroService.registrarLibro(libro).subscribe();
        }).flatMap(item -> {
                    return Observable.just(libro);
                }
        );
    }

    @DeleteMapping(value = "/{codLibro}")
    public Observable<CltLibro> deleteLibro(@PathVariable String codLibro, @RequestBody CltLibro libro) throws Exception {
        return iLibroService.obtenerLibroPorCodigo(codLibro).doOnNext(u -> {
            libro.setId(u.getId());
            iLibroService.eliminarLibro(libro).subscribe();
        }).flatMap(item -> {
                    return Observable.just(libro);
                }
        );
    }

   /* @RequestMapping(method = RequestMethod.GET, value = "/single")
    public Single<String> single() {
        return Single.just("single value");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/singleWithResponse")
    public ResponseEntity<Single<String>> singleWithResponse() {
        return new ResponseEntity<>(Single.just("single value"),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/singleCreatedWithResponse")
    public Single<ResponseEntity<String>> singleOuterWithResponse() {
        return Single.just(new ResponseEntity<>("single value", HttpStatus.CREATED));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/throw")
    public Single<Object> error() {
        return Single.error(new RuntimeException("Unexpected"));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/multiple")
    public Single<List<String>> multiple() {
        return Observable.just("multiple", "values").toList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Observable<String> getInvoices() {
        return Observable.just(
                "Acme",
                "Oceanic"
        );
    }*/

}
