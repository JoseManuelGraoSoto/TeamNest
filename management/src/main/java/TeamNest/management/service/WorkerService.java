package TeamNest.management.service;

import TeamNest.management.model.Worker;

import java.util.List;

public interface WorkerService {
    public Worker getWorker(String dni);
    public List<Worker> getWorkers();
    public void removeWorker(String dni);
    public void addWorker(Worker worker);
    public void updateWorker(Worker worker);
}
