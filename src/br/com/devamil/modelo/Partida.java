/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devamil.modelo;

/**
 *
 * @author eders
 */
public class Partida{
    
    private Double codigoPartida;
    private Integer quantidadeAssasinatos = 0; 
    private Integer quantidadeMortes = 0;
    private Jogador jogador;

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Integer getQuantidadeAssasinatos() {
        return quantidadeAssasinatos;
    }

    public void setQuantidadeAssasinatos(Integer quantidadeAssasinatos) {
        this.quantidadeAssasinatos = quantidadeAssasinatos;
    }

    public Integer getQuantidadeMortes() {
        return quantidadeMortes;
    }

    public void setQuantidadeMortes(Integer quantidadeMortes) {
        this.quantidadeMortes = quantidadeMortes;
    }
    
    public Double getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(Double codigoPartida) {
        this.codigoPartida = codigoPartida;
    }
    
}
