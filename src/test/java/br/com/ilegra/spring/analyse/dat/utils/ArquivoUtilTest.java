package br.com.ilegra.spring.analyse.dat.utils;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoExtensaoArquivo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil.isNotContainsArquivosFileDiretorio;
import static br.com.ilegra.spring.analyse.dat.utils.ParametrosTesteUtil.getFileDiretorioPadraoSistema;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class ArquivoUtilTest {

    @Before
    public void setUp() {
    }

    @Test
    public void isDeveRetornarTruePorNaoConterArquivosNoDiretorioFile() {
        log.info("{} ", "#TEST: isDeveRetornarTruePorNaoConterArquivosNoDiretorioFile: ");

        // -- 01_Cenário
        File fileDiretorioAPartirRaiz = new File(getFileDiretorioPadraoSistema().getAbsolutePath() + "/" + RandomicoUtil.gerarValorRandomicoLong());
        File novoDiretorioFileVazio = ArquivoUtil.criarPathDiretorioInexistente(fileDiretorioAPartirRaiz);

        System.out.println("Diretorio File: ".concat(novoDiretorioFileVazio.getAbsolutePath()));

        // -- 02_Ação && 03_Verificacao-Validacao
        assertTrue(isNotContainsArquivosFileDiretorio(novoDiretorioFileVazio));
        log.info("{} ", "Qtde Arquivos Diretorio: ".concat(String.valueOf(Objects.requireNonNull(novoDiretorioFileVazio.listFiles()).length)));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void isDeveRetornarFalsePorNaoConterArquivosNoDiretorioFile() throws IOException {
        log.info("{} ", "#TEST: isDeveRetornarTruePorNaoConterArquivosNoDiretorioFile: ");

        // -- 01_Cenário
        String nomeArquivo = String.valueOf(RandomicoUtil.gerarValorRandomicoLong());
        File fileDiretorioAPartirRaiz = new File(getFileDiretorioPadraoSistema().getAbsolutePath() + "/" + nomeArquivo);

        File novoDiretorioTemporario = ArquivoUtil.criarPathDiretorioInexistente(fileDiretorioAPartirRaiz);
        log.info("{} ", "Diretorio File: ".concat(novoDiretorioTemporario.getAbsolutePath()));

        // -- 02_Ação
        File nomeArquivoNovo = new File(novoDiretorioTemporario.getAbsolutePath() + "/" + nomeArquivo.concat(".done").concat(TipoExtensaoArquivo.DAT.getExtensao()));
        String conteudoArquivo = ParametrosTesteUtil.gerarConteudoArquivoRandomicoValores(false, true);
        ArquivoUtil.gravarArquivo(conteudoArquivo, nomeArquivoNovo);

        // -- 03_Verificacao-Validacao
        assertFalse(isNotContainsArquivosFileDiretorio(novoDiretorioTemporario));
        log.info("{} ", "Qtde Arquivos Diretorio: ".concat(String.valueOf(Objects.requireNonNull(novoDiretorioTemporario.listFiles()).length)));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void getRegistrosFromSeparadorLinhaArquivo() {
    }

    @Test
    public void getRegistrosFromLinhaArquivoPorSeparador() {
    }

    @Test
    public void isParamsPermiteEscritaMapParameters() {
    }

    @Test
    public void getStringStringMap() {
    }

    @Test
    public void getLinhasRegistrosArquivos() {
    }

}
