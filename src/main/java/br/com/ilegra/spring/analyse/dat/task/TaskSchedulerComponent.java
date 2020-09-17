package br.com.ilegra.spring.analyse.dat.task;

import br.com.ilegra.spring.analyse.dat.service.IProcessaDadosArquivoService;
import br.com.ilegra.spring.analyse.dat.utils.DateUtil;
import br.com.ilegra.spring.analyse.dat.utils.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskSchedulerComponent {

    private static final String TEMPO_CADA_UM_MINUTO = "0 0/1 * * * *";
    private static final int TEMPO_CADA_TRINTA_SEGUNDOS = 30000;

    private final IProcessaDadosArquivoService processaDadosArquivoService;

    public void executarStart() {
        log.info("Inicializada a execução projeto ilegra-spring-analise-dados-dat em: " + DateUtil.toStringLocalDateFormatada(LocalDateTime.now()));
        log.info("Executou aplicação c/ scheduled c/ cron a 30 segundos em - " + DateUtil.toStringLocalDateFormatada(LocalDateTime.now()));

        try {
            this.processaDadosArquivoService.efetuarProcessamento();
            log.info("Finalizado o processamento - " + DateUtil.toStringLocalDateFormatada(LocalDateTime.now()));
        } catch (ServiceException e) {
            log.error("Houve fallha na aplicação - " + e.getLocalizedMessage());
        }
    }

    // @Scheduled(cron = TEMPO_CADA_UM_MINUTO)
    @Scheduled(fixedRate = TEMPO_CADA_TRINTA_SEGUNDOS)
    public void executar() {
        executarStart();
    }
}
