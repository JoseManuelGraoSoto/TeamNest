package TeamNest.management.dao;

import TeamNest.management.model.Worker;

import java.sql.*;
import java.util.List;

public class WorkerDAOImple implements WorkerDAO {

    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");

    private Connection getConnection() throws SQLException {
        Connection conn = null;

    }

    @Override
    public void insertWorker(Worker worker) {
    }

    @Override
    public void updateWorker(Worker worker) {

    }

    @Override
    public void deleteWorker(String dni) {

    }

    @Override
    public Worker getWorker(String dni) {

    }

    @Override
    public List<Worker> getAllWorkers() {
        return List.of();
    }
}
