package TeamNest.management.dao;

import TeamNest.management.model.Worker;

import java.sql.Connection;
import java.sql.SQLException;

/*
    This functional interface would allow us to update the specific part of the worker, it will keep the conectivity open
    and we will use the only one anonimous function can be declared here.
 */
@FunctionalInterface
public interface WorkerUpdater<T extends Worker>{
    void update(Connection conn, T worker) throws SQLException;
}
