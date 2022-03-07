package ru.silonov.accountant.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.silonov.accountant.Constants;
import ru.silonov.accountant.model.AccountantEntity;
import ru.silonov.accountant.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProvider implements IDataProvider {

    private static final Logger logger = LogManager.getLogger(DataProvider.class);

    @Override
    public AccountantEntity insert(AccountantEntity entity) {
        Transaction transaction = null;
        try (Session session = getSession()){
            transaction = session.beginTransaction();
            entity = (AccountantEntity) session.merge(entity);
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
    public Optional<AccountantEntity> getById(long id) {
        Transaction transaction = null;
        Optional<AccountantEntity> optional;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            optional = Optional.ofNullable(session.get(AccountantEntity.class, id));
            transaction.commit();
            logger.info(AccountantEntity.class.getSimpleName()+Constants.FOUND);
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
    public List<AccountantEntity> selectAll() {
        Transaction transaction = null;
        List<AccountantEntity> list = new ArrayList<>();
        try (Session session = getSession()){
            transaction = session.beginTransaction();
            list = session.createQuery("from AccountantEntity ", AccountantEntity.class).list();
            transaction.commit();
            logger.info(Constants.RECORDS_SELECTED);
            return list;
        }
        catch (Exception e){
            logger.error(e.getClass()+e.getMessage());
            return list;
        }
    }

    @Override
    public boolean update(AccountantEntity entity) {
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
    public boolean delete(long id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(new AccountantEntity(id));
            transaction.commit();
            logger.info(AccountantEntity.class.getSimpleName()+id+Constants.DELETED);
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
