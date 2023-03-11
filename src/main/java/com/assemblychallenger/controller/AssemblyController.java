package com.assemblychallenger.controller;

import com.assemblychallenger.dao.AssemblyDao;
import com.assemblychallenger.dao.LinhasDeMontagensDao;
import com.assemblychallenger.infra.JPAUtil;
import com.assemblychallenger.models.Assembly;
import com.assemblychallenger.models.LinhasDeMontagens;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssemblyController {

    LinhasDeMontagens linhasDeMontagens = new LinhasDeMontagens();

    public LinhasDeMontagens getLinhasDeMontagens() {
        return linhasDeMontagens;
    }
    public void setLinhasDeMontagens(LinhasDeMontagens linhasDeMontagens) {this.linhasDeMontagens = linhasDeMontagens;}

    public void populaMontagens(Integer number){
        EntityManager em = JPAUtil.getEntityManager();
        LinhasDeMontagensDao linhasDeMontagensDao = new LinhasDeMontagensDao(em);
        linhasDeMontagens = linhasDeMontagensDao.saveLinhaDeMontagem(number);
    }
    public List<LinhasDeMontagens> findAllMontagens() {
        EntityManager em = JPAUtil.getEntityManager();
        LinhasDeMontagensDao linhasDeMontagensDao = new LinhasDeMontagensDao(em);
        return linhasDeMontagensDao.findAll();
    }
    public List<Assembly> findAllAssemblies() {
        EntityManager em = JPAUtil.getEntityManager();
        AssemblyDao dao = new AssemblyDao(em);
        return dao.findAll();
    }
    public List<Assembly> verificaLinha(List<String> atividades) {
        List<Assembly> assemblyList = new ArrayList<>();
        Collections.shuffle(atividades);
        LocalTime inicioDaManha = LocalTime.of(9, 0);
        LocalTime horarioInvalidoManha = LocalTime.of(11, 45);
        LocalTime almoco = LocalTime.of(12, 0);
        LocalTime inicioDaTarde = LocalTime.of(13, 0);
        LocalTime fimDaTarde = LocalTime.of(17, 0);
        LocalTime horaAtual = inicioDaManha;

        LocalTime horarioMinGinastica = LocalTime.of(16, 0);
        LocalTime horarioMaxGinastica = LocalTime.of(17, 0);

        String regex = "(.*?)(\\d+)min|(.*?)(\\d+)minute|(.*?)(\\d+)minutes|(.*?)(\\d+) min|(.*?)(\\d+) minute|(.*?)(\\d+) minutes|(.*?)(\\d+) minuto|(.*?)(\\d+) minutos";

        while (horaAtual.isBefore(almoco)) {
            for (int i = 0; i < atividades.size(); i++) {
                Matcher matcher = Pattern.compile(regex).matcher(atividades.get(i));
                if (matcher.matches()) {
                    String descricao = matcher.group(1);
                    int duracao = Integer.parseInt(matcher.group(2));

                    if ((duracao <= Duration.between(horaAtual, almoco).toMinutes()) && !horarioInvalidoManha.equals(horaAtual.plusMinutes(duracao))) {
                        addAssembly(descricao, duracao, horaAtual);
                        assemblyList.add(new Assembly(descricao, duracao, horaAtual));
                        horaAtual = horaAtual.plusMinutes(duracao);
                        atividades.remove(atividades.get(i));
//                        break;
                    }
                    if (horaAtual == almoco){
                        //Horário de Almoço
                        addAssembly("Horário de almoço", 0, almoco);
                        break;
                    }
                }
            }
        }

        horaAtual = inicioDaTarde;
        while (horaAtual.isBefore(fimDaTarde)) {
            for (int i = 0; i < atividades.size(); i++) {
                if (!atividades.get(i).contains("maintenance")){
                    Matcher matcher = Pattern.compile(regex).matcher(atividades.get(i));
                    /* O método matches() é usado para verificar se a sequência de caracteres inteira corresponde ao padrão definido pela expressão regular. */
                    if (matcher.matches()) {
                        String descricao = matcher.group(1);
                        int duracao = Integer.parseInt(matcher.group(2));
                        if (duracao <= Duration.between(horaAtual, fimDaTarde).toMinutes()) {
                            addAssembly(descricao, duracao, horaAtual);
                            assemblyList.add(new Assembly(descricao, duracao, horaAtual));
                            horaAtual = horaAtual.plusMinutes(duracao);
                            atividades.remove(atividades.get(i));
                       }
                    }
                }else{
                    String descricao = atividades.get(i);
                    int maintenanceTime = 5;
                    addAssembly(descricao, 0, horaAtual);
                    assemblyList.add(new Assembly(descricao, 0, horaAtual));
                    horaAtual = horaAtual.plusMinutes(maintenanceTime);
                    atividades.remove(atividades.get(i));
                }
                if (horaAtual.getHour() >= horarioMinGinastica.getHour()  && horaAtual.getHour() <= horarioMaxGinastica.getHour()) {
                    addAssembly("Ginastica Laboral", 0, horaAtual);
                    horaAtual = horarioMaxGinastica;
                }if (horaAtual == horarioMaxGinastica){
                    break;
                }
            }
        }
        return assemblyList;
    }

    public void addAssembly(String descricao, int duracao, LocalTime horario){
        Assembly assembly = new Assembly();
        EntityManager em = JPAUtil.getEntityManager();
        AssemblyDao dao = new AssemblyDao(em);
        assembly.setLinhasDeMontagens(linhasDeMontagens);
        assembly.setDescription(descricao);
        if (duracao != 0){
            assembly.setTime(duracao + " Minutos");
        }else{
            assembly.setTime(null);
        }
        assembly.setInitialHours(horario);
        dao.save(assembly);
        em.close();
    }

}
