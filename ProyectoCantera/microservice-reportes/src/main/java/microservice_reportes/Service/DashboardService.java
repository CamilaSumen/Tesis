package microservice_reportes.Service;


import microservice_reportes.Model.DashboardData;

import java.time.LocalDate;

public interface DashboardService {
    DashboardData obtenerDatosDashboard(LocalDate fecha);
}

