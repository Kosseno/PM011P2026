package com.example.app01.Configuracion;

public class Transacciones
{
    //DB name
    public static final String dbname = "DBPM01";

    public static final int dbversion = 1;

    //DB tables
    public static final String tbpersons = "personas";
    //Table Person Fields
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";

    public static final String foto = "foto";

    //DDL Create
    public static final String CreateTablePerson = "CREATE TABLE " + tbpersons + " ( " +
            id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            nombres + " TEXT, " +
            apellidos + " TEXT, " +
            edad + " INTEGER, " +
            correo + " TEXT, " +
            foto + " TEXT )";

    // Consulta DDL para eliminación de tabla
    public static final String DropTablePersons = "DROP TABLE IF EXISTS " + tbpersons;

    // Consulta DML para selección de tabla
    public static final String SelectTablePerson = "SELECT * FROM " + tbpersons;
}