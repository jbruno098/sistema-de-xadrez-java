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
	
	private void lugarDaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoDeXadrez(coluna, linha).paraPosicao());
	}
	
	private void inicio() {
		lugarDaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('e', 8, new King(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('e', 1,new King(tabuleiro, Cor.BRANCO));

	}

}
