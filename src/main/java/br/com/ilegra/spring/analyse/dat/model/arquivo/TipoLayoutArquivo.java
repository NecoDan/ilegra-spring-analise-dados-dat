package br.com.ilegra.spring.analyse.dat.model.arquivo;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum TipoLayoutArquivo {

    NENHUM(0, "000", "NENHUM"),

    LAYOUT_VENDEDOR(1, "001", "LAYOUT ARQUIVO VENDEDOR"),

    LAYOUT_CLIENTE(2, "002", "LAYOUT ARQUIVO CLIENTE"),

    LAYOUT_VENDAS(3, "003", "LAYOUT ARQUIVO VENDAS");

    private static final Map<Integer, TipoLayoutArquivo> lookup;

    static {
        lookup = new HashMap<>();
        EnumSet<TipoLayoutArquivo> enumSet = EnumSet.allOf(TipoLayoutArquivo.class);

        for (TipoLayoutArquivo type : enumSet)
            lookup.put(type.codigo, type);
    }

    private int codigo;
    private String codigoLiteral;
    private String descricao;

    TipoLayoutArquivo(int codigo, String codigoLiteral, String descricao) {
        inicialize(codigo, codigoLiteral, descricao);
    }

    private void inicialize(int codigo, String codigoLiteral, String descricao) {
        this.codigo = codigo;
        this.codigoLiteral = codigoLiteral;
        this.descricao = descricao;
    }

    public static TipoLayoutArquivo fromCodigo(int codigo) {
        if (lookup.containsKey(codigo))
            return lookup.get(codigo);
        throw new IllegalArgumentException(String.format("Código do Layout Arquivo inválido: %d", codigo));
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getCodigoLiteral() {
        return this.codigoLiteral;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean isLayoutVendedor() {
        return Objects.equals(this, LAYOUT_VENDEDOR);
    }

    public boolean isLayoutCliente() {
        return Objects.equals(this, LAYOUT_CLIENTE);
    }

    public boolean isLayoutVendas() {
        return Objects.equals(this, LAYOUT_VENDAS);
    }
}
