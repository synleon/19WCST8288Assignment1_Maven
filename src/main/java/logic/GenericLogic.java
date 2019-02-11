package logic;

import dao.GenericDAO;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author Shariar
 * @param <E> - entity type
 * @param <T> - DAO type
 */
public abstract class GenericLogic< E, T extends GenericDAO<E>>{

    private final T DAO;

    GenericLogic( T dao) {
        this.DAO = dao;
    }

    protected final T dao(){
        return DAO;
    }

    protected <R> R get( Supplier<R> supplier){
        R r;
        DAO.beginTransaction();
        r = supplier.get();
        DAO.closeTransaction();
        return r;
    }

    public void add( E entity){
        DAO.beginTransaction();
        DAO.save(entity);
        DAO.commitAndCloseTransaction();
    }

    public void delete( E entity){
        DAO.beginTransaction();
        DAO.delete(entity);
        DAO.commitAndCloseTransaction();
    }

    public E update( E entity){
        DAO.beginTransaction();
        E e = DAO.update(entity);
        DAO.commitAndCloseTransaction();
        return e;
    }

    protected abstract E createEntity(Map<String, String[]> requestData);
}
