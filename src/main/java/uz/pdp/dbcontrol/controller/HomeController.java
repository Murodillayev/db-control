package uz.pdp.dbcontrol.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class HomeController {
    private final ReportService reportService;

    public HomeController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/send")
    public String sendReport() {
        reportService.sendReport();
        return "success send report";
    }
}
