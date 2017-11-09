package com.example.romeg.bcarev10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by romeg on 25/09/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_APP = "app";
    private static final String COLUMN_APM = "apm";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EDAD = "edad";
    private static final String COLUMN_TEL = "tel";
    private static final String COLUMN_CONT1 = "cont1";
    private static final String COLUMN_CONT2 = "cont2";
    private static final String COLUMN_GENE = "gen";
    private static final String COLUMN_FUM = "fum";
    private static final String COLUMN_MED = "med";
    private static final String COLUMN_COLT = "colt";
    private static final String COLUMN_COLH = "colh";
    private static final String COLUMN_PRESU = "presu";
    private static final String COLUMN_PUNT = "punt";
    private static final String COLUMN_RISK = "risk";
    private static final String COLUMN_NUMPAC="numpac";


    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null, name text not null, app text not null, apm text not null, email text not null, uname text not null, pass text not null, edad integer null, tel integer null, cont1 integer null, cont2 integer null, gen text not null, fum text not null, med text not null, colt text not null, colh text not null, presu integer not null, punt integer not null, risk integer not null, numpac text not null);";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_APP, c.getApp());
        values.put(COLUMN_APM, c.getApm());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_EDAD, c.getEdad());
        values.put(COLUMN_TEL, c.getTel());
        values.put(COLUMN_CONT1, c.getCont1());
        values.put(COLUMN_CONT2, c.getCont2());
        values.put(COLUMN_GENE, c.getGenero());

        values.put(COLUMN_FUM, c.getFum());
        values.put(COLUMN_MED, c.getMed());
        values.put(COLUMN_COLT, c.getColt());
        values.put(COLUMN_COLH, c.getColh());
        values.put(COLUMN_PRESU, c.getPresu());
        values.put(COLUMN_PUNT, c.getPunt());
        values.put(COLUMN_RISK, c.getRisk());

        values.put(COLUMN_NUMPAC, c.getNumPac());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchbyemail(String email)
    {
        db = this.getReadableDatabase();
        String query = "select email, uname from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(email))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchownemail(String email)
    {
        db = this.getReadableDatabase();
        String query = "select email from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        a = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(email))
                {
                    return a;
                }
            }
            while (cursor.moveToNext());
        }
        return a;
    }

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, pass from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                            break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchUse(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        a = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                   return a;
                }
            }
            while (cursor.moveToNext());
        }
        return a;
    }

    public String searchname(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, name from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchapp(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, app from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchapm(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, apm from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchedad(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, edad from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchemail(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, email from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchtel(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, tel from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchcont1(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, cont1 from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchcont2(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, cont2 from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchgen (String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, gen from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchfum (String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, fum from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchmed(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, med from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchcolt(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, colt from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchcolh(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, colh from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchpresure(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, presu from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchpunt(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, punt from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchrisk(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, risk from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String searchnumpac(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, numpac from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public String insertCalcu (String useCal, String fum, String med, String colt, String colh, int presure, int punt, int risk )
    {
        String Mensaje = "";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("fum",fum);
        contenedor.put("med",med);
        contenedor.put("colt",colt);
        contenedor.put("colh",colh);
        contenedor.put("presu",presure);
        contenedor.put("punt",punt);
        contenedor.put("risk",risk);
        int cantidad = database.update("contacts", contenedor,"uname='"+useCal+"'",null);
        if (cantidad != 0){
            Mensaje="La operacion se ha realizado exitosamente";
        }
        else
        {
            Mensaje = "Operación no exitosa";
        }

        return Mensaje;
    }

    public String actualizarExpediente (String use, String name, String app, String apm,  int age, String email, int presure, String genero, String fum, String med, String colt, String colh, int punt, int risk)
    {
        String Mensaje = "";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("name",name);
        contenedor.put("app",app);
        contenedor.put("apm",apm);
        contenedor.put("edad",age);
        contenedor.put("email",email);
        contenedor.put("gen",genero);
        contenedor.put("fum",fum);
        contenedor.put("med",med);
        contenedor.put("colt",colt);
        contenedor.put("colh",colh);
        contenedor.put("presu",presure);
        contenedor.put("punt",punt);
        contenedor.put("risk",risk);
        int cantidad = database.update("contacts", contenedor,"uname='"+use+"'",null);
        if (cantidad != 0){
            Mensaje="La operacion se ha realizado exitosamente";
        }
        else
        {
            Mensaje = "Operación no exitosa";
        }

        return Mensaje;
    }

    public String actualizarData(String use, String uname, String name, String app, String apm,  int edad, String email, int tel, int cont1, int cont2,String gener)
    {
        String Mensaje = "";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("name",name);
        contenedor.put("app",app);
        contenedor.put("apm",apm);
        contenedor.put("email",email);
        contenedor.put("uname",uname);
        contenedor.put("edad",edad);
        contenedor.put("tel",tel);
        contenedor.put("cont1",cont1);
        contenedor.put("cont2",cont2);
        contenedor.put("gen",gener);
        int cantidad = database.update("contacts", contenedor,"uname='"+use+"'",null);
        if (cantidad != 0){
            Mensaje="La operacion se ha realizado exitosamente";
        }
        else
        {
            Mensaje = "Operación no exitosa";
        }

        return Mensaje;
    }

    public String actualizaPass(String userc, String pass)
    {
        String Mensaje = "";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("pass", pass);
        int cantidad = database.update("contacts", contenedor, "uname='"+userc+"'",null);
        if (cantidad != 0){
            Mensaje = "La operacion se ha realizado exitosamente";
        }
        else
        {
            Mensaje = "Operación no exitosa";
        }
        return Mensaje;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query = "DROP TABLE IF EXISTS contacts";
        db.execSQL(query);
        this.onCreate(db);

    }
}
