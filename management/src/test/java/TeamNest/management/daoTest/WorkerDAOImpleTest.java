package TeamNest.management.daoTest;

import TeamNest.management.dao.WorkerDAO;
import TeamNest.management.dao.WorkerDAOImple;
import TeamNest.management.model.Player;
import TeamNest.management.model.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

class WorkerDAOImpleTest {

    private WorkerDAO dao;

    @BeforeEach
    void setUp() {
        dao = new WorkerDAOImple();
    }

    @Test
    void testGetByDni() throws Exception {
        Worker worker = dao.getWorker("DNI2001");
        Assertions.assertNotNull(worker);
        Assertions.assertInstanceOf(Player.class, worker);

        Player player = (Player) worker;
        Assertions.assertEquals("DNI2001", player.getDni());
        Assertions.assertEquals("Jude Bellingham", player.getName());
    }
}
