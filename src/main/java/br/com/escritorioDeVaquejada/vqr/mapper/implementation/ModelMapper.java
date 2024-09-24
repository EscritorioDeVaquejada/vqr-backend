package br.com.escritorioDeVaquejada.vqr.mapper.implementation;

import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ModelMapper implements Mapper {
    private static final org.modelmapper.ModelMapper mapper = new org.modelmapper.ModelMapper();

    public <O,D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin,destination);
    }
    public <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> endList = new ArrayList<>();
        for(O x:origin){
            endList.add(mapper.map(x,destination));
        }
        return endList;
    }
    public <O, D> void copyProperties(O source, D target){
        mapper.map(source, target);
    }
}
