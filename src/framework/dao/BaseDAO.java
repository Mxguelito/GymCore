package framework.dao;

import framework.database.ConnectionManager;
import framework.repository.Repository;

import java.sql.Connection;

public abstract class BaseDAO<T> implements Repository<T> {

    protected Connection getConnection() {
        return ConnectionManager.getConnection();
    }

}