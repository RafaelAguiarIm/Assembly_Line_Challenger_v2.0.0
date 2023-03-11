package com.assemblychallenger.controller;//package controller;
//
//import dao.AssemblyDao;
//import dao.LinhasDeMontagensDao;
//import infra.JPAUtil;
//import models.Assembly;
//import models.LinhasDeMontagens;
//
//import javax.persistence.EntityManager;
//import java.time.Duration;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class AssemblyController2 {
//
//    List<LinhasDeMontagens> listMontagens = new ArrayList<>();
//    List<Assembly> listaDeAssemblies = new ArrayList<>();
//    Assembly assembly;
//    LinhasDeMontagens linhasDeMontagens = new LinhasDeMontagens();
//
//    public List<LinhasDeMontagens> getListMontagens() {
//        return listMontagens;
//    }
//    public void setListMontagens(List<LinhasDeMontagens> listMontagens) {
//        this.listMontagens = listMontagens;
//    }
//    public List<Assembly> getListaDeAssemblies() {
//        return listaDeAssemblies;
//    }
//    public void setListaDeAssemblies(List<Assembly> listaDeAssemblies) { this.listaDeAssemblies = listaDeAssemblies;}
//    public Assembly getAssembly() {
//        return assembly;
//    }
//    public void setAssembly(Assembly assembly) {
//        this.assembly = assembly;
//    }
//    public LinhasDeMontagens getLinhasDeMontagens() {
//        return linhasDeMontagens;
//    }
//    public void setLinhasDeMontagens(LinhasDeMontagens linhasDeMontagens) {this.linhasDeMontagens = linhasDeMontagens;}
//
//    public void populaMontagens(Integer number){
//        EntityManager em = JPAUtil.getEntityManager();
//        LinhasDeMontagensDao linhasDeMontagensDao = new LinhasDeMontagensDao(em);
//        linhasDeMontagens = linhasDeMontagensDao.saveLinhaDeMontagem(number);
//    }
//    public List<LinhasDeMontagens> findAllMontagens() {
//        EntityManager em = JPAUtil.getEntityManager();
//        LinhasDeMontagensDao linhasDeMontagensDao = new LinhasDeMontagensDao(em);
//        return linhasDeMontagensDao.findAll();
//    }
//    public List<Assembly> findAllAssemblies() {
//        EntityManager em = JPAUtil.getEntityManager();
//        AssemblyDao dao = new AssemblyDao(em);
//        return dao.findAll();
//    }
//    public List<LinhasDeMontagens> verificaLinha(List<String> atividades) {
//        String regex = "(.*?)(\\d+)min|(.*?)(\\d+)minute|(.*?)(\\d+)minutes|(.*?)(\\d+) min|(.*?)(\\d+) minute|(.*?)(\\d+) minutes|(.*?)(\\d+) minuto|(.*?)(\\d+) minutos";
//        Boolean verificaRegex = true;
//        try {
//
//            Collections.shuffle(atividades);
//
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher;
//            LocalTime horarioInicioManha = LocalTime.of(9, 0);
//            LocalTime horarioInvalidoManha = LocalTime.of(11, 45);
//            LocalTime horarioFimManha = LocalTime.of(12, 0);
//            LocalTime horarioAtualManha = horarioInicioManha;
//
//            LocalTime horarioInicioTarde = LocalTime.of(13, 0);
//            LocalTime horarioInvalidoTarde = LocalTime.of(16, 45);
//            LocalTime horarioFimTarde = LocalTime.of(17, 0);
//            LocalTime horarioAtualTarde = horarioInicioTarde;
//
//            LocalTime horarioMinGinastica = LocalTime.of(16, 0);
//            LocalTime horarioMaxGinastica = LocalTime.of(17, 0);
//
//            LinhasDeMontagens linhas = new LinhasDeMontagens();
//
//            final String descricaoLinhaDeMontagem = "Linha de Montagem: ";
//
//            while (horarioAtualManha.isBefore(horarioFimManha)) {
//
//                int minutosRestantes = (int) Duration.between(horarioAtualManha, horarioFimManha).toMinutes();
//                boolean atividadeEncontrada = false;
//
//                int minutos = 0;
//                String atividade = null;
//                List<String> tokens = new ArrayList<>();
//                for (int i = 0; i < atividades.size(); i++) {
//                    System.out.println("...");
//                    System.out.println(atividades.get(i));
//                    if (!atividades.get(i).contains("maintenance")){
//                        matcher = pattern.matcher(atividades.get(i));
//                        if (matcher.find()) {
//                            atividade = matcher.group(1);
//                            minutos = Integer.parseInt(matcher.group(2));
//                        }
//                        if (horarioAtualManha.equals(horarioInvalidoManha)) {
//                            atividades.get(atividades.size() - 1);
//                            LocalTime horarioInicioAtividade = horarioAtualManha;
//                            LocalTime horarioFimAtividade = horarioAtualManha.plusMinutes(-minutos);
//                            horarioAtualManha = horarioFimAtividade;
//                            minutosRestantes += minutos;
//                        }
//                        if (minutos <= minutosRestantes) {
//                            LocalTime horarioInicioAtividade = horarioAtualManha;
//                            LocalTime horarioFimAtividade = horarioAtualManha.plusMinutes(minutos);
//                            if (!horarioFimAtividade.equals(horarioInvalidoManha)) {
//                                Assembly assembly1 = new Assembly();
//                                EntityManager em1 = JPAUtil.getEntityManager();
//                                AssemblyDao dao1 = new AssemblyDao(em1);
//                                assembly1.setLinhasDeMontagens(linhasDeMontagens);
//                                assembly1.setDescription(atividade);
//                                assembly1.setTime(minutos + " minutos");
//                                assembly1.setInitialHours(horarioAtualManha);
//                                dao1.save(assembly1);
//                                em1.close();
//                                listMontagens.add(assembly1.getLinhasDeMontagens());
//                                listaDeAssemblies.add(assembly1);
//                                linhas = new LinhasDeMontagens();
//                                atividades.remove(atividades.get(i));
//                                horarioAtualManha = horarioFimAtividade;
//                            } else {
//                                atividades.remove(atividades.get(i));
//                            }
//                            atividadeEncontrada = true;
//                            break;
//                        }
//                    }
//                }
//            }
//            {
//                Assembly assembly2 = new Assembly();
//                EntityManager em2 = JPAUtil.getEntityManager();
//                AssemblyDao dao2 = new AssemblyDao(em2);
//                assembly2.setLinhasDeMontagens(linhasDeMontagens);
//                assembly2.setDescription("Horário de almoço");
//                assembly2.setInitialHours(horarioFimManha);
//                dao2.save(assembly2);
//                em2.close();
//                listMontagens.add(assembly2.getLinhasDeMontagens());
//                listaDeAssemblies.add(assembly2);
//            }
//            while (horarioAtualTarde.isBefore(horarioFimTarde)) {
//                int minutosRestantes = (int) Duration.between(horarioAtualTarde, horarioFimTarde).toMinutes();
//                boolean atividadeEncontrada = false;
//                int minutos = 0;
//                String atividade = null;
//                List<String> tokens = new ArrayList<>();
//                for (int i = 0; i < atividades.size(); i++) {
//                    if (!atividades.get(i).contains("maintenance")) {
//                       matcher = pattern.matcher(atividades.get(i));
//                        if (matcher.find()) {
//                            atividade = matcher.group(1);
//                            minutos = Integer.parseInt(matcher.group(2));
//                        }
//                        if (horarioAtualTarde.equals(horarioInvalidoManha)) {
//                            atividades.get(atividades.size() - 1);
//                            LocalTime horarioInicioAtividade = horarioAtualTarde;
//                            LocalTime horarioFimAtividade = horarioAtualTarde.plusMinutes(-minutos);
//                            horarioAtualTarde = horarioFimAtividade;
//                            minutosRestantes += minutos;
//                        } else if (minutos != 0 && minutos <= minutosRestantes )  {
//                            LocalTime horarioInicioAtividade = horarioAtualTarde;
//                            LocalTime horarioFimAtividade = horarioAtualTarde.plusMinutes(minutos);
//                            if (horarioAtualTarde.getHour() < horarioFimTarde.getHour() && horarioAtualTarde.getHour() < horarioInvalidoTarde.getHour() || horarioAtualTarde.equals("16:30") && minutos <= 30) {
//                                minutosRestantes -= minutos;
//                                Assembly assembly3 = new Assembly();
//                                EntityManager em3 = JPAUtil.getEntityManager();
//                                AssemblyDao dao3 = new AssemblyDao(em3);
//                                assembly3.setLinhasDeMontagens(linhasDeMontagens);
//                                assembly3.setDescription(atividade);
//                                assembly3.setTime(minutos + " minutos");
//                                assembly3.setInitialHours(horarioAtualTarde);
//                                dao3.save(assembly3);
//                                em3.close();
//                                listMontagens.add(assembly3.getLinhasDeMontagens());
//                                listaDeAssemblies.add(assembly3);
//                                linhas = new LinhasDeMontagens();
//                                atividades.remove(atividades.get(i));
//                                horarioAtualTarde = horarioFimAtividade;
//                            }
//                        }
//                    }else if (atividades.get(i).contains("maintenance") && minutos == 0){
//                        minutos = 5;
//                        LocalTime horarioInicioAtividade = horarioAtualTarde;
//                        LocalTime horarioFimAtividade = horarioAtualTarde.plusMinutes(minutos);
//                        Assembly assembly4 = new Assembly();
//                        EntityManager em4 = JPAUtil.getEntityManager();
//                        AssemblyDao dao4 = new AssemblyDao(em4);
//                        assembly4.setLinhasDeMontagens(linhasDeMontagens);
//                        assembly4.setDescription(atividades.get(i));
//                        assembly4.setInitialHours(horarioInicioAtividade);
//                        dao4.save(assembly4);
//                        em4.close();
//                        listMontagens.add(assembly4.getLinhasDeMontagens());
//                        listaDeAssemblies.add(assembly4);
//                        assembly = new Assembly();
//                        linhas = new LinhasDeMontagens();
//                        atividades.remove(atividades.get(i));
//                        horarioAtualTarde = horarioFimAtividade;
//                    }
//                }
//                if (horarioAtualTarde.getHour() >= horarioMinGinastica.getHour()  && horarioAtualTarde.getHour() <= horarioMaxGinastica.getHour()) {
//                    LocalTime horarioInicioAtividade = horarioAtualTarde;
//                    System.out.println("\n Ginastica Laboral" + " - " + horarioInicioAtividade);
//                    Assembly assembly5 = new Assembly();
//                    EntityManager em5 = JPAUtil.getEntityManager();
//                    AssemblyDao dao5 = new AssemblyDao(em5);
//                    assembly5.setLinhasDeMontagens(linhasDeMontagens);
//                    assembly5.setDescription("Ginastica Laboral");
//                    assembly5.setInitialHours(horarioInicioAtividade);
//                    dao5.save(assembly5);
//                    em5.close();
//                    listMontagens.add(assembly5.getLinhasDeMontagens());
//                    listaDeAssemblies.add(assembly5);
//                    horarioAtualTarde = horarioFimTarde;
//                }
//            }
//            return listMontagens;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
