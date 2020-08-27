package com.example.obligatorio.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class pConexion extends SQLiteOpenHelper {
    //Ruta donde se localizara la BDD
    private static String BDD_RUTA = "/data/data/com.example.obligatorio/databases/";
    //Nombre de la BDD
    private static String BDD_NOMBRE = "androiiddb.s3db";

    @Override public void onOpen(SQLiteDatabase db) { super.onOpen(db); db.disableWriteAheadLogging(); }


    //Para la BDD en memoria
    private SQLiteDatabase miBdd;

    private Context miContexto;
    //Elemento para acceder a los datos
    protected Cursor c;

    public pConexion(Context contexto) {
        super(contexto, BDD_NOMBRE, null, 1);
        this.miContexto = contexto;
        try{
            this.crearDataBase();
        }
        catch(Exception e){
            Log.e("Constructor: ", e.getMessage());
        }
    }

    private void crearDataBase() throws IOException{
        boolean dbExist = comprobarBaseDatos();
        if(dbExist == false){
            this.getWritableDatabase();
            try{
                copiarBaseDatos();
            }
            catch (Exception e){
                Log.e("CrearDataBase: ", e.getMessage());
            }
        }
    }

    private void copiarBaseDatos() throws IOException {
        try
        {
            InputStream myInput = miContexto.getAssets().open(BDD_NOMBRE);
            String outFileName = BDD_RUTA + BDD_NOMBRE;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while((length = myInput.read(buffer)) > 0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch(Exception ex)
        {
            Log.e("copiarBaseDatos: ", ex.getMessage());
        }

    }

    private boolean comprobarBaseDatos() {
        SQLiteDatabase checkDB = null;
        try{
            String myPath = BDD_RUTA + BDD_NOMBRE;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e){
            //No existe
        }
        if(checkDB != null){
            checkDB.close();
            return true;
        }
        return false;
    }

    private void abrirBaseDatos() throws SQLException {
        try
        {
            String myPath = BDD_RUTA + BDD_NOMBRE;
            this.miBdd = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }
        catch(Exception e)
        {
            Log.e("abrirBaseDatos: ", e.getMessage());
        }

    }

    //Cerrar la conexion con la BDD
    @Override
    public synchronized void close(){
        if(miBdd != null){
            miBdd.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //método que se utiliza para seleccionar datos

    protected void seleccionarDatos(String sql){

        try{
            this.abrirBaseDatos();
            this.c = this.miBdd.rawQuery(sql, null);
            this.c.moveToFirst();
        }
        catch (SQLException ex){
            Log.e("seleccionarDatos: ", ex.getMessage());
        }
        this.miBdd.close();
    }

    //Metodo que se utiliza para editar, ingresar o eliminar datos
    protected void ModificarDatos(String sql){

        try{
            this.abrirBaseDatos();
            this.miBdd.execSQL(sql);
        }
        catch(SQLException ex){
            Log.e("ModificarDatos: ", ex.getMessage());
        }
        this.miBdd.close();
    }
}
