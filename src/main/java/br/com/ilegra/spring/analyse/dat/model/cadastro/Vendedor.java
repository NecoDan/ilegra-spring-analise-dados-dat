package br.com.ilegra.spring.analyse.dat.model.cadastro;

import br.com.ilegra.spring.analyse.dat.utils.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Vendedor extends AbstractEntity {
    private String cpf;
    private String name;
    private BigDecimal salary;
}
