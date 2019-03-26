package com.flaviomelo.mvc.web.conversor;

import com.flaviomelo.mvc.domain.Departamento;
import com.flaviomelo.mvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConverter  implements Converter<String, Departamento> {
    @Autowired
    DepartamentoService service;

    @Override
    public Departamento convert(String text) {
        if(text.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(text);
        return service.buscarPorId(id);
    }
}
