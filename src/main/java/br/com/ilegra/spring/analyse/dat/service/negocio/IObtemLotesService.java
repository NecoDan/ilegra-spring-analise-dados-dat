package br.com.ilegra.spring.analyse.dat.service.negocio;

import br.com.ilegra.spring.analyse.dat.model.cadastro.Cliente;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.dtos.ResumoRelatorio;
import br.com.ilegra.spring.analyse.dat.model.vendas.Venda;

import java.util.List;

public interface IObtemLotesService {

    List<Vendedor> getVendedoresResumoRelatorio(List<ResumoRelatorio> resumoRelatorioList);

    List<Cliente> getClientesResumoRelatorio(List<ResumoRelatorio> resumoRelatorioList);

    List<Venda> getVendasResumoRelatorio(List<ResumoRelatorio> resumoRelatorioList);
}
