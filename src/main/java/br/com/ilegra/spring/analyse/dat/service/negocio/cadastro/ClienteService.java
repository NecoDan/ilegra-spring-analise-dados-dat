package br.com.ilegra.spring.analyse.dat.service.negocio.cadastro;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Cliente;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.service.validation.IValidadorProcessamentoService;
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
public class ClienteService implements IClienteService {

    private static final String POSICAO_CNPJ = "1";
    private static final String POSICAO_NAME = "2";
    private static final String POSICAO_BUSINESS_AREA = "3";

    private final IValidadorProcessamentoService validadorProcessamentoService;

    @Override
    public Optional<ResumoRelatorio> getResumoRelatorioCliente(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException {
        validadorProcessamentoService.validaParametrosLeituraRegistros(tipoLayoutArquivo, linhaArquivo);
        List<String> stringListRegistros = getRegistrosFromSeparadorLinhaArquivo(linhaArquivo);
        return Optional.ofNullable(mountResumoRelatorio(stringListRegistros));
    }

    private ResumoRelatorio mountResumoRelatorio(List<String> stringListRegistros) {
        return ResumoRelatorio.builder().cliente(mountCliente(stringListRegistros)).build();
    }

    private Cliente mountCliente(List<String> stringListRegistros) {
        Map<String, String> mapParametres = getMapParametersFromListaRegistros(stringListRegistros);
        return (mapParametres.isEmpty()) ? null : getClienteFromMapParameters(mapParametres);
    }

    private Cliente getClienteFromMapParameters(Map<String, String> mapParameters) {
        return Cliente.builder()
                .cnpj(mapParameters.get(POSICAO_CNPJ))
                .name(mapParameters.get(POSICAO_NAME))
                .businessArea(mapParameters.get(POSICAO_BUSINESS_AREA))
                .build();
    }

    private Map<String, String> getMapParametersFromListaRegistros(List<String> stringListRegistros) {
        return getStringStringMap(stringListRegistros, POSICAO_CNPJ, POSICAO_NAME, POSICAO_BUSINESS_AREA);
    }
}
