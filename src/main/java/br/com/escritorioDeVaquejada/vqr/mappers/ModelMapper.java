package br.com.escritorioDeVaquejada.vqr.mappers;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper{
    public static org.modelmapper.ModelMapper mapper = new org.modelmapper.ModelMapper();
    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }
    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<>();
        for(O object: origin){
            destinationObjects.add(mapper.map(object, destination));
        }
        return destinationObjects;
    }
}
