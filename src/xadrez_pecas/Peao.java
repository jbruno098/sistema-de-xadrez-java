package xadrez_pecas;

import board_game.Posicao;
import board_game.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {
	
	private PartidaDeXadrez partida;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}
	
			
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0);
		
		if (getCor() == Cor.BRANCO) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContadorDeMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// en Passant (Brancas)
			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if (getTabuleiro().posicaoExiste(esquerda) && existeOponente(esquerda) && getTabuleiro().peca(esquerda) == partida.enPassantVulneravel()) {
					mat[esquerda.getLinha() -1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if (getTabuleiro().posicaoExiste(direita) && existeOponente(direita) && getTabuleiro().peca(direita) == partida.enPassantVulneravel()) {
					mat[direita.getLinha() -1][direita.getColuna()] = true;
				}				
			}
		}
		else {
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getContadorDeMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna()-1);
			if(getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna()+1);
			if(getTabuleiro().posicaoExiste(p) && existeOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// en Passant (Pretas)
			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if (getTabuleiro().posicaoExiste(esquerda) && existeOponente(esquerda) && getTabuleiro().peca(esquerda) == partida.enPassantVulneravel()) {
					mat[esquerda.getLinha() +1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if (getTabuleiro().posicaoExiste(direita) && existeOponente(direita) && getTabuleiro().peca(direita) == partida.enPassantVulneravel()) {
					mat[direita.getLinha()+1][direita.getColuna()] = true;
				}				
			}			
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
