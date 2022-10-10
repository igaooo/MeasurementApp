package com.measurement.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.measurement.config.Page;
import com.measurement.models.Client;


public class ClientDAO {
    private EntityManager entityManager;

    public ClientDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(Client obj) {
        getEntityManager().persist(obj);
    }

    public Client update(Client obj) {
        return getEntityManager().merge(obj);
    }

    public void deleteById(Long id) {
        Client client = getById(id);
        getEntityManager()
                .remove(client);
    }

    public void delete(Client obj) {
        getEntityManager()
                .remove(obj);
    }

    public Client getById(Long id) {
        return getEntityManager()
                .find(Client.class, id);
    }

    public List<Client> getAll() {
        return getEntityManager()
                .createQuery("select o from Client o", Client.class)
                .getResultList();
    }

    public List<Client> getByName(String value) {
        return getEntityManager()
                .createQuery("select o from Client o where o.name = :name", Client.class)
                .setParameter("name", value)
                .getResultList();
    }

    public Client getByUserName(String value) {
        try {
            return getEntityManager()
                    .createQuery("select u from Client u where u.userName = :user", Client.class)
                    .setParameter("user", value)
                    .getResultList().get(0);
        } catch (Exception e) {
        }
        return null;    
    }

    public Page<Client> paginatedList(int currentPage, int pageLength) {
        Page<Client> page = new Page<Client>();
        List<Client> clients = getEntityManager()
                .createQuery("SELECT c FROM Client c", Client.class)
                .setFirstResult(currentPage)
                .setMaxResults(pageLength).getResultList();

        int total = count().intValue();

        page.setContent(clients);
        page.setPage(currentPage);
        page.setPageSize(pageLength);
        page.setTotalRecords(total);
        page.setTotalPage(total / pageLength);

        return page;
    }

    public Long count() {
        return getEntityManager()
                .createQuery("SELECT COUNT(c) FROM Client c", Long.class)
                .getSingleResult();
    }
}
