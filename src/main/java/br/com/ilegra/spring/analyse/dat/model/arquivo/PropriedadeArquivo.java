package br.com.ilegra.spring.analyse.dat.model.arquivo;

import lombok.Builder;

import java.io.File;

@Builder
public class PropriedadeArquivo {

    private static final String DIRETORIO_PADRAO_ARQUIVOS_ENTRADA = System.getProperty("user.home") + "/data/in";
    private static final String DIRETORIO_PADRAO_ARQUIVOS_SAIDA = System.getProperty("user.home") + "/data/out";

    private static final String NOME_PADRAO_ARQUIVO_SAIDA = "{flat_file_name}.done.dat";

    public File getFileDiretorioPadraoEntrada() {
        return new File(getDiretorioPadraoArquivosEntrada());
    }

    public String getDiretorioPadraoArquivosEntrada() {
        return DIRETORIO_PADRAO_ARQUIVOS_ENTRADA;
    }

    public String getDiretorioPadraoArquivosSaida() {
        return DIRETORIO_PADRAO_ARQUIVOS_SAIDA;
    }

    public String getExtensaoPadraoArquivosLeituraDat() {
        return TipoExtensaoArquivo.DAT.getExtensao();
    }

    public String getExtensaoPadraoArquivosLeituraTxt() {
        return TipoExtensaoArquivo.TXT.getExtensao();
    }

}
