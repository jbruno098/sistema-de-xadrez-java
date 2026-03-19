package xadrez_pecas;

import board_game.Posicao;
import board_game.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class King extends PecaDeXadrez{
	
	private PartidaDeXadrez partida;

	public King(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean podeMover(Posicao pos) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(pos);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testeRoque(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorDeMovimentos() == 0;
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		//pra cima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//baixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal cima esquerda(noroeste NW)
		p.setValues(posicao.getLinha() -1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal cima direita(nordeste NE)
		p.setValues(posicao.getLinha() -1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal baixo esquerda(sudoeste SW)
		p.setValues(posicao.getLinha() +1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//diagonal baixo direita(sudeste SE)
		p.setValues(posicao.getLinha() +1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// movimento especial Roque
		if (getContadorDeMovimentos() == 0 && !partida.getCheck()) {
			// Roque pequeno
			Posicao pt1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testeRoque(pt1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() +2] = true;
				}
			}
			Posicao pt2 = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
			if (testeRoque(pt2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() -2] = true;
				}
			}		
		}
		
		return mat;
	}
}
