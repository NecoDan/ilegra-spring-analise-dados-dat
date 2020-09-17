package br.com.ilegra.spring.analyse.dat.utils;

import br.com.ilegra.spring.analyse.dat.model.cadastro.Cliente;
import br.com.ilegra.spring.analyse.dat.model.cadastro.Vendedor;
import br.com.ilegra.spring.analyse.dat.model.vendas.ItemVenda;
import br.com.ilegra.spring.analyse.dat.model.vendas.Venda;
import br.com.ilegra.spring.analyse.dat.utils.domain.AbstractEntity;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.ilegra.spring.analyse.dat.utils.RandomicoUtil.gerarCnpjRandomico;
import static br.com.ilegra.spring.analyse.dat.utils.RandomicoUtil.gerarCpfRandomico;

public final class ParametrosTesteUtil {

    public ParametrosTesteUtil() {

    }

    public static String getDiretorioTempSistema() {
        String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        System.out.println("Diretorio temporario: " + tempDir);
        return tempDir;
    }

    public static File getFileDiretorioPadraoSistema() {
        return new File(getDiretorioTempSistema());
    }

    public static Vendedor mountVendedorFrom(String nome, BigDecimal salario) {
        return Vendedor
                .builder()
                .cpf(gerarCpfRandomico(false))
                .name(nome)
                .salary(salario)
                .build();
    }

    public static Cliente mountClienteFrom(String nome, String areaNegocio) {
        return Cliente
                .builder()
                .cnpj(gerarCnpjRandomico(false))
                .name(nome)
                .businessArea(areaNegocio)
                .build();
    }

    public static Venda mountVendaFrom(Vendedor vendedor, List<ItemVenda> itemVendas, BigDecimal valorVendaTotal) {
        return Venda
                .builder()
                .vendedor(vendedor)
                .itemVendas(itemVendas)
                .valorTotal(valorVendaTotal)
                .build();
    }

    public static ItemVenda mountItemVendaFrom(Venda venda, BigDecimal quantidade, BigDecimal valorPreco) {
        return ItemVenda
                .builder()
                .venda(venda)
                .quantity(quantidade)
                .price(valorPreco)
                .build();
    }

    public static boolean isPathCaminhoASalvarInvalido(String pahtCaminhoASalvar) {
        return (Objects.isNull(pahtCaminhoASalvar) || pahtCaminhoASalvar.isEmpty());
    }

    public static List<Cliente> getListaClientes() {
        return Arrays.asList(
                mountClienteFrom("Paulo", "Rural"),
                mountClienteFrom("Jose", "Comercio"),
                mountClienteFrom("Suzana", "Material Construcao"),
                mountClienteFrom("Leila", "Padaria")
        );
    }

    public static List<Venda> getListaVendas(Vendedor vendedor1, Vendedor vendedor2, Vendedor vendedor3) {
        return Arrays.asList(
                mountVendaFrom(vendedor1, Arrays.asList(mountItemVendaFrom(
                        null,
                        RandomicoUtil.gerarValorRandomicoDecimalAte(5000D),
                        RandomicoUtil.gerarValorRandomicoDecimalAte(5000D)),
                        mountItemVendaFrom(
                                null,
                                RandomicoUtil.gerarValorRandomicoDecimalAte(5000D),
                                RandomicoUtil.gerarValorRandomicoDecimalAte(5000D))
                ), BigDecimal.ZERO),
                mountVendaFrom(vendedor2, Collections.singletonList(mountItemVendaFrom(
                        null,
                        RandomicoUtil.gerarValorRandomicoDecimalAte(5000D),
                        RandomicoUtil.gerarValorRandomicoDecimalAte(5000D)
                        )
                ), BigDecimal.ZERO),
                mountVendaFrom(vendedor3, Collections.singletonList(mountItemVendaFrom(
                        null,
                        RandomicoUtil.gerarValorRandomicoDecimalAte(5000D),
                        RandomicoUtil.gerarValorRandomicoDecimalAte(5000D)
                        )
                ), BigDecimal.ZERO)
        );
    }

    public static String gerarConteudoArquivoRandomicoValores(boolean geraItensVenda, boolean geraConteudoEmToString) {
        Vendedor vendedor1 = mountVendedorFrom("Pedro", RandomicoUtil.gerarValorRandomicoDecimalAte(10000D));
        Vendedor vendedor2 = mountVendedorFrom("Pedro", RandomicoUtil.gerarValorRandomicoDecimalAte(10000D));
        Vendedor vendedor3 = mountVendedorFrom("Pedro", RandomicoUtil.gerarValorRandomicoDecimalAte(10000D));

        List<Vendedor> vendedorList = Arrays.asList(vendedor1, vendedor2, vendedor3);
        List<Cliente> clienteList = getListaClientes();
        List<Venda> vendaList = (geraItensVenda) ? getListaVendas(vendedor1, vendedor2, vendedor3) : Collections.emptyList();

        StringBuilder stringBuilder = new StringBuilder();

        if (geraConteudoEmToString) {
            stringBuilder.append(vendedorList.stream().map(AbstractEntity::toString).collect(Collectors.joining()));
            stringBuilder.append(clienteList.stream().map(AbstractEntity::toString).collect(Collectors.joining()));
            stringBuilder.append(vendaList.stream().map(AbstractEntity::toString).collect(Collectors.joining()));
        }

        return stringBuilder.toString();
    }
}
