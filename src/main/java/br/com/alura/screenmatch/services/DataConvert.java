package br.com.alura.screenmatch.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert implements IDataConvert {

    private ObjectMapper mapper = new ObjectMapper();



    public <T> T obterDatas(String json, Class<T> classNew) { // <T> T --> Generics permitem criar classes, interfaces e métodos que podem trabalhar com tipos desconhecidos ou parâmetros genéricos.
        try {
            return mapper.readValue(json, classNew);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
