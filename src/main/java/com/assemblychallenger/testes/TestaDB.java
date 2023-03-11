package com.assemblychallenger.testes;//package testes;
//
//import dao.AssemblyDao;
//import dao.LinhasDeMontagensDao;
//import infra.JPAUtil;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//
//import models.Assembly;
//import models.LinhasDeMontagens;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestaDB {
//
//
//    public static void main(String[] args) {
//        //cadastrarAssembly();
//        //cadastrarListAssembly();
//        //findAssembly();
//        //findLinhas();
//        //find(1L);
//       // LinhasDeMontagensDao dao = new LinhasDeMontagensDao();
//        //dao.listAllLinhasDeMontagem();
//        //TestaDB t = new TestaDB();
//        //t.adicionarLinhaDeMontagem("Linha 3");
//        EntityManager em = JPAUtil.getEntityManager();;
//        //LinhasDeMontagensDao dao = new LinhasDeMontagensDao(em);
//        //dao.saveLinhaDeMontagem(1);
//        //LinhasDeMontagensDao dao = new LinhasDeMontagensDao(em);
//        //System.out.println(dao.findAll());
//        AssemblyDao dao = new AssemblyDao(em);
//        dao.excluirTodos();
//
//        LinhasDeMontagensDao lDao = new LinhasDeMontagensDao(em);
//        lDao.excluirTodos();
//
//
//
//    }
//
//    @PersistenceContext
//    EntityManager em = JPAUtil.getEntityManager();;
//    public void adicionarLinhaDeMontagem(String linhaMontagem) {
//        //EntityManager em = JPAUtil.getEntityManager();
//        LinhasDeMontagensDao dao = new LinhasDeMontagensDao(em);
//        List<LinhasDeMontagens> linhasDeMontagem = new ArrayList<>();
//        // Cria uma nova instância da linha de montagem e a persiste no banco de dados
//        LinhasDeMontagens linhaDeMontagem = new LinhasDeMontagens();
//
//
//        // Verifica se já existe uma linha de montagem com o mesmo nome
//        TypedQuery<LinhasDeMontagens> query = em.createQuery("SELECT l FROM LinhasDeMontagens l WHERE l.linhaMontagem = :linhaMontagem", LinhasDeMontagens.class);
//        query.setParameter("linhaMontagem", linhaMontagem);
//        linhasDeMontagem = query.getResultList();
//
//        if (linhasDeMontagem.isEmpty()){
//            linhaDeMontagem.setLinhaMontagem(linhaMontagem);
//
//            em.getTransaction().begin();
//            em.persist(linhaDeMontagem);
//            em.getTransaction().commit();
//            em.close();
//
//        } else
//        if (!linhasDeMontagem.isEmpty()) {
//            throw new RuntimeException("Já existe uma linha de montagem com o linhaMontagem " + linhaMontagem);
//        }
//
//
//
//    }
//
//    public static void findAssembly(){
//        EntityManager em = JPAUtil.getEntityManager();
//        AssemblyDao dao = new AssemblyDao(em);
//        List<Assembly> a = new ArrayList<>();
//
//    }
//    public static void find(Long id){
//        EntityManager em = JPAUtil.getEntityManager();
//        AssemblyDao dao = new AssemblyDao(em);
//
//        dao.findAllById(id);
//    }
//    public static void findLinhas(){
//        EntityManager em = JPAUtil.getEntityManager();
//        LinhasDeMontagensDao dao = new LinhasDeMontagensDao(em);
//        List<LinhasDeMontagens> l = new ArrayList<>();
//        l = dao.findAll();
//        System.out.println(l);
//    }
//    private static void cadastrarAssembly() {
//        EntityManager em = JPAUtil.getEntityManager();
//        AssemblyDao dao = new AssemblyDao(em);
//        LinhasDeMontagens linhas = new LinhasDeMontagens();
//        Assembly assembly = new Assembly();
//        assembly.setDescription("Deescrição 1");
//        assembly.setTime("30 minutos");
//        assembly.setInitialHours(LocalTime.of(12,00));
//        em.getTransaction().begin();
//        dao.save(assembly);
//        em.getTransaction().commit();
//        em.close();
//
//
//    }
//
//    private static void cadastrarLinhasDeManutencoes() {
//
//    }
//
//    private static void cadastrarListAssembly() {
//
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
//
//
//    }
//
//}
