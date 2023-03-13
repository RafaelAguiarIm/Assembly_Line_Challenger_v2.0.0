package com.assemblychallenger.dao;

import com.assemblychallenger.infra.JPAUtil;
import com.assemblychallenger.models.Assembly;
import com.assemblychallenger.models.LinhasDeMontagens;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class AssemblyDao {

    @PersistenceContext
    private EntityManager em;
    public AssemblyDao(EntityManager em) {
        this.em = em;
    }

    public List<Assembly> findAssembliesByLinhaDeMontagemId(Long linhaDeMontagemId) {
        LinhasDeMontagens linhaDeMontagem = em.find(LinhasDeMontagens.class, linhaDeMontagemId);
        System.out.println(linhaDeMontagem);
        if (linhaDeMontagem == null) {
            throw new IllegalArgumentException("Linha de montagem com id " + linhaDeMontagemId + " não encontrada.");
        }
        return new ArrayList<>(linhaDeMontagem.getAssemblys());
    }

    public void save(Assembly assembly) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(assembly);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Assembly assembly) {
        this.em.merge(assembly);
    }

    public void remove(Assembly assembly) {
        assembly = em.merge(assembly);
        this.em.remove(assembly);
    }

    public List<Assembly> findAll() {
        return em.createQuery("SELECT a FROM Assembly a").getResultList();
    }
    public List<Assembly> findAllById(Long id) {
        LinhasDeMontagens linhaProducao = this.em.find(LinhasDeMontagens.class, id);
        List<Assembly> assemblys = linhaProducao.getAssemblys();
        System.out.println(assemblys);
        return assemblys;
    }

    public void excluirTodos() {
        // Remove todas as instâncias de Assembly
        EntityManager em = JPAUtil.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery("DELETE FROM Assembly");
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}