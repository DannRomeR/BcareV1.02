package com.example.romeg.bcarev10;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Verexp extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    TextView tvnombreV, tvgeneroV, tvedadV, tvemailV, tvfumV, tvmedV, tvcoltV, tvcolhV, tvpresV, tvpuntV, tvriskV;

    private Button b;
    private PdfPCell cell;
    private String textAnswer;
    private Image bgImage;
    ListView list;
    private String path;
    private File dir;
    private File file;

    BaseColor myColor = WebColors.getRGBColor("#9E9E9E");
    BaseColor myColor1 = WebColors.getRGBColor("#757575");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verexp);

        String username = getIntent().getStringExtra("Username");
        String named = helper.searchname(username);
        String edadd = helper.searchedad(username);
        String emaild = helper.searchemail(username);
        String gend = helper.searchgen(username);
        String fum = helper.searchfum(username);
        String med = helper.searchmed(username);
        String colt = helper.searchcolt(username);
        String colh = helper.searchcolh(username);
        String presu = helper.searchpresure(username);
        String punt = helper.searchpunt(username);
        String risk = helper.searchrisk(username);


        tvnombreV = (TextView) findViewById(R.id.tvnomExp);
        tvnombreV.setText(named);
        tvgeneroV = (TextView) findViewById(R.id.tvgeneroE);
        tvgeneroV.setText(gend);
        tvedadV = (TextView) findViewById(R.id.tvageE);
        tvedadV.setText(edadd);
        tvemailV = (TextView) findViewById(R.id.tvemailE);
        tvemailV.setText(emaild);
        tvfumV = (TextView) findViewById(R.id.tvfumE);
        tvfumV.setText(fum);
        tvmedV = (TextView) findViewById(R.id.tvdiaE);
        tvmedV.setText(med);
        tvcoltV = (TextView) findViewById(R.id.tvcoltE);
        tvcoltV.setText(colt);
        tvcolhV = (TextView) findViewById(R.id.tvcolh);
        tvcolhV.setText(colh);
        tvpresV = (TextView) findViewById(R.id.tvpresu);
        tvpresV.setText(presu);
        tvpuntV = (TextView) findViewById(R.id.tvpunt);
        tvpuntV.setText(punt);
        tvriskV = (TextView) findViewById(R.id.tvrisk);
        tvriskV.setText(risk);


        b = (Button) findViewById(R.id.btnexportarexp);
        list = (ListView) findViewById(R.id.list);


    }

    public void onVerClick(View v)  {
        if(v.getId()==R.id.btneditarexp)
        {
            String str = getIntent().getStringExtra("Username");
            String named = helper.searchname(str);
            String edadd = helper.searchedad(str);
            String emaild = helper.searchemail(str);
            String gend = helper.searchgen(str);
            String fum = helper.searchfum(str);
            String med = helper.searchmed(str);
            String colt = helper.searchcolt(str);
            String colh = helper.searchcolh(str);
            String presu = helper.searchpresure(str);
            String punt = helper.searchpunt(str);
            String risk = helper.searchrisk(str);

            Intent i = new Intent(Verexp.this, Editarexped.class);
            i.putExtra("Username", str);
            i.putExtra("Name", named);
            i.putExtra("Edad", edadd);
            i.putExtra("Email", emaild);
            i.putExtra("Gen", gend);
            i.putExtra("Fum", fum);
            i.putExtra("Med", med);
            i.putExtra("Colt", colt);
            i.putExtra("Colh", colh);
            i.putExtra("Presu", presu);
            i.putExtra("Punt", punt);
            i.putExtra("Risk", risk);
            startActivity(i);


        }
        else if(v.getId() == R.id.btnexportarexp)
        {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Trinity/PDF Files";
            dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                createPDF();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//getting files from directory and display in listview
        try {

            ArrayList<String> FilesInFolder = GetFiles("/sdcard/Trinity/PDF Files");
            if (FilesInFolder.size() != 0)
                list.setAdapter(new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, FilesInFolder));

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    // Clicking on items
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(DirectoryPath);

        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0)
            return null;
        else {
            for (int i = 0; i < files.length; i++)
                MyFiles.add(files[i].getName());
        }

        return MyFiles;
    }


    public void createPDF() throws FileNotFoundException, DocumentException {

        //create document file
        Document doc = new Document();
        try {

            Log.e("PDFCreator", "PDF Path: " + path);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File(dir, "Expediente.pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();
//create table
            PdfPTable pt = new PdfPTable(3);
            pt.setWidthPercentage(100);
            float[] fl = new float[]{20, 45, 35};
            pt.setWidths(fl);
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);

            //set drawable in cell
            Drawable myImage = Verexp.this.getResources().getDrawable(R.drawable.doctor);
            Bitmap bitmap = ((BitmapDrawable) myImage).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapdata = stream.toByteArray();
            try {
                String username = getIntent().getStringExtra("Username");
                String name = helper.searchname(username);
                String edadd = helper.searchedad(username);
                String emaild = helper.searchemail(username);
                String gend = helper.searchgen(username);
                String fum = helper.searchfum(username);
                String med = helper.searchmed(username);
                String colt = helper.searchcolt(username);
                String colh = helper.searchcolh(username);
                String presu = helper.searchpresure(username);
                String punt = helper.searchpunt(username);
                String risk = helper.searchrisk(username);
                String fecha = sdf.format(Calendar.getInstance().getTime());

                bgImage = Image.getInstance(bitmapdata);
                bgImage.setAbsolutePosition(330f, 642f);
                cell.addElement(bgImage);
                pt.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                cell.addElement(new Paragraph("Nombre: " + name));

                cell.addElement(new Paragraph("Correo: " + emaild));
                cell.addElement(new Paragraph("Fecha: " + fecha));
                pt.addCell(cell);
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.NO_BORDER);
                pt.addCell(cell);

                PdfPTable pTable = new PdfPTable(1);
                pTable.setWidthPercentage(100);
                cell = new PdfPCell();
                cell.setColspan(1);
                cell.addElement(pt);
                pTable.addCell(cell);
                PdfPTable table = new PdfPTable(10);

                float[] columnWidth = new float[]{6, 30, 15, 20, 20, 20, 20, 20, 20, 20};
                table.setWidths(columnWidth);


                cell = new PdfPCell();


                cell.setBackgroundColor(myColor);
                cell.setColspan(6);
                cell.addElement(pTable);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(" "));
                cell.setColspan(6);
                table.addCell(cell);
                cell = new PdfPCell();
                cell.setColspan(10);

                cell.setBackgroundColor(myColor1);

                cell = new PdfPCell(new Phrase("#"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Genero"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Edad"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Fumador"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Diabetes"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Colesterol Total"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Colesterol HDL"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("BP sistólica"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Puntuacion"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Porcentaje de riesgo %"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);

                //table.setHeaderRows(3);
                cell = new PdfPCell();
                cell.setColspan(10);

                for (int i = 1; i <= 1; i++) {
                    table.addCell(String.valueOf(i));
                    table.addCell(gend);
                    table.addCell(edadd);
                    table.addCell(fum);
                    table.addCell(med);
                    table.addCell(colt);
                    table.addCell(colh);
                    table.addCell(presu);
                    table.addCell(punt);
                    table.addCell(risk);
                }

                doc.add(table);
                Toast.makeText(getApplicationContext(), "Acceda a la ruta para ver: Trinity/PDF Files/Expediente.pdf", Toast.LENGTH_LONG).show();

            } catch (DocumentException de) {
                Log.e("PDFCreator", "DocumentException:" + de);
            } catch (IOException e) {
                Log.e("PDFCreator", "ioException:" + e);
            } finally {
                doc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed()
    {

        String str = getIntent().getStringExtra("Username");
        String userna = helper.searchUse(str);
        String named = helper.searchname(str);
        String edadd = helper.searchedad(str);
        String emaild = helper.searchemail(str);
        String teld = helper.searchtel(str);
        String cont1d = helper.searchcont1(str);
        String cont2d = helper.searchcont2(str);
        String gend = helper.searchgen(str);
        String fum = helper.searchfum(str);
        String med = helper.searchmed(str);
        String colt = helper.searchcolt(str);
        String colh = helper.searchcolh(str);
        String presu = helper.searchpresure(str);
        String punt = helper.searchpunt(str);
        String risk = helper.searchrisk(str);

        Intent i = new Intent(Verexp.this, Expediente.class);
        i.putExtra("Username", str);
        i.putExtra("Name", named);
        i.putExtra("Edad", edadd);
        i.putExtra("Email", emaild);
        i.putExtra("Tel", teld);
        i.putExtra("Cont1", cont1d);
        i.putExtra("Cont2", cont2d);
        i.putExtra("Gen", gend);
        i.putExtra("Fum", fum);
        i.putExtra("Med", med);
        i.putExtra("Colt", colt);
        i.putExtra("Colh", colh);
        i.putExtra("Presu", presu);
        i.putExtra("Punt", punt);
        i.putExtra("Risk", risk);
        startActivity(i);
        finish();

    }


}
