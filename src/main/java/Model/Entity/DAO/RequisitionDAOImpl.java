package Model.Entity.DAO;

import Model.Entity.Requisition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pyshankov on 27.10.15.
 */
//@Stateless
public class RequisitionDAOImpl implements RequisitioDAO {
    private  static  org.hibernate.SessionFactory factory;

    public RequisitionDAOImpl(org.hibernate.SessionFactory factory) {
        this.factory=factory;
    }

    @Override
    public Long addRequisition(Requisition r) throws SQLException {
        Session session = factory.openSession();
        Transaction tx = null;
        Long id = null;
        try{
            tx = session.beginTransaction();
           id = (Long) session.save(r);

            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  id;
    }

    @Override
    public List<Requisition> getAll() throws SQLException {
        Session session = factory.openSession();
        List<Requisition> r = new LinkedList();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            r = session.createQuery("FROM Model.Entity.Requisition").list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return r;
    }

    @Override
    public Requisition getById(long id) throws SQLException {
        Session session = factory.openSession();
        Requisition r = null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            r=(Requisition)session.load(Requisition.class,id);
            tx.commit();

        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return r;
    }

    @Override
    public Requisition update(long id, Requisition r) throws SQLException {
        Session session = factory.openSession();
        Transaction tx = null;
        Requisition r1 = null;
        try{
            tx = session.beginTransaction();
            r1 = (Requisition) session.get(Requisition.class,id);
            r1.setRequisition(r);
            tx.commit();

        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return r1;
    }

    @Override
    public Requisition delete(long id) throws SQLException {
        Session session = factory.openSession();
        Transaction tx = null;
        Requisition r = null;
        try{
            tx = session.beginTransaction();
            r = (Requisition)session.load(Requisition.class,id);
            session.delete(getById(id));
//              session.createSQLQuery("DELETE FROM requisition WHERE id="+id).executeUpdate();
            tx.commit();

        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return r;
    }

    @Override
    public List<Requisition> getByDate(String d1, String d2)  throws  SQLException{
        Session session = factory.openSession();
        List<Requisition> r = new ArrayList();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            r = session.createQuery("from Model.Entity.Requisition re where requisitionDate >= '"
                    +java.sql.Date.valueOf(d1)+"' AND requisitionDate <= '"+java.sql.Date.valueOf(d2)+"' order by requisitionDate DESC").list();
//            r = session.createSQLQuery("SELECT * from requisition WHERE requisitiondate >="
//                    +sDate1+" AND  requisitiondate<= "+sDate2+"  order by requisitiondate desc").list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  r;
    }
    @Override
    public List<Requisition> getCheckDate(String d1, String d2) throws SQLException {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Requisition> rl = getByDate(d1,d2);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date uDateNow = new java.util.Date();
        java.sql.Date sDate = new Date(uDateNow.getTime());
            try {
                tx = session.beginTransaction();

                for (Requisition r : rl) {
                    r.getTimeToSent();
                    if (r.getTimeToSent()!=null&&r.getTimeToSent().compareTo(sDate) <=0&&r.getCompleteMark()!="выполнено") {
                        r.setCompleteMark("не выполнено");
                        update(r.getId(),r);
                    }
                }
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
            }

        return rl;
    }
}
