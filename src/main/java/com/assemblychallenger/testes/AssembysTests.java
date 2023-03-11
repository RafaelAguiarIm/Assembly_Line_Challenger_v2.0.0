package com.assemblychallenger.testes;//package testes;
//
//import dao.AssemblyDao;
//import dao.LinhasDeMontagensDao;
//import infra.JPAUtil;
//import models.Assembly;
//import models.LinhasDeMontagens;
//import org.junit.Assert;
//import org.junit.Test;
//
//import javax.persistence.EntityManager;
//import java.time.LocalTime;
//
//public class AssembysTests {
//
//    //@Test
//    public void saveAssembly(){
//        EntityManager em = JPAUtil.getEntityManager();
//
//        LinhasDeMontagensDao montagensDao = new LinhasDeMontagensDao(em);
//        LinhasDeMontagens montagens1 = new LinhasDeMontagens();
//        montagens1.setLinhaMontagem("Linha 1");
//
//        AssemblyDao assemblyDao = new AssemblyDao(em);
//        Assembly assembly1 = new Assembly();
//
//        assembly1.setInitialHours(LocalTime.of(12,00));
//        assembly1.setDescription("Descrição 01");
//        assembly1.setTime("30 minutos");
//        assembly1.setLinhasDeMontagens(montagens1);
//
//        Assembly assembly2 = new Assembly();
//
//        assembly2.setInitialHours(LocalTime.of(12,00));
//        assembly2.setDescription("Descrição 01");
//        assembly2.setTime("30 minutos");
//        assembly2.setLinhasDeMontagens(montagens1);
//
//        em.getTransaction().begin();
//        montagensDao.saveTeste(montagens1);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        assemblyDao.save(assembly1);
//        assemblyDao.save(assembly2);
//        em.getTransaction().commit();
//
//        em.close();
//    }
//}
