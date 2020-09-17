package br.com.ilegra.spring.analyse.dat.service.negocio.cadastro;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.service.validation.IValidadorProcessamentoService;
import br.com.ilegra.spring.analyse.dat.utils.FormatterUtil;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil.getRegistrosFromSeparadorLinhaArquivo;
import static br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil.getStringStringMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendedorService implements IVendedorService {

    private static final String POSICAO_CPF = "1";
    private static final String POSICAO_NAME = "2";
    private static final String POSICAO_SALARY = "3";

    private final IValidadorProcessamentoService validadorProcessamentoService;

    @Override
    public Optional<ResumoRelatorio> getResumoRelatorioVendedor(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException {
        validadorProcessamentoService.validaParametrosLeituraRegistros(tipoLayoutArquivo, linhaArquivo);
        List<String> stringListRegistros = getRegistrosFromSeparadorLinhaArquivo(linhaArquivo);
        return Optional.of(mountResumoRelatorio(stringListRegistros));
    }

    private ResumoRelatorio mountResumoRelatorio(List<String> stringListRegistros) {
        return ResumoRelatorio.builder().vendedor(mountVendedor(stringListRegistros)).build();
    }

    private Vendedor mountVendedor(List<String> stringListRegistros) {
        Map<String, String> mapParametres = getMapParametersFromListaRegistros(stringListRegistros);
        return (mapParametres.isEmpty()) ? null : getVendedorFromMapParameters(mapParametres);
    }

    private Vendedor getVendedorFromMapParameters(Map<String, String> mapParameters) {
        return Vendedor.builder()
                .cpf(mapParameters.get(POSICAO_CPF))
                .name(mapParameters.get(POSICAO_NAME))
                .salary(FormatterUtil.onlyBigDecimal(mapParameters.get(POSICAO_SALARY)))
                .build();
    }

    private Map<String, String> getMapParametersFromListaRegistros(List<String> stringListRegistros) {
        return getStringStringMap(stringListRegistros, POSICAO_CPF, POSICAO_NAME, POSICAO_SALARY);
    }
}
