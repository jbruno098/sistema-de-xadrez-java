package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import board_game.Peca;
import board_game.Posicao;
import board_game.Tabuleiro;
import xadrez_pecas.Bispo;
import xadrez_pecas.Cavalo;
import xadrez_pecas.King;
import xadrez_pecas.Peao;
import xadrez_pecas.Rainha;
import xadrez_pecas.Torre;

public class PartidaDeXadrez {
	private Tabuleiro tabuleiro;
	private Cor jogadorAtual;
	private int vez;
	private boolean check;
	private boolean checkMate;
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		vez = 1;
		jogadorAtual = Cor.BRANCO;
		inicio();
	}
	
	public int getVez() {
		return vez;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	public boolean[][] movimentosPossiveis(PosicaoDeXadrez posInicial) {
		Posicao pos = posInicial.paraPosicao();
		validarPosicaoAtual(pos);
		return tabuleiro.peca(pos).movimentosPossiveis();
	}
	
	public PecaDeXadrez executarMovimentoXadrez(PosicaoDeXadrez posicaoAtual, PosicaoDeXadrez posicaoDesejada) {
		Posicao atual = posicaoAtual.paraPosicao();
		Posicao desejada = posicaoDesejada.paraPosicao();
		validarPosicaoAtual(atual);
		validarPosicaoDestino(atual, desejada);
		Peca pecaCapturada = facaOMovimento(atual, desejada);
		
		if (testeCheck(jogadorAtual)) {
			desfazerMovimento(atual, desejada, pecaCapturada);
			throw new XadrezException("Você não pode se colocar em check!");
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		if (testCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		return (PecaDeXadrez)pecaCapturada;			
	}
	
	private Peca facaOMovimento(Posicao origem, Posicao destino) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(origem);
		p.incrementarContador();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarDaPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		// Roque pequeno
		if (p instanceof King && destino.getColuna() == origem.getColuna() +2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() +3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() +1);
			PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removerPeca(origemTorre);
			tabuleiro.lugarDaPeca(torre, destinoTorre);
			torre.incrementarContador();
		}
			
			// Roque grande
			if (p instanceof King && destino.getColuna() == origem.getColuna() -2) {
				Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() -4);
				Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() -1);
				PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removerPeca(origemTorre);
				tabuleiro.lugarDaPeca(torre, destinoTorre);
				torre.incrementarContador();
			}

		return pecaCapturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturada) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(destino);
		p.decrementarContador();
		tabuleiro.lugarDaPeca(p, origem);
		
		if (capturada != null) {
			tabuleiro.lugarDaPeca(capturada, destino);
			pecasCapturadas.remove(capturada);
			pecasNoTabuleiro.add(capturada);
		}
		
		// Roque pequeno
		if (p instanceof King && destino.getColuna() == origem.getColuna() +2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() +3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() +1);
			PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removerPeca(destinoTorre);
			tabuleiro.lugarDaPeca(torre, origemTorre);
			torre.decrementarContador();
		}
			
		// Roque grande
		if (p instanceof King && destino.getColuna() == origem.getColuna() -2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() -4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() -1);
			PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.removerPeca(destinoTorre);
			tabuleiro.lugarDaPeca(torre, origemTorre);
			torre.decrementarContador();
		}

	}
	
	private void validarPosicaoAtual(Posicao pos) {
		if (!tabuleiro.temUmaPeca(pos)) {
			throw new XadrezException("Não existe uma peça na posição atual");
		}
		
		if (jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(pos)).getCor()) {
			throw new XadrezException("Não se pode mover a peça do adversário!");
		}
		
		if (!tabuleiro.peca(pos).existeAlgumMoviPos()) {
			throw new XadrezException("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	public void validarPosicaoDestino(Posicao atual, Posicao destino) {
		if (!tabuleiro.peca(atual).movimentoPossivel(destino)) {
			throw new XadrezException("A peça escolhida não pode se mover para posição de destino");
		}
	}
	
	private void nextTurn() {
		vez++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaDeXadrez king(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof King) {
				return (PecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Não tem um rei dessa cor no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao posicaoDoRei = king(cor).getPosicaoDeXadrez().paraPosicao();
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p: pecasDoOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaDeXadrez)p).getPosicaoDeXadrez().paraPosicao();
						Posicao destino = new Posicao(i,j);
						Peca capturada = facaOMovimento(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazerMovimento(origem, destino, capturada);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	
	private void lugarDaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoDeXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void inicio() {
		// Brancas
		lugarDaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('e', 1, new King(tabuleiro, Cor.BRANCO, this));
		lugarDaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		lugarDaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

		// Pretas
		lugarDaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('e', 8, new King(tabuleiro, Cor.PRETO, this));
		lugarDaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		lugarDaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));

	}
}
