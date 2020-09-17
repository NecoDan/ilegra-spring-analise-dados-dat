package br.com.ilegra.spring.analyse.dat.model.arquivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {

    private String caminho;
    private String extensao;
    private String nomeArquivo;

    private boolean isPathCaminhoInvalido() {
        return (Objects.isNull(caminho) || caminho.isEmpty());
    }

    public void gerarExtensaoArquivo(PropriedadeArquivo propriedadeArquivo) {
        if (isPathCaminhoInvalido())
            return;

        if (this.caminho.contains(propriedadeArquivo.getExtensaoPadraoArquivosLeituraDat()))
            this.extensao = propriedadeArquivo.getExtensaoPadraoArquivosLeituraDat();
    }

    public Arquivo geraExtensaoArquivo(PropriedadeArquivo propriedadeArquivo) {
        gerarExtensaoArquivo(propriedadeArquivo);
        return this;
    }

    public void gerarNomeArquivo(PropriedadeArquivo propriedadeArquivo) {
        if (isPathCaminhoInvalido())
            return;
        this.nomeArquivo = this.caminho.replace(propriedadeArquivo.getDiretorioPadraoArquivosEntrada().concat("/"), "");
        this.nomeArquivo = this.nomeArquivo.replace(propriedadeArquivo.getExtensaoPadraoArquivosLeituraDat(), "");
    }

    public Arquivo geraNomeArquivo(PropriedadeArquivo propriedadeArquivo) {
        gerarNomeArquivo(propriedadeArquivo);
        return this;
    }
}
