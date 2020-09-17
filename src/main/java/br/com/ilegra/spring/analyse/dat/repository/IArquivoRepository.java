package br.com.ilegra.spring.analyse.dat.repository;

import br.com.ilegra.spring.analyse.dat.model.arquivo.Arquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.RelatorioArquivo;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.RepositoryException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArquivoRepository {

    List<Arquivo> findAllArquivos() throws RepositoryException;

    void saveArquivo(Arquivo arquivo, RelatorioArquivo relatorioArquivo) throws RepositoryException;
}
