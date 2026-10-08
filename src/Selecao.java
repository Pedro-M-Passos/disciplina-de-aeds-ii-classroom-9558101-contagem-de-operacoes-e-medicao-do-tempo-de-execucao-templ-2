import java.util.Comparator;

public class Selecao<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dadosOrdenados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Selecao() {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(T::compareTo);
	}
	
	public Selecao(Comparator<T> comparador) {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(comparador);
	}
	
	@Override
	public void setComparador(Comparator<T> comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public T[] ordenar(T[] dados) {

		dadosOrdenados = dados;
		
		comparacoes = 0;
		movimentacoes = 0;
		iniciar();
		
		for (int i = 0; i < dadosOrdenados.length - 1; i++) {
			int menor = i;
			for (int j = i + 1; j < dadosOrdenados.length; j++) {
				comparacoes++;
				if (comparador.compare(dadosOrdenados[j], dadosOrdenados[menor]) < 0)
					menor = j;
			}
			swap(i, menor);
		}
		
		terminar();
		
		return dadosOrdenados;
	}
	
	private void swap(int i, int j) {
	      
		movimentacoes++;
		
		T temp = dadosOrdenados[i];
	    dadosOrdenados[i] = dadosOrdenados[j];
	    dadosOrdenados[j] = temp;
	}
	
	@Override
	public long getComparacoes() {
		return comparacoes;
	}
	
	@Override
	public long getMovimentacoes() {
		return movimentacoes;
	}
	
	private void iniciar() {
		inicio = System.nanoTime();
	}
	
	private void terminar() {
		termino = System.nanoTime();
	}
	
	@Override
	public double getTempoOrdenacao() {
		
		double tempoTotal;
		
	    tempoTotal = (termino - inicio) / 1_000_000.0;
	    return tempoTotal;
	}
}
