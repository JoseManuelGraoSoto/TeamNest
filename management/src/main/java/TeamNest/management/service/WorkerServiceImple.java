package TeamNest.management.service;

import TeamNest.management.dao.WorkerDAO;
import TeamNest.management.model.Worker;
import org.springframework.stereotype.Service;

import java.util.List;
/*
La anotación @Service le dice a Spring:
    "Esta clase es un componente de lógica de negocio. Por favor, gestionala automáticamente (como un bean)."
 */
@Service
public class WorkerServiceImple implements WorkerService{

    private final WorkerDAO workerDAO;

    public WorkerServiceImple(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    @Override
    public Worker getWorker(String dni) throws Exception {
        return workerDAO.getWorker(dni);
    }

    @Override
    public List<Worker> getWorkers() {
        return workerDAO.getAllWorkers();
    }

    @Override
    public void removeWorker(String dni) {
        workerDAO.deleteWorker(dni);
    }

    @Override
    public void addWorker(Worker worker) {
        workerDAO.insertWorker(worker);
    }

    @Override
    public void updateWorker(Worker worker) {
        workerDAO.updateWorker(worker);
    }
}
