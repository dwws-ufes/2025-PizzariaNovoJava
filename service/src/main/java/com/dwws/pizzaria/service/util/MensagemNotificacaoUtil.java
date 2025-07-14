package com.dwws.pizzaria.service.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MensagemNotificacaoUtil {

    // Mensagens de validação
    public static final String NULL_PEDIDO = "Pedido Não Pode Ser Nulo";
    public static final String NULL_STATUS = "Status Não Pode Ser Nulo";
    public static final String NULL_DATA_HORA = "Data e Hora Não Podem Ser Nulas";
    public static final String NULL_TIPO_NOTIFICACAO = "Tipo de Notificação Não Pode Ser Nulo";

    // Mensagens de erro
    public static final String ENTITY_NOT_FOUND_COZINHA = "Notificação da Cozinha Não Encontrada";
    public static final String ENTITY_NOT_FOUND_BAR = "Notificação do Bar Não Encontrada";
    public static final String PEDIDO_NOT_FOUND = "Pedido Não Encontrado";
    public static final String NOTIFICACAO_JA_PROCESSADA = "Notificação Já Foi Processada";
    public static final String NOTIFICACAO_INATIVA = "Notificação Está Inativa";
    public static final String STATUS_INVALIDO = "Status Inválido para Esta Operação";

    // Mensagens de sucesso
    public static final String NOTIFICACAO_CRIADA_SUCESSO = "Notificação Criada com Sucesso";
    public static final String NOTIFICACAO_ATUALIZADA_SUCESSO = "Notificação Atualizada com Sucesso";
    public static final String NOTIFICACAO_PROCESSADA_SUCESSO = "Notificação Marcada como Processada";
    public static final String NOTIFICACAO_PRONTA_SUCESSO = "Notificação Marcada como Pronta";
    public static final String NOTIFICACAO_DESATIVADA_SUCESSO = "Notificação Desativada com Sucesso";

    // Mensagens informativas
    public static final String NENHUMA_NOTIFICACAO_PENDENTE = "Nenhuma Notificação Pendente Encontrada";
    public static final String NOTIFICACOES_ATUALIZADAS = "Notificações Atualizadas Automaticamente";
    public static final String NOTIFICACOES_DESATIVADAS = "Notificações Desativadas para Pedido Cancelado";

    // Mensagens de regras de negócio
    public static final String PRODUTO_SEM_NOTIFICACAO = "Produto Não Requer Notificação Específica";
    public static final String PEDIDO_SEM_PRODUTOS_COZINHA = "Pedido Não Possui Produtos para Cozinha";
    public static final String PEDIDO_SEM_PRODUTOS_BAR = "Pedido Não Possui Produtos para Bar";
    public static final String TRANSICAO_STATUS_INVALIDA = "Transição de Status Inválida";

    // Mensagens de log
    public static final String LOG_NOTIFICACAO_CRIADA = "Notificação {} criada para pedido {}";
    public static final String LOG_NOTIFICACAO_ATUALIZADA = "Notificação {} atualizada com status {}";
    public static final String LOG_NOTIFICACOES_PROCESSADAS = "Processadas {} notificações para pedido {}";
    public static final String LOG_ERRO_CRIAR_NOTIFICACAO = "Erro ao criar notificação para pedido {}";
}
