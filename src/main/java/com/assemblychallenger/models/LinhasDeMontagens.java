package com.assemblychallenger.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;



@Table(name = "montagem")
@Entity(name = "LinhasDeMontagens")
public class LinhasDeMontagens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String linhaMontagem;
    @OneToMany(mappedBy = "linhasDeMontagens")
    private List<Assembly> listAssemblys;
    public List<Assembly> getAssemblys() {
        return listAssemblys;
    }
    public void setAssemblys(List<Assembly> listAssemblys) {
        this.listAssemblys = listAssemblys;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLinhaMontagem() {
        return linhaMontagem;
    }
    public void setLinhaMontagem(String linhaMontagem) {
        this.linhaMontagem = linhaMontagem;
    }

    @Override
    public String toString() {
        return "LinhasDeMontagens{" +"\n"+
                "id=" + id +
                ", linhaMontagem='" + linhaMontagem +
                ", listAssemblys=" + listAssemblys +"\n"+
                '}'+"\n \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinhasDeMontagens that = (LinhasDeMontagens) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
