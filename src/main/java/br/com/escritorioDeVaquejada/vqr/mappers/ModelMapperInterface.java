package br.com.escritorioDeVaquejada.vqr.mappers;

import java.util.List;

public interface ModelMapperInterface {
    public  <O,D> D parseObject(O origin, Class<D> destination);
    public  <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination);
}
