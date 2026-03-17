package board_game;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
		
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas <1) {
			throw new TabuleiroException("Erro ao criar tabuleiro, é necessario pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}


	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new TabuleiroException("Erro: essa posição não existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao pos) {
		if (!posicaoExiste(pos)) {
			throw new TabuleiroException("Erro: essa posição não existe no tabuleiro");
		}
		return pecas[pos.getLinha()][pos.getColuna()];
	}
	
	public void lugarDaPeca(Peca peca, Posicao posicao) {
		if (temUmaPeca(posicao)) {
			throw new TabuleiroException("Erro: ja existe uma peça nessa posição!" + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >=0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temUmaPeca(Posicao pos) {
	if (!posicaoExiste(pos)) {
		throw new TabuleiroException("Erro: essa posição não existe no tabuleiro");
	}
		return peca(pos) != null;
	}
}
