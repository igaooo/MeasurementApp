package com.measurement.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import com.measurement.config.Page;
import com.measurement.dao.ClientDAO;
import com.measurement.models.Client;
import com.measurement.persistence.ConnectionDataBank;

public class ClientService {
    @PersistenceContext(unitName = "measurement")
    private final EntityManager entityManager;

    private ClientDAO dao;

    private EntityTransaction tx;

    public ClientService() {
        this.entityManager = ConnectionDataBank
                .getConnection()
                .getEntityManager();

        this.dao = new ClientDAO(entityManager);
    }

    public void add(Client obj) {
        tx = getEntityManager().getTransaction();

        try {
            getTx().begin();
            getDao().add(obj);
            getTx().commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (getTx().isActive()) {
                getTx().rollback();
            }
        } finally {
            getEntityManager().close();
        }
    }

    public Client update(Client obj) {
        tx = getEntityManager().getTransaction();

        try {
            getTx().begin();
            Client client = getDao().update(obj);
            getTx().commit();
            return client;
        } catch (Exception e) {
            e.printStackTrace();

            if (getTx().isActive()) {
                getTx().rollback();
            }
        } finally {
            getEntityManager().close();
        }
        return null;
    }

    public void deleteById(Long id) {
        tx = getEntityManager().getTransaction();

        try {
            getTx().begin();
            getDao().deleteById(id);
            getTx().commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (getTx().isActive()) {
                getTx().rollback();
            }
        } finally {
            getEntityManager().close();
        }
    }

    public void delete(Client client) {
        tx = getEntityManager().getTransaction();

        try {
            getTx().begin();
            getDao().delete(client);
            getTx().commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (getTx().isActive()) {
                getTx().rollback();
            }
        } finally {
            getEntityManager().close();
        }
    }

    public Client getById(Long id) {
        return getDao().getById(id);
    }

    public List<Client> getAll() {
        return getDao().getAll();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ClientDAO getDao() {
        return dao;
    }

    public void setDao(ClientDAO dao) {
        this.dao = dao;
    }

    public EntityTransaction getTx() {
        return tx;
    }

    public void setTx(EntityTransaction tx) {
        this.tx = tx;
    }

    public Client getByUserName(String text) {
        return getDao().getByUserName(text);
    }

    public Page<Client> paginatedList(int currentPage, int pageLength) {
        return dao.paginatedList(currentPage, pageLength);
    }
}
