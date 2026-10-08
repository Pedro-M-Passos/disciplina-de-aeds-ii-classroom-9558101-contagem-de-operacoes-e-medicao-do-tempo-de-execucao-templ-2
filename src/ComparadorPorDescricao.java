import java.util.Comparator;

public class ComparadorPorDescricao implements Comparator<Produto> {

    @Override
    public int compare(Produto p1, Produto p2) {
        return p1.descricao.compareToIgnoreCase(p2.descricao);
    }
}
