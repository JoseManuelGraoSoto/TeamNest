package TeamNest.management.dao;

import TeamNest.management.model.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface WorkerMapper<T extends Worker> {
    T map(ResultSet rs, WorkerBaseInfo base) throws SQLException;
}
