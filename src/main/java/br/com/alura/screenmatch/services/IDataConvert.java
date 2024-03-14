package br.com.alura.screenmatch.services;

public interface IDataConvert {

    <T> T obterDatas(String json, Class<T> classNew); //Usamos o <T> T justamente quando não sabemos qual entidade será devolvida.


}
