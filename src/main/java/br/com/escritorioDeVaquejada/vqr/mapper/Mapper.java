package br.com.escritorioDeVaquejada.vqr.mapper;

import java.util.List;

public interface Mapper {
    <O,D> D parseObject(O origin, Class<D> destination);
    <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination);
    <O, D> void copyProperties(O source, D target);
}
