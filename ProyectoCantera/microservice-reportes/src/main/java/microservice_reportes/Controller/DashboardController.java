package microservice_reportes.Controller;

import microservice_reportes.Model.DashboardData;
import microservice_reportes.Service.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard/datos")
    public DashboardData obtenerDatosDashboard(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {


        if (fecha == null) {
            fecha = LocalDate.now();
        }

        return dashboardService.obtenerDatosDashboard(fecha);
    }
}
