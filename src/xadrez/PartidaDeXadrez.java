package xadrez;

import board_game.Posicao;
import board_game.Tabuleiro;
import xadrez_pecas.King;
import xadrez_pecas.Torre;

public class PartidaDeXadrez {
	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inicio();
	}
	
	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}
	
	private void inicio() {
		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7,0));
		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(7,7));
		tabuleiro.lugarDaPeca(new King(tabuleiro, Cor.PRETO), new Posicao(0, 4));
		tabuleiro.lugarDaPeca(new King(tabuleiro, Cor.BRANCO), new Posicao(7, 4));

	}

}
