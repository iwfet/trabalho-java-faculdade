/*
 * Participantes:
 * Wanderson da Silva Junior RA: 202217583
 * Geovana Oliveira RA:202218173
 *  Fábio Henrique Farias da Silva RA: 202218886
 * João Vitor Vieira Claro RA:202216972
 */

package com.company;

import enun.TipoSexo;
import enun.TipoSituacaoMesa;
import molde.Garcom;
import molde.Mesa;
import repository.impl.JDBCGarcomRepositoryIpml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String[] args) {
        try{
            new Menu(new JDBCGarcomRepositoryIpml()).menu();
        }catch (Throwable e ){
            e.printStackTrace();
        }

    }

}
