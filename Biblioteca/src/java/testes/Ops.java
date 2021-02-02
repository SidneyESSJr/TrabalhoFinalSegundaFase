/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author maryu
 */
public class Ops {

    public static void main(String[] args) {
        System.out.println("opa");
        EntityManagerFactory emF = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emF.createEntityManager();

        em.close();
    }
}
