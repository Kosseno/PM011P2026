package com.example.app01.Configuracion;

public class Transacciones
{
    //DB name
    public static final String dbname = "DBPM01";

    //DB tables
    public static final String tbpersonas = "personas";
    //Table Person Fields
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";

    public static final String foto = "foto";

    //DDL
    public static final String CreateTablePerson = "CREATE TABLE " + tbpersonas + " ( " +
            id + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            nombres + " TEXT , " +
            apellidos + " TEXT , " +
            edad + " INTEGER , " +
            correo + " TEXT , " +
            foto + " TEXT , )";
}
