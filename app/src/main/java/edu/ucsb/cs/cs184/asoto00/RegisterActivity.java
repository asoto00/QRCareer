package edu.ucsb.cs.cs184.asoto00;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button StudentRegister;
    private EditText StudentName;
    private EditText StudentPhone;
    private EditText StudentMajor;
    private EditText GraduationYear;
    private EditText StudentGPA;
    private EditText Email;
    private EditText Password;
    private TextView EmployerReg;
    private TextView SignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StudentRegister = (Button) findViewById(R.id.reg);
        StudentName = (EditText) findViewById(R.id.Name);
        StudentPhone = (EditText) findViewById(R.id.Phone);
        StudentMajor = (EditText) findViewById(R.id.Major);
        GraduationYear = (EditText) findViewById(R.id.GradYear);
        StudentGPA = (EditText) findViewById(R.id.GPA);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        EmployerReg = (TextView) findViewById(R.id.Employer);
        SignIn = (TextView) findViewById(R.id.SignIn);

        StudentRegister.setOnClickListener(this);
        EmployerReg.setOnClickListener(this);
        SignIn.setOnClickListener(this);

    }

    private void registerStudent(){

    }


    @Override
    public void onClick(View v) {
        if(v == StudentRegister){
            registerStudent();
        }

        if(v == EmployerReg){

        }

        if(v == SignIn){

        }
    }
}
