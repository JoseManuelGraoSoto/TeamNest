package TeamNest.management.service;

import TeamNest.management.dao.WorkerDAO;
import TeamNest.management.model.Worker;

import java.util.List;

public class WorkerServiceImple implements WorkerService{

    private final WorkerDAO workerDAO;

    public WorkerServiceImple(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    @Override
    public Worker getWorker(String dni) {
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
