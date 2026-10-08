import java.util.Comparator;

public class Insercao<T extends Comparable<T>> implements IOrdenator<T> {

	private T[] dadosOrdenados;
	private Comparator<T> comparador;
	private long comparacoes;
	private long movimentacoes;
	private long inicio;
	private long termino;
	
	public Insercao() {
		
		comparacoes = 0;
		movimentacoes = 0;
		setComparador(T::compareTo);
	}
	
	public Insercao(Comparator<T> comparador) {
		
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
		
		for (int i = 1; i < dadosOrdenados.length; i++) {
			T temp = dadosOrdenados[i];
			int j = i - 1;
			while (j >= 0) {
				comparacoes++;
				if (comparador.compare(temp, dadosOrdenados[j]) < 0) {
					movimentacoes++;
					dadosOrdenados[j + 1] = dadosOrdenados[j];
					j--;
				} else {
					break;
				}
			}
			movimentacoes++;
			dadosOrdenados[j + 1] = temp;
		}
		
		terminar();
		
		return dadosOrdenados;
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
