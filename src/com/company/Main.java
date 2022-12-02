/*
 * Participantes:
 * Wanderson da Silva Junior RA: 202217583
 * Geovana Oliveira RA:202218173
 *  Fábio Henrique Farias da Silva RA: 202218886
 * João Vitor Vieira Claro RA:202216972
 */

package com.company;

import repository.impl.JDBCGarcomRepositoryIpml;
import repository.impl.JDBCMesaRepositoryIpml;


public class Main {

    public static void main(String[] args) {
        try{
            new Menu(new JDBCGarcomRepositoryIpml(), new JDBCMesaRepositoryIpml()).menu();
        }catch (Throwable e ){
            e.printStackTrace();
        }

    }

}
