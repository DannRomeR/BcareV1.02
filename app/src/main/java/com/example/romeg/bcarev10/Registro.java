package com.example.romeg.bcarev10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registro extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);

    EditText etnombre, etapp, etapm, etemail, etusuario, etcontraseña, etconfirmar, etTel, etedad, etcont1, etcont2;
    TextView gen;
    Spinner spGeneroR;
    Button btnreg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        String[] gener = {"-seleccione-","Masculino","Femenino"};
        btnreg = (Button) findViewById(R.id.btnregistrarreg);
        spGeneroR =(Spinner) findViewById(R.id.spgeneroR);
        spGeneroR.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gener));

        etnombre = (EditText) findViewById(R.id.txtnombrereg);
        etapp = (EditText) findViewById(R.id.txtappreg);
        etapm = (EditText) findViewById(R.id.txtapmreg);
        etemail = (EditText) findViewById(R.id.txtemailreg);
        etusuario = (EditText) findViewById(R.id.txtusuarioreg);
        etedad = (EditText) findViewById(R.id.txtedadreg);
        etTel = (EditText) findViewById(R.id.txtelefonoreg);
        etcont1 = (EditText) findViewById(R.id.txcont1reg);
        etcont2 = (EditText) findViewById(R.id.txcont2reg);
        etcontraseña = (EditText) findViewById(R.id.txtprimerpassreg);
        etconfirmar = (EditText) findViewById(R.id.txtsegundopassreg);
        gen = (TextView) findViewById(R.id.textView2);



        etnombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String namestr = etnombre.getText().toString();

                if(!validarNom(namestr))
                {
                    etnombre.setError( "Formato incorrecto");

                }

            }
        });

        etapp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String appstr = etapp.getText().toString();

                if (!validarNom(appstr))
                {
                    etapp.setError( "Formato incorrecto");

                }

            }
        });

        etapm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String apmstr = etapm.getText().toString();
                if (etapm.getText().length()!=0)
                {
                    if (!validarNom(apmstr))
                    {
                        etapm.setError( "Formato incorrecto");

                    }
                }
            }
        });

        etedad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String edadstr = etedad.getText().toString();
                switch (edadstr) {
                    case "0":
                        etedad.setError("Formato incorrecto");

                        break;
                    case "00":
                        etedad.setError("Formato incorrecto");

                        break;
                    case "1":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "2":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "3":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "4":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "5":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "6":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "7":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "8":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "9":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "10":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "11":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "12":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "13":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "14":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "15":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "16":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "17":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "18":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "19":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "20":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "21":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "22":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "23":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "24":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "25":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "26":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "27":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "28":
                        etedad.setError("Formato incorrecto");
                        break;
                    case "29":
                        etedad.setError("Formato incorrecto");
                        break;

                }

            }
        });

        etemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String emailstr = etemail.getText().toString();
                String em = helper.searchownemail(emailstr);

                if(!ValidacionEmail(emailstr)) {
                    etemail.setError("Formato de correo electrónico incorrecto");

                }
                else if (em.equals(emailstr)) {
                    etemail.setError("Ya existe un usuario con este email");
                }
            }
        });

        etusuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                String unamestr = etusuario.getText().toString();
                String us = helper.searchUse(unamestr);
                if (us.equals(unamestr)) {
                    etusuario.setError("Ya existe un usuario con este nombre");

                }
            }
        });

        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String telstr = etTel.getText().toString();
                switch (telstr)
                {
                    case "0":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "00000000":
                        etTel.setError("Formato incorrecto");
                        break;
                    case"0000000000":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "000":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "11111111":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "1111111111":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "22222222":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "2222222222":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "33333333":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "3333333333":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "44444444":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "4444444444":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "55555555":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "5555555555":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "66666666":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "6666666666":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "77777777":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "7777777777":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "88888888":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "8888888888":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "99999999":
                        etTel.setError("Formato incorrecto");
                        break;
                    case "9999999999":
                        etTel.setError("Formato incorrecto");
                        break;
                }
                if (telstr.length()<3 || telstr.length()>10)
                {
                    etTel.setError("Formato incorrecto");
                }

            }
        });

        etcont1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
            @Override
            public void afterTextChanged(Editable s) {
                String cont1str = etcont1.getText().toString();
                switch (cont1str)
                {
                    case "0":
                        etcont1.setError("Formato incorrecto");
                        break;
                    case "00000000":
                        etcont1.setError("Formato incorrecto");
                        break;
                    case"0000000000":
                        etcont1.setError("Formato incorrecto");
                        break;
                    case "000":
                        etcont1.setError("Formato incorrecto");
                        break;
                }
                if (cont1str.length()<8)
                {
                    etcont1.setError("Formato incorrecto");
                }
            }
        });

        etcont2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String cont2str = etcont2.getText().toString();
                switch (cont2str)
                {
                    case "0":
                        etcont2.setError("Formato incorrecto");
                        break;
                    case "00000000":
                        etcont2.setError("Formato incorrecto");
                        break;
                    case"0000000000":
                        etcont2.setError("Formato incorrecto");
                        break;
                    case "000":
                        etcont2.setError("Formato incorrecto");
                        break;
                }
                if (cont2str.length()<8)
                {
                    etcont2.setError("Formato incorrecto");

                }

            }
        });

        etcontraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass1str = etcontraseña.getText().toString();

                    if (pass1str.length()<6 || !validarPass(pass1str))
                    {
                        etcontraseña.setError("Formato de contraseña incorrecto");
                    }

            }
        });

        etconfirmar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass1str = etcontraseña.getText().toString();
                String pass2str = etconfirmar.getText().toString();

                    if (!pass2str.equals(pass1str))
                    {
                        etconfirmar.setError("Las contraseñas no coinciden");
                    }


            }
        });
    }


    public void onSignUpClick(View v)
    {

        etnombre = (EditText) findViewById(R.id.txtnombrereg);
        etapp = (EditText) findViewById(R.id.txtappreg);
        etapm = (EditText) findViewById(R.id.txtapmreg);
        etemail = (EditText) findViewById(R.id.txtemailreg);
        etusuario = (EditText) findViewById(R.id.txtusuarioreg);
        etedad = (EditText) findViewById(R.id.txtedadreg);
        etTel = (EditText) findViewById(R.id.txtelefonoreg);
        etcont1 = (EditText) findViewById(R.id.txcont1reg);
        etcont2 = (EditText) findViewById(R.id.txcont2reg);
        etcontraseña = (EditText) findViewById(R.id.txtprimerpassreg);
        etconfirmar = (EditText) findViewById(R.id.txtsegundopassreg);


        String namestr = etnombre.getText().toString();
        String appstr = etapp.getText().toString();
        String apmstr = etapm.getText().toString();

            String edadstr = etedad.getText().toString();
            String emailstr = etemail.getText().toString();
            String unamestr = etusuario.getText().toString();
            /*String em = helper.searchownemail(emailstr);
            String us = helper.searchUse(unamestr);*/
            String telstr = etTel.getText().toString();
            String cont1str = etcont1.getText().toString();
            String cont2str = etcont2.getText().toString();
            String pass1str = etcontraseña.getText().toString();
            String pass2str = etconfirmar.getText().toString();

           String genero = (spGeneroR.getSelectedItem().toString());

            if (v.getId() == R.id.btnregistrarreg)
            {


                try {
                    if (namestr.isEmpty() || appstr.isEmpty() || apmstr.isEmpty() || emailstr.isEmpty() || unamestr.isEmpty() || pass1str.isEmpty() || pass2str.isEmpty() || edadstr.isEmpty() || telstr.isEmpty()) {
                        Toast.makeText(this, "Los campos marcados con '*' son obligatorios ",
                                Toast.LENGTH_SHORT).show();
                        if (namestr.isEmpty()) {
                            etnombre.setError("Complete este campo");
                        }
                        if (appstr.isEmpty()) {
                            etapp.setError("Complete este campo");
                        }
                        if (apmstr.isEmpty()) {
                            etapm.setError("Complete este campo");
                        }
                        if (edadstr.isEmpty()) {
                            etedad.setError("Complete este campo");
                        }
                        if (emailstr.isEmpty()) {
                            etemail.setError("Complete este campo");
                        }
                        if (unamestr.isEmpty()) {
                            etusuario.setError("Complete este campo");
                        }
                        if (telstr.isEmpty()) {
                            etTel.setError("Complete este campo");
                        }
                        if (cont1str.isEmpty()) {
                            etcont1.setError("Complete este campo");
                        }
                        if (cont2str.isEmpty()) {
                            etcont2.setError("Complete este campo");
                        }
                        if (pass1str.isEmpty()) {
                            etcontraseña.setError("Complete este campo");
                        }
                        if (pass2str.isEmpty()) {
                            etconfirmar.setError("Complete este campo");
                        }

                    } else//***********************************REGISTRO****************************/
                        {
                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                            dialogo1.setTitle("Politica de privacidad");
                            dialogo1.setMessage("El presente Política de Privacidad establece los términos en que B-Care" +
                                    " usa y protege la información que es proporcionada por sus usuarios al momento de utilizar" +
                                    " su sitio web. Esta compañía está comprometida con la seguridad de los datos de sus usuarios." +
                                    " Cuando le pedimos llenar los campos de información personal con la" +
                                    " cual usted pueda ser identificado, lo hacemos asegurando que sólo se " +
                                    "empleará de acuerdo con los términos de este documento. Sin embargo esta" +
                                    " Política de Privacidad puede cambiar con el tiempo o ser actualizada por" +
                                    " lo que le recomendamos y enfatizamos revisar continuamente esta página para" +
                                    " asegurarse que está de acuerdo con dichos cambios." +
                                    "Información que es recogida\n" +
                                    "\n" +
                                    "Nuestro sitio web podrá recoger información personal por ejemplo: Nombre," +
                                    "  información de contacto como  su dirección de correo electrónica e información demográfica." +
                                    " Así mismo cuando sea necesario podrá ser requerida información específica para" +
                                    " procesar algún pedido o realizar una entrega o facturación.\n" +
                                    "\n" +
                                    "Uso de la información recogida\n " +
                                    "\nNuestro sitio web emplea la información con" +
                                    " el fin de proporcionar el mejor servicio posible, particularmente para mantener un registro" +
                                    " de usuarios, de pedidos en caso que aplique, y mejorar nuestros productos y servicios. " +
                                    " Es posible que sean enviados correos electrónicos periódicamente a través de nuestro sitio " +
                                    "con ofertas especiales, nuevos productos y otra información publicitaria que consideremos relevante " +
                                    "para usted o que pueda brindarle algún beneficio, estos correos electrónicos serán enviados a la" +
                                    " dirección que usted proporcione y podrán ser cancelados en cualquier momento.\n" +
                                    "\n" +
                                    "B-Care está altamente comprometido para cumplir con el compromiso de mantener su información " +
                                    "segura. Usamos los sistemas más avanzados y los actualizamos constantemente para asegurarnos" +
                                    " que no exista ningún acceso no autorizado.\n" +
                                    "\nCookies\n" +
                                    "\n" +
                                    "Una cookie se refiere a un fichero que es enviado con la finalidad de solicitar permiso " +
                                    "para almacenarse en su ordenador, al aceptar dicho fichero se crea y la cookie sirve " +
                                    "entonces para tener información respecto al tráfico web, y también facilita las futuras" +
                                    " visitas a una web recurrente. Otra función que tienen las cookies es que con ellas las" +
                                    " web pueden reconocerte individualmente y por tanto brindarte el mejor servicio " +
                                    "personalizado de su web.\n" +
                                    "\n" +
                                    "Nuestro sitio web emplea las cookies para poder identificar las páginas " +
                                    "que son visitadas y su frecuencia. Esta información es empleada únicamente para análisis" +
                                    " estadístico y después la información se elimina de forma permanente. Usted puede eliminar" +
                                    " las cookies en cualquier momento desde su ordenador. Sin embargo las cookies ayudan a " +
                                    "proporcionar un mejor servicio de los sitios web, estás no dan acceso a información de su " +
                                    "ordenador ni de usted, a menos de que usted así lo quiera y la proporcione directamente," +
                                    " link . Usted puede aceptar o negar el uso de cookies, sin embargo la mayoría de navegadores" +
                                    " aceptan cookies automáticamente pues sirve para tener un mejor servicio web. " +
                                    "También usted puede cambiar la configuración de su ordenador para declinar las cookies." +
                                    " Si se declinan es posible que no pueda utilizar algunos de nuestros servicios.\n" +
                                    "\n" +
                                    "Enlaces a Terceros\n" +
                                    "\nEste sitio web pudiera contener en laces a otros sitios que pudieran ser de su interés." +
                                    " Una vez que usted de clic en estos enlaces y abandone nuestra página, ya no tenemos control" +
                                    " sobre al sitio al que es redirigido y por lo tanto no somos responsables de los términos o" +
                                    " privacidad ni de la protección de sus datos en esos otros sitios terceros. Dichos sitios " +
                                    "están sujetos a sus propias políticas de privacidad por lo cual es recomendable que los " +
                                    "consulte para confirmar que usted está de acuerdo con estas.\n" +
                                    "\n" +
                                    "Control de su información personal\n" +
                                    "\n" +
                                    "En cualquier momento usted puede restringir la recopilación o el uso de la información" +
                                    " personal que es proporcionada a nuestro sitio web.  Cada vez que se le solicite rellenar" +
                                    " un formulario, como el de alta de usuario, puede marcar o desmarcar la opción de recibir " +
                                    "información por correo electrónico.  En caso de que haya marcado la opción de recibir " +
                                    "nuestro boletín o publicidad usted puede cancelarla en cualquier momento.\n" +
                                    "\n" +
                                    "B-Care Se reserva el derecho de cambiar los términos de la presente Política de" +
                                    " Privacidad en cualquier momento.");
                            dialogo1.setCancelable(false);
                            dialogo1.setNegativeButton("No acepto", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo1, int id) {
                                    finish();
                                }
                            });
                            dialogo1.setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo1, int id) {
                                    String namestr = etnombre.getText().toString();
                                    String appstr = etapp.getText().toString();
                                    String apmstr = etapm.getText().toString();

                                    String edadstr = etedad.getText().toString();
                                    String emailstr = etemail.getText().toString();
                                    String unamestr = etusuario.getText().toString();

                                    String telstr = etTel.getText().toString();
                                    String cont1str = etcont1.getText().toString();
                                    String cont2str = etcont2.getText().toString();
                                    String pass1str = etcontraseña.getText().toString();

                                    String genero = (spGeneroR.getSelectedItem().toString());
                                    /*int edadint = Integer.parseInt(edadstr);
                                    int telint = Integer.parseInt(telstr);
                                    int cont1int = Integer.parseInt(cont1str);
                                    int cont2int = Integer.parseInt(cont2str);
                                    int numa = (int) (Math.random() * 999) + 1;*/

                                    String numastr = String.valueOf((int) (Math.random() * 999) + 1);
                                    String nomS = namestr.substring(0, 1);
                                    String appS = appstr.substring(0, 1);
                                    String apmS = apmstr.substring(0, 1);
                                    String numPac = nomS + appS + apmS + edadstr + numastr;

                                    datos(namestr, appstr, apmstr, edadstr, emailstr, unamestr, telstr, cont1str, cont2str, pass1str
                                            , genero, numPac);
                                }
                            });

                            dialogo1.show();


                        }

                }
                catch (Exception e) {
                            Toast pass = Toast.makeText(Registro.this, "Operacion no exitosa", Toast.LENGTH_LONG);
                            pass.show();
                    finish();
                }

            }
    }


public void datos(String namestr, String appstr, String apmstr, String edadstr, String emailstr, String unamestr
        ,String telstr, String cont1str, String cont2str, String pass1str, String genero, String numPac)
{
    try
    {


        //insert the details in database
        Contact c = new Contact();
        c.setName(namestr);
        c.setApp(appstr);
        c.setApm(apmstr);
        c.setEmail(emailstr);
        c.setUname(unamestr);
        c.setPass(pass1str);
        c.setEdad(Integer.parseInt(edadstr));
        c.setTel(Integer.parseInt(telstr));
        c.setCont1(Integer.parseInt(cont1str));
        c.setCont2(Integer.parseInt(cont2str));
        c.setGenero(genero);

        c.setFum("Sin dato");
        c.setMed("Sin dato");
        c.setColt("Sin dato");
        c.setColh("Sin dato");
        c.setPresu(120);
        c.setPunt(120);
        c.setRisk(120);
        c.setNumPac(numPac);
        helper.insertContact(c);

        Toast pass = Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_LONG);
        pass.show();

        Intent i = new Intent(Registro.this, Bienvenido.class);
        startActivity(i);
    }
    catch (Exception e)
    {
        Toast pass = Toast.makeText(Registro.this, "Operacion no exitosa", Toast.LENGTH_LONG);
        pass.show();
        finish();
    }

}



        public boolean ValidacionEmail(String email)
        {
            String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(email);


            return matcher.matches();
        }
        public boolean validarNom(String name)
        {
            Pattern pattern;
            Matcher matcher;
            final String NOMBRE_PATTERN = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+$";
            pattern = Pattern.compile(NOMBRE_PATTERN);
            matcher = pattern.matcher(name);

            return matcher.matches();
        }
        public boolean validarPass(String pass)
        {
            // ^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$
            // ^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$
            Pattern pattern;
            Matcher matcher;
            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!.-_])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(pass);

            return matcher.matches();
        }

    public void onBackPressed()
    {
        Intent i = new Intent(Registro.this, MainActivity.class);
        startActivity(i);
    }

    }







