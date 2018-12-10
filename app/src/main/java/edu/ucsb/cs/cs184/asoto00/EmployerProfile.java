package edu.ucsb.cs.cs184.asoto00;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import javax.xml.transform.Result;

public class EmployerProfile extends AppCompatActivity implements View.OnClickListener {

    private TextView CEName;
    private TextView CECompany;
    private TextView CEPhone;
    private Button SignOutButton2;
    private Button ScanButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private EmployerUser employerUser;

    final Activity activity = this;

    String items[] = new String[]{"Art", "Yes","Rob", "Ale","pro", "sus"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        if(user == null){
            startActivity( new Intent(this, Sign_In.class));
            finish();
        }else{
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    employerUser = dataSnapshot.getValue(EmployerUser.class);
                    updateEmployerInfo();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        CEName = (TextView) findViewById(R.id.CEName);
        CECompany = (TextView) findViewById(R.id.CECompany);
        CEPhone = (TextView) findViewById(R.id.CEPhone);

        SignOutButton2 = (Button) findViewById(R.id.SignOutButton2);
ScanButton= (Button)findViewById(R.id.ScanButton);

        SignOutButton2.setOnClickListener(this);
        ScanButton.setOnClickListener(this) ;

    }

    public void updateEmployerInfo(){
        CEName.setText(employerUser.Name);
        CECompany.setText(employerUser.Company);
        CEPhone.setText(employerUser.PhoneNumber);

    }

//    @Override
//    public void handleResult(Result rawResult){
//        Log.e("handler", rawResult.getText());
//        Log.e("handler", rawResult.getBarCodeFormat().String());
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan Result");
//        builder.setMessage(rawResult.getText());
//        AlertDialog alert1 = builder.create();
//        alert1.show();
//    }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
   Intent intent = new Intent(this, InfoList.class);
    if (result != null) {
        if (result.getContents() == null) {
            Toast.makeText(this, "You Cancelled the scanning", Toast.LENGTH_LONG).show();

        } else {


            startActivity( new Intent(getApplicationContext(), InfoList.class ));
            Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();


            finish();
        }
    }

    else {
        super.onActivityResult(requestCode, resultCode, data);
    }
    }



    @Override
    public void onClick(View v) {
        if (v == SignOutButton2) {
            firebaseAuth.signOut();

            startActivity(new Intent(this, Sign_In.class));
            finish();
        }

        if (v == ScanButton) {
            IntentIntegrator integrator = new IntentIntegrator(activity);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("Scan");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(false);
            integrator.initiateScan();
        }
    }
}
