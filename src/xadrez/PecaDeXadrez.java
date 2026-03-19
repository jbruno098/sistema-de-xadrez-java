package xadrez;

import board_game.Peca;
import board_game.Posicao;
import board_game.Tabuleiro;

public abstract class PecaDeXadrez extends Peca{
	private Cor cor;
	private int contadorDeMovimentos;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public int getContadorDeMovimentos() {
		return contadorDeMovimentos;
	}
	
	public void incrementarContador() {
		contadorDeMovimentos ++;
	}
	
	public void decrementarContador() {
		contadorDeMovimentos --;
	}
	
	public PosicaoDeXadrez getPosicaoDeXadrez() {
		return PosicaoDeXadrez.daPosicao(posicao);
	}
	
	protected boolean existeOponente(Posicao pos) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(pos);
		return p != null && p.getCor() != cor;
	}
	
}
