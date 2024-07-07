package br.com.escritorioDeVaquejada.vqr.mappers;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {
    private static final org.modelmapper.ModelMapper mapper = new org.modelmapper.ModelMapper();

    public static <O,D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin,destination);
    }
    public static <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> endList = new ArrayList<>();
        for(O x:origin){
            endList.add(mapper.map(x,destination));
        }
        return endList;
    }
}
