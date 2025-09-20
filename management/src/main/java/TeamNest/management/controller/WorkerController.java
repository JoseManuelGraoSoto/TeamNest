package TeamNest.management.controller;

import TeamNest.management.model.Worker;
import TeamNest.management.service.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/{dni}")
    public Worker getWorkerByDni(@PathVariable String dni) throws Exception {
        return workerService.getWorker(dni);
    }

    @GetMapping("/getAllWorkers")
    public List<Worker> getWorkerByDni() throws Exception {
        return workerService.getAllWorkers();
    }
}
