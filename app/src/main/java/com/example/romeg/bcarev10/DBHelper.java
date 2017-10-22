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
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EDAD = "edad";
    private static final String COLUMN_TEL = "tel";
    private static final String COLUMN_CONT1 = "cont1";
    private static final String COLUMN_CONT2 = "cont2";
    private static final String COLUMN_GENE = "gen";


    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null, " +
            "name text not null, email text not null, uname text not null, pass text not null, edad integer null, tel integer null, cont1 integer null, cont2 integer null, gen text not null);";


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
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_EDAD, c.getEdad());
        values.put(COLUMN_TEL, c.getTel());
        values.put(COLUMN_CONT1, c.getCont1());
        values.put(COLUMN_CONT2, c.getCont2());
        values.put(COLUMN_GENE, c.getGenero());

        db.insert(TABLE_NAME, null, values);
        db.close();
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



    public String actualizarData(String use, String uname,String name, int edad, String email, int tel, int cont1, int cont2,String gener)
    {
        String Mensaje = "";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("name",name);
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
