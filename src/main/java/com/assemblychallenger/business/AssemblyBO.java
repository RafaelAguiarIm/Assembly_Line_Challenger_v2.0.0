package com.assemblychallenger.business;

import com.assemblychallenger.controller.AssemblyController;
import com.assemblychallenger.dao.AssemblyDao;
import com.assemblychallenger.dao.LinhasDeMontagensDao;
import com.assemblychallenger.infra.JPAUtil;
import com.assemblychallenger.models.Assembly;
import com.assemblychallenger.models.LinhasDeMontagens;
import org.primefaces.model.file.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AssemblyBO {


    Integer number = 0;

    List<LinhasDeMontagens> linhasDeMontagens = new ArrayList<>();
    List<Assembly> assemblyList = new ArrayList<>();

    ConversationListFromFile conversationListFromFile = new ConversationListFromFile();
    AssemblyController assemblyController = new AssemblyController();

    @PersistenceContext
    private EntityManager em = JPAUtil.getEntityManager();
    LinhasDeMontagensDao linhasDeMontagensDao = new LinhasDeMontagensDao(em);
    AssemblyDao assemblyDao = new AssemblyDao(em);

    public List<LinhasDeMontagens> getLinhasDeMontagens() { return linhasDeMontagens; }
    public void setLinhasDeMontagens(List<LinhasDeMontagens> linhasDeMontagens) { this.linhasDeMontagens = linhasDeMontagens; }


    public List<Assembly> getAssemblyList() { return assemblyList; }
    public void setAssemblyList(List<Assembly> assemblyList) {
        this.assemblyList = assemblyList;
    }

    public void populaInput(String caminho) throws Exception {

        if (conversationListFromFile.getConversationListFromFile(caminho).isEmpty()){
            setLinhasDeMontagens(assemblyController.findAllMontagens());
            setAssemblyList(assemblyController.findAllAssemblies());
            em.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "O arquivo não contém um conteúdo válido, seu conteúdo deve conter uma descrição da atividade e o tempo medido em minutos"));
        }else {
            number++;
            assemblyController.populaMontagens(number);
            conversationListFromFile.getConversationListFromFile(caminho);
            assemblyController.verificaLinha(conversationListFromFile.getConversationList());
            setLinhasDeMontagens(assemblyController.findAllMontagens());
            setAssemblyList(assemblyController.findAllAssemblies());
            em.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Arquivo enviado com sucesso!"));
            caminho = null;
        }
    }



    public void upload(UploadedFile file) throws Exception {

        byte[] conteudo = file.getContent();
        File arquivoTemporario = File.createTempFile("arquivo", ".tmp");
        FileOutputStream saida = new FileOutputStream(arquivoTemporario);
        saida.write(conteudo);
        saida.close();
        String caminhoCompleto = arquivoTemporario.getAbsolutePath();

        populaInput(caminhoCompleto);
    }


    public void limparDados(){
        assemblyDao.excluirTodos();;
        linhasDeMontagensDao.excluirTodos();
    }

    public List<LinhasDeMontagens> findAllAssemblysLinhasDeMontagens() {
        return linhasDeMontagensDao.findAll();
    }

}
