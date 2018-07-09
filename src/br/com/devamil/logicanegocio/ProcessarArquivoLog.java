/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devamil.logicanegocio;

import br.com.devamil.modelo.Jogador;
import br.com.devamil.modelo.Partida;
import br.com.devamil.modelo.Ranking;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eders
 */
public class ProcessarArquivoLog {
    
    public void processarArquivoLog (BufferedReader bufferedReaderArquivLog) {
        
        //Cria Uma Lista Para Armazenar Os Rankings
        List<Ranking> listaRanking = new ArrayList<>();
        
        //Cria Uma Lista Para Armazenar Os Jogadores
        List<Jogador> listaJogador = new ArrayList<>();

        //Adiciona O Jogador Roman
        Jogador jogadorRoman = new Jogador();
        jogadorRoman.setCodigoJogador(1);
        jogadorRoman.setNomeJogador("Roman");
        listaJogador.add(jogadorRoman);

        //Adiciona O Jogador Nick
        Jogador jogadorNick = new Jogador();
        jogadorNick.setCodigoJogador(2);
        jogadorNick.setNomeJogador("Nick");
        listaJogador.add(jogadorNick);
        
        try {
            
        //Lê A Primeira Linha Do Log Do Jogo
        String linhaArquivoLog = bufferedReaderArquivLog.readLine();

        //Cria A Partida
        Partida partida = new Partida();
        Ranking ranking = new Ranking();                    

        //Verifica A Linha Se Tem New Match Para Pegar O Número Da Partida
        if (linhaArquivoLog.contains("New match")) {

            String[] codigoPartida = linhaArquivoLog.split(" ");
            
            Double codigoPartidaDouble = new Double(Double.valueOf(codigoPartida[5]));

            partida.setCodigoPartida(codigoPartidaDouble);

            //Incluí O Jogador Roman No Ranking
            ranking.setPartida(partida);
            ranking.setJogador(jogadorRoman);
            listaRanking.add(ranking);
            
            //Incluí O Jogador Nick No Ranking
            partida = new Partida();
            ranking = new Ranking();
            
            partida.setCodigoPartida(codigoPartidaDouble);
            ranking.setPartida(partida);
            ranking.setJogador(jogadorNick);
            
            listaRanking.add(ranking);

        }

        System.out.println(linhaArquivoLog);

        while (linhaArquivoLog != null) {
            
            //Faz As Contagens Das Mortes Sofridas Pelos Outros Jogadores
            if (linhaArquivoLog.contains("killed") && linhaArquivoLog.contains("using")) {
                
                String[] nomeJogador = linhaArquivoLog.split(" ");
                
                for (Ranking rankingAssasinatos : listaRanking) {
                    
                    if (rankingAssasinatos.getJogador().getNomeJogador().equalsIgnoreCase(nomeJogador[3])) {
                        
                        Integer quantidadeAssasinatos = ranking.getPartida().getQuantidadeAssasinatos();
                        
                        rankingAssasinatos.getPartida().setQuantidadeAssasinatos(++quantidadeAssasinatos);
                    }
                    
                    if (rankingAssasinatos.getJogador().getNomeJogador().equalsIgnoreCase(nomeJogador[5])) {

                        Integer quantidadeMortes = rankingAssasinatos.getPartida().getQuantidadeMortes();

                        rankingAssasinatos.getPartida().setQuantidadeMortes(++quantidadeMortes);

                    }
                }
            }
            
            //Faz As Contagens Das Mortes Sofridas Pelo <WORLD>
            if (linhaArquivoLog.contains("<WORLD> killed")) {
                
                String[] nomeJogador = linhaArquivoLog.split(" ");
                
                for (Ranking rankingMortes : listaRanking) {

                    if (rankingMortes.getJogador().getNomeJogador().equalsIgnoreCase(nomeJogador[5])) {

                        Integer quantidadeMortes = rankingMortes.getPartida().getQuantidadeMortes();

                        rankingMortes.getPartida().setQuantidadeMortes(++quantidadeMortes);

                    }
                }
            }

            linhaArquivoLog = bufferedReaderArquivLog.readLine();

            System.out.println(linhaArquivoLog);

        }
        
        
        //Fecha O Arquivo Lido
        bufferedReaderArquivLog.close();
        
        //Monta O Ranking Final Da Partida
        String rankingFinalJogo = "\n";
        rankingFinalJogo += "Ranking Dev Amil 1.0 ";
        rankingFinalJogo += "\n";
        
        for (Ranking rankingFinal : listaRanking) {
            
            rankingFinalJogo += "\n";
            rankingFinalJogo += "Partida: " + rankingFinal.getPartida().getCodigoPartida();
            rankingFinalJogo += "\n";
            rankingFinalJogo += "Jogador: " + rankingFinal.getJogador().getNomeJogador();
            rankingFinalJogo += "\n";
            rankingFinalJogo += "Quantidade De Assasinatos: " + rankingFinal.getPartida().getQuantidadeAssasinatos();
            rankingFinalJogo += "\n";
            rankingFinalJogo += "Quantidade De Mortes: " + rankingFinal.getPartida().getQuantidadeMortes();
            rankingFinalJogo += "\n";

            if (rankingFinal.getPartida().getQuantidadeMortes() == 0) {
                
                rankingFinalJogo += "Parabéns Você Ganhou Um 'award' Por Não Morrer Nenhuma Vez Durante A Partida!";
                rankingFinalJogo += "\n";
                
            }
        }
        
        System.out.println(rankingFinalJogo);
        
        } catch (Exception e) {
        
            System.out.println("Erro :" + e);
            
        }

    }
}
    
