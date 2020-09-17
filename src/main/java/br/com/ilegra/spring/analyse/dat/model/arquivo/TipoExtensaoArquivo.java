package br.com.ilegra.spring.analyse.dat.model.arquivo;

import java.util.*;
import java.util.stream.Collectors;

public enum TipoExtensaoArquivo {

    TXT(2, "TXT", "TIPO EXTENSAO ARQUIVO TXT"),

    DAT(3, "DAT", "TIPO EXTENSAO ARQUIVO DAT");

    private static final Map<Integer, TipoExtensaoArquivo> lookup;

    static {
        lookup = new HashMap<>();
        EnumSet<TipoExtensaoArquivo> enumSet = EnumSet.allOf(TipoExtensaoArquivo.class);

        for (TipoExtensaoArquivo type : enumSet)
            lookup.put(type.codigo, type);
    }

    private int codigo;
    private String codigoLiteral;
    private String descricao;

    TipoExtensaoArquivo(int codigo, String codigoLiteral, String descricao) {
        inicialize(codigo, codigoLiteral, descricao);
    }

    private void inicialize(int codigo, String codigoLiteral, String descricao) {
        this.codigo = codigo;
        this.codigoLiteral = codigoLiteral;
        this.descricao = descricao;
    }

    public static TipoExtensaoArquivo fromCodigo(int codigo) {
        if (lookup.containsKey(codigo))
            return lookup.get(codigo);
        throw new IllegalArgumentException(String.format("Código do Tipo Extensão Arquivo inválido: %d", codigo));
    }

    public static List<String> getListaExtensoesArquivo() {
        List<TipoExtensaoArquivo> tipoExtensaoArquivoList = Arrays.stream(TipoExtensaoArquivo.values()).collect(Collectors.toList());
        return tipoExtensaoArquivoList.stream().filter(Objects::nonNull).map(TipoExtensaoArquivo::getExtensao).collect(Collectors.toList());
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getCodigoLiteral() {
        return this.codigoLiteral.toLowerCase();
    }

    public String getExtensao() {
        return ".".concat(getCodigoLiteral());
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean isTXT() {
        return Objects.equals(this, TXT);
    }

    public boolean isDAT() {
        return Objects.equals(this, DAT);
    }
}
