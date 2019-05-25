package com.example.james.suv;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class GerarPDF extends AppCompatActivity {

    Button btnTeste;
    EditText txtTeste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_pdf);

        btnTeste=findViewById(R.id.btnTeste);
        txtTeste=findViewById(R.id.txtTeste);

        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[]permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, Integer.parseInt(STORAGE_SERVICE));
                    }
                    else{
                        savePdf();
                    }
                }
                else
                    {

                    }
            }

        });
    }

    private void savePdf() {
        Document mDoc=new Document();
        String mFileName=new SimpleDateFormat("yyyymmdd_HHmmss",Locale.getDefault()).format(System.currentTimeMillis());
        String mFilePath=Environment.getExternalStorageDirectory()+"/"+mFileName+".pdf";
        try{
            PdfWriter.getInstance(mDoc,new FileOutputStream(mFilePath));
            mDoc.open();
            String mText=txtTeste.getText().toString();
            Cliente c=new Cliente();

            mDoc.addAuthor(c.getNome());
            mDoc.add(new Paragraph(mText));
            mDoc.close();
            Toast.makeText(this,mFileName+".pdf\nSavo em \n"+mFilePath,Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
