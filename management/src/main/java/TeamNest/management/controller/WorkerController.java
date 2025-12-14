package TeamNest.management.controller;

import TeamNest.management.model.Worker;
import TeamNest.management.service.WorkerService;
import org.springframework.http.ResponseEntity;
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
    public List<Worker> getAllWorkers() throws Exception {
        return workerService.getAllWorkers();
    }

    
    @PostMapping
    public ResponseEntity<String> createWorker(@RequestBody Worker worker) {
        try {
            workerService.insertWorker(worker);
            return ResponseEntity.ok("Worker creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear worker: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateWorker(@RequestBody Worker worker) {
        try {
            workerService.updateWorker(worker);
            return ResponseEntity.ok("Worker actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear worker: " + e.getMessage());
        }
    }

}
