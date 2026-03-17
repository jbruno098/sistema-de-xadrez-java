package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoDeXadrez;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		
		while (true) {
			UI.imprimirTabuleiro(partida.getPecas());
			System.out.println();
			System.out.print("Posicao de Origem: ");
			PosicaoDeXadrez origem = UI.lerPosicao(sc);
			
			System.out.println();
			System.out.print("Posição destino: ");
			PosicaoDeXadrez destino = UI.lerPosicao(sc);
			
			PecaDeXadrez pecaCapturada = partida.executarMovimentoXadrez(origem, destino);
		}
	}
}
