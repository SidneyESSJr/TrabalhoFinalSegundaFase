package br.com.grupoVoid.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author maryucha
 */
public class Emprestimo {

    private Integer id;
    private Integer usuario;
    private Integer livro;
    private LocalDate dataInicio;
    private LocalDate dataEntrega;
    private double multa;
    private boolean situacao;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = LocalDate.parse(dataInicio, dtf);
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = LocalDate.parse(dataEntrega, dtf);
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public Boolean isSituacao() {

        return situacao;
    }

    public void setSituacao(Boolean situacao) {

        this.situacao = situacao;
    }

    public Integer getLivro() {
        return livro;
    }

    public void setLivro(Integer livro) {
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
        return Objects.equals(this.id, other.id);
    }

}
