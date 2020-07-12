package br.univille.dacs2020.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(value = TemporalType.DATE)
    private Date data;
    private String status;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Paciente paciente;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONSULTA_ID")
    private List<ProcedimentoRealizado> listaProcedimentos = new ArrayList();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}