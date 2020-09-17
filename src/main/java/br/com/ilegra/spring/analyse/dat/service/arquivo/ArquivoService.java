package br.com.ilegra.spring.analyse.dat.service.arquivo;

import br.com.ilegra.spring.analyse.dat.model.arquivo.Arquivo;
import br.com.ilegra.spring.analyse.dat.model.dtos.RelatorioArquivo;
import br.com.ilegra.spring.analyse.dat.repository.IArquivoRepository;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.RepositoryException;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArquivoService implements IArquivoService {

    private final IArquivoRepository arquivoRepository;

    @Override
    public List<Arquivo> recuperarArquivos() throws ServiceException {
        try {
            return this.arquivoRepository.findAllArquivos();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getLocalizedMessage());
        }
    }

    @Override
    public void salvarArquivoResumoRelatorio(Arquivo arquivo, RelatorioArquivo relatorioArquivo) throws ServiceException {
        try {
            this.arquivoRepository.saveArquivo(arquivo, relatorioArquivo);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getLocalizedMessage());
        }
    }
}
