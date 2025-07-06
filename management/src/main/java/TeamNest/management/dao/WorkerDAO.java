package TeamNest.management.dao;

import TeamNest.management.model.Worker;

import java.util.List;

public interface WorkerDAO {
    public void insertWorker(Worker worker);
    public void updateWorker(Worker worker);
    public void deleteWorker(String dni);
    public Worker getWorker(String dni) throws Exception;
    public List<Worker> getAllWorkers();
}
