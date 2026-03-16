package application;

import board_game.Tabuleiro;
import xadrez.PartidaDeXadrez;

public class Program {

	public static void main(String[] args) {
		
		PartidaDeXadrez partida = new PartidaDeXadrez();
		UI.imprimirTabuleiro(partida.getPecas());
	}
}
