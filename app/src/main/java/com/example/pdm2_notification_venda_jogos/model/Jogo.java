package com.example.pdm2_notification_venda_jogos.model;

import java.util.Objects;

public class Jogo {

    private String nome;
    private Double preco;
    private String genero;
    private String plataforma;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return Objects.equals(nome, jogo.nome) && Objects.equals(preco, jogo.preco) && Objects.equals(genero, jogo.genero) && Objects.equals(plataforma, jogo.plataforma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco, genero, plataforma);
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", genero='" + genero + '\'' +
                ", plataforma='" + plataforma + '\'' +
                '}';
    }
}
