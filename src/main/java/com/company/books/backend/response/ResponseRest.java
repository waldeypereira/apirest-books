package com.company.books.backend.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseRest<T> {

    private final List<Metadata> metadata = new ArrayList<>();
    private T data;

    // Getters e Setters
    public List<Metadata> getMetadata() {
        return metadata;
    }

    public void addMetadata(String tipo, String codigo, String date) {
        this.metadata.add(new Metadata(tipo, codigo, date));
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
