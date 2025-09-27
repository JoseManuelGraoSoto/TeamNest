package TeamNest.management.dao;

import TeamNest.management.model.Assistant;
import TeamNest.management.model.Executive;
import TeamNest.management.model.Player;
import TeamNest.management.model.Worker;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@Repository indica a Spring que esta clase forma parte de la capa de
acceso a datos, y permite que se manejen automáticamente excepciones como SQLException.
 */
@Repository
public class WorkerDAOImple implements WorkerDAO {

    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /*
        Map that will contain the lambda expressions that we will need to insert specific information in the workers
     */
    private final Map<Class<? extends Worker>, WorkerInserter<? extends Worker>> insertStatements =
            //Map.of() static method that creates an inmutable Map
            Map.of(
                    Player.class, (WorkerInserter<Player>) (conn, p) -> {
                        String sql = "INSERT INTO player (dni, age, marketValue, conditionToPlay) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                            ps.setString(1, p.getDni());
                            ps.setInt(2, p.getAge());
                            ps.setDouble(3, p.getMarketValue());
                            ps.setBoolean(4, p.getConditionToPlay());
                            ps.executeUpdate();
                        }
                    },
                    Assistant.class, (WorkerInserter<Assistant>) (conn, a) -> {
                        String sql = "INSERT INTO assistant (dni, job, speciality) VALUES (?, ?, ?)";
                        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                            ps.setString(1, a.getDni());
                            ps.setString(2, a.getJob());
                            ps.setString(3, a.getSpeciality());
                            ps.executeUpdate();
                        }
                    },
                    Executive.class, (WorkerInserter<Executive>) (conn, e) -> {
                        String sql = "INSERT INTO executive (dni, job) VALUES (?, ?)";
                        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
                            ps.setString(1, e.getDni());
                            ps.setString(2, e.getJob());
                            ps.executeUpdate();
                        }
                    }
            );

    @Override
    public void insertWorker(Worker worker) {
        String sql = "INSERT INTO worker (dni, name, phoneNumber, type) VALUES (?, ?, ?, ?)";
        try(Connection conn = getConnection()){
            conn.setAutoCommit(false); // Start transaction with the DB
            try(PreparedStatement psBase = conn.prepareStatement(sql)){
                psBase.setString(1, worker.getDni());
                psBase.setString(2, worker.getName());
                psBase.setString(3, worker.getPhoneNumber());
                psBase.setString(4, worker.getClass().getSimpleName().toLowerCase());
                psBase.executeUpdate();
            }

            @SuppressWarnings("unchecked")
            WorkerInserter<Worker> inserter = (WorkerInserter<Worker>)insertStatements.get(worker.getClass());

            if(inserter != null) {
                inserter.insert(conn, worker);
            }

            conn.commit();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateWorker(Worker worker) {

    }

    @Override
    public void deleteWorker(String dni) {

    }

    private Worker mapWorker(WorkerBaseInfo workerBase) throws Exception {
        if(workerBase == null) return null;
        return switch(workerBase.type()){
            case "player" -> fetchExtendedWorker(
                    "SELECT age, marketValue, conditionToPlay FROM player WHERE dni =?",
                    workerBase.dni(),
                    workerBase,
                    (rs, b) -> new Player(b.dni(), b.name(), b.phoneNumber(),
                            rs.getInt("age"), rs.getLong("marketValue"), rs.getBoolean("conditionToPlay"))
            );
            case "assistant" -> fetchExtendedWorker(
                    "SELECT job, speciality FROM assistant WHERE dni =?",
                    workerBase.dni(),
                    workerBase,
                    (rs, b) -> new Assistant(b.dni(), b.name(), b.phoneNumber(),
                            rs.getString("job"), rs.getString("speciality"))
            );
            case "executive" -> fetchExtendedWorker(
                    "SELECT job FROM executive WHERE dni =?",
                    workerBase.dni(),
                    workerBase,
                    (rs, b) -> new Executive(b.dni(), b.name(), b.phoneNumber(),
                            rs.getString("job"))
            );
            default -> throw new Exception("Tipo desconocido: " + workerBase.type());
        };
    }

    @Override
    public Worker getWorker(String dni) {
        String sql = "SELECT dni, name, phoneNumber, type FROM worker WHERE dni=?";
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getString("phoneNumber") + " " + rs.getString("type"));
                WorkerBaseInfo workerBase = new WorkerBaseInfo(
                    rs.getString("dni"),
                    rs.getString("name"),
                    rs.getString("phoneNumber"),
                    rs.getString("type")
                );
                return mapWorker(workerBase);
            }
        }catch(SQLException e){
            System.out.println(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        List<Worker> workers = new ArrayList<>();

        String sql = "SELECT dni, name, phoneNumber, type FROM worker";
        try(Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getString("phoneNumber") + " " + rs.getString("type"));
                WorkerBaseInfo workerB = new WorkerBaseInfo(
                        rs.getString("dni"),
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("type")
                );
                workers.add(mapWorker(workerB));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return workers;
    }
}
