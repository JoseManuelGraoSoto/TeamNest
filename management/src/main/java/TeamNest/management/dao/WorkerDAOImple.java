package TeamNest.management.dao;

import TeamNest.management.model.Assistant;
import TeamNest.management.model.Executive;
import TeamNest.management.model.Player;
import TeamNest.management.model.Worker;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class WorkerDAOImple implements WorkerDAO {

    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
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
    public Worker getWorker(String dni) throws Exception {
        WorkerBaseInfo base = getWorkerBaseInfo(dni);
        if(base == null) return null;
        return switch(base.type()){
            case "player" -> fetchExtendedWorker(
                    "SELECT age, marketValue, conditionToPlay FROM player WHERE dni =?",
                    dni,
                    base,
                    (rs, b) -> new Player(b.dni(), b.name(), b.phoneNumber(),
                            rs.getInt("age"), rs.getLong("marketValue"), rs.getBoolean("conditionToPlay"))
            );
            case "assistant" -> fetchExtendedWorker(
                    "SELECT job, speciality FROM assistant WHERE dni =?",
                    dni,
                    base,
                    (rs, b) -> new Assistant(b.dni(), b.name(), b.phoneNumber(),
                            rs.getString("job"), rs.getString("speciality"))
            );
            case "executive" -> fetchExtendedWorker(
                    "SELECT job FROM executive WHERE dni =?",
                    dni,
                    base,
                    (rs, b) -> new Executive(b.dni(), b.name(), b.phoneNumber(),
                            rs.getString("job"))
            );
            default -> throw new Exception("Tipo desconocido: " + base.type());
        };
    }

    private WorkerBaseInfo getWorkerBaseInfo(String dni) {
        String sql = "SELECT name, phoneNumber, type FROM worker WHERE dni=?";
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getString("phoneNumber") + " " + rs.getString("type"));
                return new WorkerBaseInfo(
                    dni,
                    rs.getString("name"),
                    rs.getString("phoneNumber"),
                    rs.getString("type")
                );
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

    private <T extends Worker> T fetchExtendedWorker(
            String sql,
            String dni,
            WorkerBaseInfo base,
            WorkerMapper<T> mapper
    ) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapper.map(rs, base);
            }
        }
        return null;
    }

    @Override
    public List<Worker> getAllWorkers() {
        return List.of();
    }
}
