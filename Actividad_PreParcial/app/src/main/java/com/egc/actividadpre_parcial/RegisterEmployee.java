package com.egc.actividadpre_parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ib.custom.toast.CustomToastView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterEmployee extends AppCompatActivity implements View.OnClickListener {

    private EditText txtIdentificationCard;
    private EditText txtName;
    private EditText txtSurname;
    private EditText txtAge;
    private Spinner spinnerWorkPosition;
    private EditText txtEmail;
    private EditText txtSalary;
    private Button btnRegister;
    private Button btnReturn;
    static List<Employee> employeesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);
        txtIdentificationCard = findViewById(R.id.txtIdentificationCard);
        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        txtAge = findViewById(R.id.txtAge);
        spinnerWorkPosition = findViewById(R.id.SpinnerWorkposition);
        List<String> listWorkPosition = (List<String>) getIntent().getSerializableExtra("listWorkPosition");
        //employeesList = (List<Employee>) getIntent().getSerializableExtra("ListEmployees");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listWorkPosition);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWorkPosition.setAdapter(adapter);
        txtEmail = findViewById(R.id.txtEmail);
        txtSalary = findViewById(R.id.txtSalary);
        btnRegister = findViewById(R.id.btnRegister);
        btnReturn = findViewById(R.id.btnReturn);
        btnRegister.setOnClickListener(this);
        btnReturn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister){

            if (txtIdentificationCard.getText().toString().isEmpty()  ||  txtName.getText().toString().isEmpty() || txtAge.getText().toString().isEmpty()
                    || txtEmail.getText().toString().isEmpty() || txtSalary.getText().toString().isEmpty()){
                CustomToastView.makeErrorToast(this, "Llene todos los campos", R.layout.custom_toast).show();
            }else {
                addEmpleyee();
                CustomToastView.makeSuccessToast(this, "Empleado Guardado", R.layout.custom_toast).show();
            }
        }else if(v.getId()==R.id.btnReturn){
            Intent intentList = new Intent(this, MainActivity.class);
            intentList.putExtra("list", (Serializable) employeesList);
            startActivity(intentList);

        }
    }

    public void addEmpleyee(){
        Employee newEmp = new Employee(txtIdentificationCard.getText().toString(),txtName.getText().toString(),txtSurname.getText().toString(),
                Integer.parseInt(txtAge.getText().toString()),spinnerWorkPosition.getSelectedItem().toString(),txtEmail.getText().toString(),Double.parseDouble(txtSalary.getText().toString()));
            employeesList.add(newEmp);
            clearFields();
    }

    public void clearFields(){
        txtIdentificationCard.setText("");
        txtName.setText("");
        txtSurname.setText("");
        txtAge.setText("");
        txtEmail.setText("");
        txtSalary.setText("");
    }

}