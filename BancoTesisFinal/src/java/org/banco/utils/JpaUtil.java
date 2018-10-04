package org.banco.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rjsan
 */
public class JpaUtil {

    public static EntityManagerFactory getEMF() {
        return Persistence.createEntityManagerFactory("BancoTesisFinalPU");
    }
}