package br.com.ilegra.spring.analyse.dat.service.negocio;

import br.com.ilegra.spring.analyse.dat.model.cadastro.Cliente;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.model.vendas.Venda;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ObtemLotesService implements IObtemLotesService {

    @Override
    public List<Vendedor> getVendedoresResumoRelatorio(List<ResumoRelatorio> resumoRelatorioList) {
        return resumoRelatorioList.stream()
                .filter(Objects::nonNull)
                .filter(resumoRelatorio -> Objects.nonNull(resumoRelatorio.getVendedor()))
                .map(ResumoRelatorio::getVendedor)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> getClientesResumoRelatorio(List<ResumoRelatorio> resumoRelatorioList) {
        return resumoRelatorioList.stream()
                .filter(Objects::nonNull)
                .filter(resumoRelatorio -> Objects.nonNull(resumoRelatorio.getCliente()))
                .map(ResumoRelatorio::getCliente)
                .collect(Collectors.toList());
    }

    @Override
    public List<Venda> getVendasResumoRelatorio(List<ResumoRelatorio> resumoRelatorioList) {
        return resumoRelatorioList.stream()
                .filter(Objects::nonNull)
                .filter(resumoRelatorio -> Objects.nonNull(resumoRelatorio.getVenda()))
                .map(ResumoRelatorio::getVenda)
                .collect(Collectors.toList());
    }
}
