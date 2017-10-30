package com.example.romeg.bcarev10;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static android.widget.Toast.makeText;


public class Verexp extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    TextView tvnombreV,tvappV, tvapmV, tvgeneroV, tvedadV, tvemailV, tvfumV, tvmedV, tvcoltV, tvcolhV, tvpresV, tvpuntV, tvriskV, tvnumpacV;
    private Button b;
    private PdfPCell cell;
    private String textAnswer;
    private Image bgImage;

    private String path;
    private File dir;
    private static final String TAG = "PdfCreatorActivity";
    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;

    BaseColor myColor = WebColors.getRGBColor("#9E9E9E");
    BaseColor myColor1 = WebColors.getRGBColor("#757575");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verexp);

        String username = getIntent().getStringExtra("Username");
        String named = helper.searchname(username);
        String appd = helper.searchapp(username);
        String apmd = helper.searchapm(username);
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
        String numpacd = helper.searchnumpac(username);


        tvnombreV = (TextView) findViewById(R.id.tvnomExp);
        tvnombreV.setText(named);
        tvappV = (TextView) findViewById(R.id.tvappExp);
        tvappV.setText(appd);
        tvapmV = (TextView) findViewById(R.id.tvapmExp);
        tvapmV.setText(apmd);

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
        tvnumpacV = (TextView) findViewById(R.id.tvnumpExp);
        tvnumpacV.setText(numpacd);


    }


    public void onVerClick(View v) {
        if (v.getId() == R.id.btneditarexp) {
            String str = getIntent().getStringExtra("Username");
            String named = helper.searchname(str);
            String appd = helper.searchapp(str);
            String apmd = helper.searchapm(str);
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
            String numpacd = helper.searchnumpac(str);

            Intent i = new Intent(Verexp.this, Editarexped.class);
            i.putExtra("Username", str);
            i.putExtra("Name", named);
            i.putExtra("App", appd);
            i.putExtra("Apm", apmd);
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
            i.putExtra("Numpac", numpacd);
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
                createPdfWrapper();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

    }

    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        String username = getIntent().getStringExtra("Username");
        String named = helper.searchname(username);
        String appd = helper.searchapp(username);
        String apmd = helper.searchapm(username);
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
        String numpacd = helper.searchnumpac(username);

        String str = getIntent().getStringExtra("Username");
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"Expediente.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        try {

            Log.e("PDFCreator", "PDF Path: " + path);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            //open the document
            document.open();
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
                bgImage = Image.getInstance(bitmapdata);
                bgImage.setAbsolutePosition(330f, 642f);
                cell.addElement(bgImage);
                pt.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                cell.addElement(new Paragraph("N° Paciente: " + numpacd));
                cell.addElement(new Paragraph("Nombre: " + named + " " + appd + " " + apmd));

                cell.addElement(new Paragraph("Edad: " + edadd));
                cell.addElement(new Paragraph("Email: " + emaild));
                cell.addElement(new Paragraph("Fecha: " + sdf.format(Calendar.getInstance().getTime())));
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
                PdfPTable table = new PdfPTable(6);

                float[] columnWidth = new float[]{6, 30, 30, 20, 20, 30};
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
                cell.setColspan(6);

                cell.setBackgroundColor(myColor1);

                cell = new PdfPCell(new Phrase("#"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Colesterol T"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Colesterol HDL"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("¿Fumador?"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("?Diabetes?"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("BP Sistólica"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);

                //table.setHeaderRows(3);
                cell = new PdfPCell();
                cell.setColspan(6);

                for (int i = 1; i <= 2; i++) {
                    table.addCell(String.valueOf(i));
                    table.addCell(colt);
                    table.addCell(colh);
                    table.addCell(fum);
                    table.addCell(med);
                    table.addCell(presu);

                }

                PdfPTable ftable = new PdfPTable(6);
                ftable.setWidthPercentage(100);
                float[] columnWidthaa = new float[]{40, 10, 20, 10, 40, 10};
                ftable.setWidths(columnWidthaa);
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.NO_BORDER);
                pt.addCell(cell);
                cell = new PdfPCell();
                cell.setColspan(6);
                cell.setBackgroundColor(myColor1);
                cell = new PdfPCell(new Phrase("Puntaje obtenido de la calculadora de riesgo"));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);

                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);

                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);

                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);

                cell = new PdfPCell(new Phrase("Porcentaje de riesgo de padecer un ACV"));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);

                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);

                cell = new PdfPCell(new Paragraph(punt));
                cell.setBorder(Rectangle.NO_BORDER);
                ftable.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                ftable.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                ftable.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                ftable.addCell(cell);
                cell = new PdfPCell(new Paragraph(risk));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(6);
                ftable.addCell(cell);
                cell = new PdfPCell(new Paragraph(risk));
                cell.setColspan(6);
                cell.addElement(ftable);
                table.addCell(cell);
                document.add(table);
                Toast.makeText(getApplicationContext(), "El PDF se ha creado en la ruta  /Documents/Expediente.pdf", Toast.LENGTH_LONG).show();
            } catch (DocumentException de) {
                Log.e("PDFCreator", "DocumentException:" + de);
            } catch (IOException e) {
                Log.e("PDFCreator", "ioException:" + e);
            } finally {
                document.close();
                previewPdf();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"El telefono no tiene lector de documentos PDF",Toast.LENGTH_SHORT).show();
        }
    }




    public void onBackPressed() {

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
        String appd = helper.searchapp(str);
        String apmd = helper.searchapm(str);
        String numpacd = helper.searchnumpac(str);

        Intent i = new Intent(Verexp.this, Expediente.class);
        i.putExtra("Username", str);
        i.putExtra("Name", named);
        i.putExtra("App", appd);
        i.putExtra("Apm", apmd);
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
        i.putExtra("Numpac", numpacd);
        startActivity(i);
        finish();

    }


}

