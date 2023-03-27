package com.shapran.repository;

import com.shapran.model.Detail;
import com.shapran.model.StatsDTO;
import com.shapran.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class DetailRepository {
    private static DetailRepository instance;

    public static DetailRepository getInstance() {
        if (instance == null) {
            instance = new DetailRepository();
        }
        return instance;
    }

    public void save(Detail details) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(details);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<Detail> getById(String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Detail.class, id));
    }

    public StatsDTO getStats() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<StatsDTO> query = criteriaBuilder.createQuery(StatsDTO.class);
        final Root<Detail> root = query.from(Detail.class);
        query.select(criteriaBuilder.construct(
                StatsDTO.class,
                criteriaBuilder.count(root.get("id")),
                criteriaBuilder.sum(root.get("brokenMicrochips")),
                criteriaBuilder.sum(
                        criteriaBuilder.sum(root.get("amountFuel")),
                        criteriaBuilder.sum(root.get("usedFuel")))));
        return entityManager.createQuery(query).getSingleResult();
    }

    public List<String> getAllId() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                        "SELECT  s.id FROM Detail s", String.class)
                .getResultList();
    }

}
