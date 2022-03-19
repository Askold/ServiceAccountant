package ru.silonov.accountant.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.silonov.accountant.Constants;
import ru.silonov.accountant.model.ReportEntity;
import ru.silonov.accountant.util.HibernateUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProvider implements IDataProvider {

    private static final Logger logger = LogManager.getLogger(DataProvider.class);
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ReportEntity insert(ReportEntity entity) {
        Transaction transaction = null;
        try (Session session = getSession()){
            transaction = session.beginTransaction();
            entity = (ReportEntity) session.merge(entity);
            transaction.commit();
            logger.info(entity.getClass().getSimpleName()+Constants.ADDED);
            return entity;
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getClass()+e.getMessage());
            if (transaction != null){
                transaction.rollback();
            }
        }
        return entity;
    }

    @Override
    public Optional<ReportEntity> getById(int id) {
        Transaction transaction = null;
        Optional<ReportEntity> optional;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            optional = Optional.ofNullable(session.get(ReportEntity.class, id));
            transaction.commit();
            logger.info(ReportEntity.class.getSimpleName()+Constants.FOUND);
            return optional;
        } catch (Exception e){
            logger.error(e.getClass()+e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return Optional.empty();
        }
    }

    @Override
    public List<ReportEntity> selectAll() {
        Transaction transaction = null;
        List<ReportEntity> list = new ArrayList<>();
        try (Session session = getSession()){
            transaction = session.beginTransaction();
            list = session.createQuery("from ReportEntity ", ReportEntity.class).list();
            transaction.commit();
            logger.info(Constants.RECORDS_SELECTED);
            return list;
        }
        catch (Exception e){
            logger.error(e.getClass()+e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return list;
        }
    }

    public List<ReportEntity> selectCurrent(){
        Transaction transaction = null;
        List<ReportEntity> list = new ArrayList<>();
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            //String hql = "from AccountantEntity where ", new Date(System.currentTimeMillis());
            Query<ReportEntity> query = session.createQuery("from ReportEntity where date = :date", ReportEntity.class);
            query.setParameter("date", format.format(new Date(System.currentTimeMillis())));
            list = query.list();
            transaction.commit();
            logger.info(Constants.RECORDS_SELECTED);
            return list;
        }
        catch (Exception e){
            logger.error(e.getClass()+e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return list;
        }
    }

    @Override
    public boolean update(ReportEntity entity) {
        Transaction transaction = null;
        try (Session session = getSession()){
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            logger.info(entity.getClass().getSimpleName()+Constants.UPDATED);
            return true;
        } catch (Exception e){
            logger.error(e.getClass() + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(new ReportEntity(id));
            transaction.commit();
            logger.info(ReportEntity.class.getSimpleName()+id+Constants.DELETED);
            return true;
        } catch (Exception e){
            logger.error(e.getClass()+e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }


    /**
     * Receives Session object via SessionFactory
     * @return Session object
     */
    Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
}
