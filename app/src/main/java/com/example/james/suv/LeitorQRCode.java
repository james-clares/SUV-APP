package com.example.james.suv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.suv.AcessoBD.Cliente;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LeitorQRCode extends AppCompatActivity {

    private Button scan_btn;
    TextView idQRCodeNomeCli,idQRCodeCPF,idQRCodeRG;
    Cliente cli=new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_qrcode);

        scan_btn = (Button) findViewById(R.id.scan_btn);

        idQRCodeNomeCli=findViewById(R.id.idQRCodeNomeCli);
        idQRCodeCPF=findViewById(R.id.idQRCodeCPF);
        idQRCodeRG=findViewById(R.id.idQRCodeRG);

        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            IntentIntegrator integrator = new IntentIntegrator(activity);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            //integrator.setPrompt("Buscando código QR Code");
            integrator.setPrompt("A leitura do código é feita na horizontal.");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(false);
            integrator.initiateScan();
        }
    });

}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Você cancelou a leitura do QR Code", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                String a=result.getContents();
                int b=Integer.parseInt(a);
                //cli.setId(b);
                //cli.CarregarDados();
                //Intent m = new Intent(LeitorQRCode.this, Dados_usuario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(m);
                //idQRCodeNomeCli.setText("Bem-vindo "+result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
