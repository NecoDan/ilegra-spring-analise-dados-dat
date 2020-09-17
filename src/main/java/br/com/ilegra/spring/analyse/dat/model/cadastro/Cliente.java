package br.com.ilegra.spring.analyse.dat.model.cadastro;

import br.com.ilegra.spring.analyse.dat.utils.domain.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Cliente extends AbstractEntity {
    private String cnpj;
    private String name;
    private String businessArea;
}
