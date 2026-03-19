package xadrez_pecas;

import board_game.Posicao;
import board_game.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Bispo extends PecaDeXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		// noroeste NW
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() -1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() -1, p.getColuna() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// nordeste NE
		p.setValues(posicao.getLinha() -1, posicao.getColuna() +1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() -1, p.getColuna() +1);
		}
		if (getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sudeste SE
		p.setValues(posicao.getLinha() +1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() +1, p.getColuna()+1);
		}
		if (getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sudoeste SW
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() -1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() +1, p.getColuna() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
	
}
