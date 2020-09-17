package br.com.ilegra.spring.analyse.dat.service.negocio.vendas;

import br.com.ilegra.spring.analyse.dat.model.arquivo.TipoLayoutArquivo;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.model.vendas.ItemVenda;
import br.com.ilegra.spring.analyse.dat.model.vendas.Venda;
import br.com.ilegra.spring.analyse.dat.service.validation.IValidadorProcessamentoService;
import br.com.ilegra.spring.analyse.dat.utils.FormatterUtil;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.ilegra.spring.analyse.dat.utils.ArquivoUtil.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendaService implements IVendaService {

    private final IValidadorProcessamentoService validadorProcessamentoService;

    private static final String POSICAO_VENDA_ID = "1";
    private static final String POSICAO_VENDA_ITEM = "2";
    private static final String POSICAO_VENDA_NOME_VENDEDOR = "3";

    private static final String POSICAO_ITEM_ID = "0";
    private static final String POSICAO_ITEM_QTDE = "1";
    private static final String POSICAO_ITEM_PRICE = "2";

    @Override
    public Optional<ResumoRelatorio> getResumoRelatorioVendas(TipoLayoutArquivo tipoLayoutArquivo, String linhaArquivo) throws ServiceException {
        validadorProcessamentoService.validaParametrosLeituraRegistros(tipoLayoutArquivo, linhaArquivo);
        List<String> stringListRegistros = getRegistrosFromSeparadorLinhaArquivo(linhaArquivo);
        return Optional.of(mountResumoRelatorio(stringListRegistros));
    }

    private ResumoRelatorio mountResumoRelatorio(List<String> stringListRegistros) {
        return ResumoRelatorio.builder().venda(mountVenda(stringListRegistros)).build();
    }

    private Venda mountVenda(List<String> stringListRegistros) {
        Map<String, String> mapParameters = getMapParametersVendaFromListaRegistros(stringListRegistros);
        return (mapParameters.isEmpty()) ? null : getVendaFromMapParameters(mapParameters);
    }

    private Venda getVendaFromMapParameters(Map<String, String> mapParameters) {
        return Venda.builder()
                .id(FormatterUtil.onlyLongNumbers(mapParameters.get(POSICAO_VENDA_ID)))
                .vendedor(Vendedor.builder()
                        .name(mapParameters.get(POSICAO_VENDA_NOME_VENDEDOR))
                        .build())
                .build()
                .addAllItensVenda(getItensVendaFromLinhaRegistroItens(mapParameters.get(POSICAO_VENDA_ITEM)));
    }

    private Map<String, String> getMapParametersVendaFromListaRegistros(List<String> stringListRegistros) {
        return getStringStringMap(stringListRegistros, POSICAO_VENDA_ID, POSICAO_VENDA_ITEM, POSICAO_VENDA_NOME_VENDEDOR);
    }

    private boolean isValoresRegistrosInValidos(String valorRegistro) {
        return (Objects.isNull(valorRegistro) || valorRegistro.isEmpty());
    }

    private List<ItemVenda> getItensVendaFromLinhaRegistroItens(String strValoresRegistro) {
        return (isValoresRegistrosInValidos(strValoresRegistro)) ? Collections.emptyList() : obterItensVendaFromLinhaRegistroPorCadaItem(strValoresRegistro);
    }

    private List<ItemVenda> obterItensVendaFromLinhaRegistroPorCadaItem(String strValoresRegistro) {
        String strValoresRegistroResult = strValoresRegistro.replaceAll("\\[|\\]", "");
        List<String> strListRegistros = getRegistrosFromLinhaArquivoPorSeparador(",", strValoresRegistroResult);
        return (strListRegistros.isEmpty()) ? Collections.emptyList() : mountListaItensVendasFromLinhaRegistroPorCadaItem(strListRegistros);
    }

    private List<ItemVenda> mountListaItensVendasFromLinhaRegistroPorCadaItem(List<String> strListRegistros) {
        return strListRegistros.stream().filter(Objects::nonNull).map(this::getItemVenda).collect(Collectors.toList());
    }

    private ItemVenda getItemVenda(String strRegistro) {
        List<String> linhasRegistro = getRegistrosFromLinhaArquivoPorSeparador("-", strRegistro);
        Optional<ItemVenda> optionalItemVenda = mountItemVendaFrom(getMapParametersItemVendaFromListaRegistros(linhasRegistro));
        return optionalItemVenda.orElse(null);
    }

    private Optional<ItemVenda> mountItemVendaFrom(Map<String, String> mapParametersItemVenda) {
        return (mapParametersItemVenda.isEmpty()) ? Optional.empty() : getItemVendaFromMap(mapParametersItemVenda);
    }

    private Optional<ItemVenda> getItemVendaFromMap(Map<String, String> mapParametersItemVenda) {
        return Optional.of(ItemVenda.builder()
                .id(FormatterUtil.onlyLongNumbers(mapParametersItemVenda.get(POSICAO_ITEM_ID)))
                .quantity(FormatterUtil.onlyBigDecimal(mapParametersItemVenda.get(POSICAO_ITEM_QTDE)))
                .price(FormatterUtil.onlyBigDecimal(mapParametersItemVenda.get(POSICAO_ITEM_PRICE)))
                .build());
    }

    private Map<String, String> getMapParametersItemVendaFromListaRegistros(List<String> linhasRegistro) {
        return getStringStringMap(linhasRegistro, POSICAO_ITEM_ID, POSICAO_ITEM_QTDE, POSICAO_ITEM_PRICE);
    }
}
