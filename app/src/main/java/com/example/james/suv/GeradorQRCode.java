package com.example.james.suv;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.Cliente.Perfil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GeradorQRCode extends Fragment {
    EditText edtTexto;
    TextView btnGerar,btnVoltar;
    ImageView ivQRCode;
    Cliente cli=new Cliente();
    ProgressBar progressBar;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_gerador_qrcode, container, false);

        btnVoltar=rootView.findViewById(R.id.btnVoltar);
        //inicializarComponentes();
        //clickButton();
        edtTexto=rootView.findViewById(R.id.edtTexto);
        btnGerar=rootView.findViewById(R.id.btnGerar);
        ivQRCode=rootView.findViewById(R.id.ivQRCode);
        progressBar=rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getActivity(), Perfil.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
            }
        });

        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String texto=edtTexto.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                String texto=String.valueOf(cli.getId());
                MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                try{
                    progressBar.setVisibility(View.GONE);
                    BitMatrix bitMatrix=multiFormatWriter.encode(texto,BarcodeFormat.QR_CODE,2000,2000);
                    BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                    Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                    ivQRCode.setImageBitmap(bitmap);
                }
                catch(WriterException e){
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }
    /*private void clickButton() {
            btnGerar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gerarQRCode();
                }
            });
    }

    private void gerarQRCode() {
        String texto=edtTexto.getText().toString();
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try{
            BitMatrix bitMatrix=multiFormatWriter.encode(texto,BarcodeFormat.QR_CODE,2000,2000);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            ivQRCode.setImageBitmap(bitmap);
        }
        catch(WriterException e){
            e.printStackTrace();
        }
    }
    private void inicializarComponentes() {
        edtTexto=findViewById(R.id.edtTexto);
        btnGerar=findViewById(R.id.btnGerar);
        ivQRCode=findViewById(R.id.ivQRCode);
    }*/

}