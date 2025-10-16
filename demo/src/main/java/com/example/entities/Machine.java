package com.example.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@NamedNativeQuery(name = "findBetweenDateNative", query = "select * from machine where dateAchat between :d1 and :d2", resultClass = Machine.class)
@NamedQuery(name = "findBetweenDate", query = "from Machine where dateAchat between :d1 and :d2")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @ManyToOne
    private Salle salle;

    Machine() {
    }

    public Machine(String ref, Date dateAchat) {
        this.ref = ref;
        this.dateAchat = dateAchat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setSalle(Salle byId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSalle'");
    }

    public String getRef() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRef'");
    }

    public String getDateAchat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDateAchat'");
    }
}