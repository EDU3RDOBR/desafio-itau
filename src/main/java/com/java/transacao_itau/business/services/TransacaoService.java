package com.java.transacao_itau.business.services;

import com.java.transacao_itau.controller.dtos.TransacaoRequestDTO;
import com.java.transacao_itau.infrastructure.excecptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {

        log.info("Inicando processamento de gravar transacoes" + dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maior que a atual");
            throw new UnprocessableEntity("Data e hora maior que a atual");
        }
        if(dto.valor() < 0){
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }
        listaTransacoes.add(dto);
        log.info("Transacao gravada com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){
        log.info("Iniciando busca de transçôes por tempo de intervalo " + intervaloBusca + " segundos");

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return listaTransacoes.stream().filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();

    }
}
