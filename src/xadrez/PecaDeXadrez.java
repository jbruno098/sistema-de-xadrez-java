package xadrez;

import board_game.Peca;
import board_game.Posicao;
import board_game.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{
	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	protected boolean existeOponente(Posicao pos) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(pos);
		return p != null && p.getCor() != cor;
	}
	
}
