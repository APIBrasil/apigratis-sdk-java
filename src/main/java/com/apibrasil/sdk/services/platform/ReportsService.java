package com.apibrasil.sdk.services.platform;

import com.apibrasil.sdk.core.ApiHttpClient;
import com.apibrasil.sdk.services.BaseService;

import java.util.Map;

/** Relatórios e dashboard de consumo. */
public class ReportsService extends BaseService {

    public ReportsService(ApiHttpClient http) {
        super(http);
    }

    /** Estatísticas do dashboard: {@code GET /dashboard/stats}. */
    public Map<String, Object> dashboardStats() {
        return get("dashboard/stats");
    }

    /** Consumo por serviço: {@code GET /reports/consumption}. */
    public Map<String, Object> consumption() {
        return get("reports/consumption");
    }

    /** Gera relatório de consumo: {@code POST /reports/generate-consumption-report}. */
    public Map<String, Object> generateConsumptionReport() {
        return post("reports/generate-consumption-report", null);
    }

    public Map<String, Object> generateConsumptionReport(Map<String, Object> body) {
        return post("reports/generate-consumption-report", body);
    }

    /** Extrato de uso: {@code GET /reports/extract}. */
    public Map<String, Object> extract() {
        return get("reports/extract");
    }

    /** Dashboard de relatórios: {@code GET /reports/dashboard}. */
    public Map<String, Object> dashboard() {
        return get("reports/dashboard");
    }

    /** Resumo: {@code GET /reports/summary}. */
    public Map<String, Object> summary() {
        return get("reports/summary");
    }

    /** Uso diário: {@code GET /reports/daily-usage}. */
    public Map<String, Object> dailyUsage() {
        return get("reports/daily-usage");
    }

    /** Resumo mensal: {@code GET /reports/monthly-summary}. */
    public Map<String, Object> monthlySummary() {
        return get("reports/monthly-summary");
    }

    /** Análise de erros: {@code GET /reports/error-analysis}. */
    public Map<String, Object> errorAnalysis() {
        return get("reports/error-analysis");
    }

    /** Análise por device: {@code GET /reports/device-analysis}. */
    public Map<String, Object> deviceAnalysis() {
        return get("reports/device-analysis");
    }

    /** Requisições recentes: {@code GET /reports/recent-requests}. */
    public Map<String, Object> recentRequests() {
        return get("reports/recent-requests");
    }

    /** Estatísticas rápidas: {@code GET /reports/quick-stats}. */
    public Map<String, Object> quickStats() {
        return get("reports/quick-stats");
    }
}
