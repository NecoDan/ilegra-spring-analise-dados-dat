package br.com.ilegra.spring.analyse.dat.service.arquivo;

import br.com.ilegra.spring.analyse.dat.model.arquivo.Arquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.RelatorioArquivo;
import br.com.ilegra.spring.analyse.dat.repository.ArquivoRepository;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
public class ArquivoServiceTest {

    @Mock
    private ArquivoRepository arquivoRepository;
    @Spy
    @InjectMocks
    private ArquivoService arquivoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(arquivoRepository);
        reset(arquivoService);
    }

    @Test
    public void deveRetornarUmaListaArquivosRecuperadasAPartirDiretorioEntradaPrincipal() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarUmaListaArquivosRecuperadasAPartirDiretorioEntradaPrincipal: ");

        // -- 01_Cenário
        resetarMocks();
        List<Arquivo> arquivos = Arrays.asList(mock(Arquivo.class), mock(Arquivo.class), mock(Arquivo.class), mock(Arquivo.class));

        // -- 02_Ação
        doCallRealMethod().when(arquivoService).recuperarArquivos();
        when(arquivoService.recuperarArquivos()).thenReturn(arquivos);

        // -- 03_Verificação_Validação
        assertEquals(arquivos, arquivoService.recuperarArquivos());
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    public void deveSalvarArquivoResumoRelatorioPorArquivoEResumoRelatorio() throws ServiceException {
        log.info("{} ", "#TEST: deveSalvarArquivoResumoRelatorioPorArquivoEResumoRelatorio: ");

        // -- 01_Cenário
        resetarMocks();
        Arquivo arquivo = Arquivo.builder().build();
        RelatorioArquivo relatorioArquivo = RelatorioArquivo.builder().build();

        // -- 02_Ação
        doCallRealMethod().when(arquivoService).salvarArquivoResumoRelatorio(isA(Arquivo.class), isA(RelatorioArquivo.class));
        arquivoService.salvarArquivoResumoRelatorio(arquivo, relatorioArquivo);

        // -- 03_Verificação_Validação
        verify(arquivoService).salvarArquivoResumoRelatorio(any(Arquivo.class), any(RelatorioArquivo.class));
        log.info("{} ", "-------------------------------------------------------------");
    }
}
