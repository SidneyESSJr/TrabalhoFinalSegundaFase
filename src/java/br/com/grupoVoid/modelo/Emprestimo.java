package br.com.grupoVoid.modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author maryucha
 */
public class Emprestimo {

    private Integer id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataInicio;
    private LocalDate dataEntrega;
    private double multa;
    private boolean situacao;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

<<<<<<< HEAD
     public boolean isSituacao() {
=======
    public Boolean isSituacao() {
>>>>>>> origin/main
        return situacao;
    }

<<<<<<< HEAD
    public void setSituacao(boolean situacao) {
=======
    public void setSituacao(Boolean situacao) {
>>>>>>> origin/main
        this.situacao = situacao;
    }
    
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emprestimo other = (Emprestimo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
