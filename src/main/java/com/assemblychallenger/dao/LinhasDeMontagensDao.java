package com.assemblychallenger.dao;

import com.assemblychallenger.infra.JPAUtil;
import com.assemblychallenger.models.Assembly;
import com.assemblychallenger.models.LinhasDeMontagens;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class LinhasDeMontagensDao {
    @PersistenceContext
    private EntityManager em;
    public LinhasDeMontagensDao() {

    }
    public LinhasDeMontagensDao(EntityManager em) {
        this.em = em;
    }

    public void listAllLinhasDeMontagem() {

        EntityManager em = JPAUtil.getEntityManager();
        // Cria uma consulta JPQL para selecionar todas as linhas de produção
        Query query = em.createQuery("FROM " + LinhasDeMontagens.class.getName());

        // Executa a consulta e obtém os resultados como uma lista de LinhaDeMontagem
        List<LinhasDeMontagens> linhasDeMontagem = query.getResultList();

        // Itera sobre a lista de linhas de produção e imprime suas informações
        for (LinhasDeMontagens linhaDeMontagem : linhasDeMontagem) {
            System.out.println("Linah de montagem: " + linhaDeMontagem.getLinhaMontagem());

            // Imprime as informações de todos os assemblies associados a esta linha de produção
            List<Assembly> assemblys = linhaDeMontagem.getAssemblys();
            for (Assembly assembly : assemblys) {
                System.out.println("  Initial Hours: " + assembly.getInitialHours());

                System.out.println("  Assembly Nome: " + assembly.getDescription());
                System.out.println("  Tempo: " + assembly.getTime());
            }
        }
    }


    public void excluirTodos() {
        // Remove todas as instâncias de Assembly
        EntityManager em = JPAUtil.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery("DELETE FROM LinhasDeMontagens");
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<LinhasDeMontagens> findAll() {
        return em.createQuery("SELECT l FROM LinhasDeMontagens l").getResultList();
    }

    public LinhasDeMontagens saveLinhaDeMontagem(Integer number) {
        LinhasDeMontagens montagens = new LinhasDeMontagens();
        montagens.setLinhaMontagem("Linha de Montagem: " + number);
        return adicionarLinhaDeMontagem(montagens.getLinhaMontagem(), number);
    }

    public LinhasDeMontagens adicionarLinhaDeMontagem(String linhaMontagem, Integer number) {
        LinhasDeMontagensDao dao = new LinhasDeMontagensDao(em);
        List<LinhasDeMontagens> linhasDeMontagem = new ArrayList<>();
        LinhasDeMontagens linhaDeMontagem = new LinhasDeMontagens();
        TypedQuery<LinhasDeMontagens> query = em.createQuery("SELECT l FROM LinhasDeMontagens l WHERE l.linhaMontagem = :linhaMontagem", LinhasDeMontagens.class);
        query.setParameter("linhaMontagem", linhaMontagem);
        linhasDeMontagem = query.getResultList();

        if (linhasDeMontagem.isEmpty()){
            linhaDeMontagem.setLinhaMontagem(linhaMontagem);
            save(linhaDeMontagem);
        }

        if (!linhasDeMontagem.isEmpty()) {
            linhasDeMontagem = findAll();
            while (linhasDeMontagem.size() >= number){
                number++;
            }
            System.out.println("Foi adicionada uma nova Linha de montagem " + linhaMontagem);
            int i = 1;
            linhaDeMontagem.setLinhaMontagem("Linha de Montagem: " + number++);
            save(linhaDeMontagem);
        }
        return linhaDeMontagem;
    }

    public void save(LinhasDeMontagens linhasDeMontagens) {
        em.getTransaction().begin();
        em.persist(linhasDeMontagens);
        em.getTransaction().commit();
        em.close();
    }
    public void saveTeste(LinhasDeMontagens linhasDeMontagens) {
        em.persist(linhasDeMontagens);
    }

    public void update(LinhasDeMontagens linhasDeMontagensDao) {
        this.em.merge(linhasDeMontagensDao);
    }

    public void remove(LinhasDeMontagens linhasDeMontagensDao) {
        linhasDeMontagensDao = em.merge(linhasDeMontagensDao);
        this.em.remove(linhasDeMontagensDao);
    }

}
