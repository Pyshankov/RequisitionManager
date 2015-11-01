package Model.Entity.DAO;

import Model.Entity.Requisition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Local;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by pyshankov on 27.10.15.
 */
//@Local
public interface RequisitioDAO {
    Long addRequisition(Requisition r) throws SQLException;
    List<Requisition> getAll()throws SQLException;
    Requisition getById(long id) throws SQLException;
    Requisition update(long id , Requisition r) throws SQLException;
    Requisition delete(long id) throws  SQLException;
    List<Requisition> getByDate(String d1,String d2) throws  SQLException;
    List<Requisition> getCheckDate(String d1,String d2) throws SQLException ;
}
