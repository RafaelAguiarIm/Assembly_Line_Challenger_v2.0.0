package com.assemblychallenger.beans;

import com.assemblychallenger.business.AssemblyBO;
import com.assemblychallenger.models.LinhasDeMontagens;
import jakarta.inject.Named;
import com.assemblychallenger.models.Assembly;
import org.primefaces.model.file.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named
@ManagedBean
@RequestScoped
public class AssemblyBean {

    public AssemblyBean() {
        preRenderView();
    }

    public void init() {
        preRenderView();
    }
    public void preRenderView() {linhasDeMontagens = assemblyBO.findAllAssemblysLinhasDeMontagens();}

    Assembly assembly = new Assembly();
    List<LinhasDeMontagens> linhasDeMontagens = new ArrayList<>();

    AssemblyBO assemblyBO = new AssemblyBO();

    public List<LinhasDeMontagens> getLinhasDeMontagens() { return linhasDeMontagens; }
    public void setLinhasDeMontagens(List<LinhasDeMontagens> linhasDeMontagens) { this.linhasDeMontagens = linhasDeMontagens; }
    public Assembly getAssembly() { return assembly; }


    public String limparDados(){
        assemblyBO.limparDados();
        preRenderView();
        return "/index.xhtml?faces-redirect=true";
    }

    private UploadedFile file;
    public UploadedFile getFile() {
        return file;
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String upload() {
        try {
            if (file == null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Selecione um arquivo para fazer o upload."));
                return "";
            } String fileName = file.getFileName();
            if (!fileName.endsWith(".txt")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Selecione um arquivo do tipo TXT."));
                return "";
            }
            assemblyBO.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "/index.xhtml?faces-redirect=true";
    }
}
