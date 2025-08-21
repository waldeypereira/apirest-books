package com.company.books.backend.response;

public class Metadata {
    private String tipo;
    private String codigo;
    private String date;

    public Metadata(String tipo, String codigo, String date) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.date = date;
    }

    // getters e setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
