package com.egc.actividadpre_parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.ib.custom.toast.CustomToastView;

import java.io.Serializable;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tvNameCompany;
    private TextView tvNit;
    private TextView tvMessage;
    private Spinner dropDown;
    private Button btnSend;
    private List<String> workPositionList = new ArrayList<>();
    private List<Employee> Operation = new ArrayList<Employee>();
    private String result;
    private TextView tvResult;

    private TextView tbIdentification;
    private TextView tbName;
    private TextView tbSurname;
    private TextView tbAge;
    private TextView tbEmail;
    private TextView tbTWorkPosition;
    private TextView tbSalary;
    private Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workPositionList.add("Secretario");workPositionList.add("Operario");
        workPositionList.add("Supervisor");workPositionList.add("Vigilante");
        workPositionList.add("Analista de Mercadeo");workPositionList.add("Vendedor");
        List<Employee> listEmp =(List<Employee>) getIntent().getSerializableExtra("list");
        company = new Company("Infinity Corp", "221212-32",workPositionList,listEmp);
        tvNameCompany = findViewById(R.id.tvNameCompany);
        tvNit = findViewById(R.id.tvNit);
        tvNameCompany.setText(company.name);
        tvNit.setText(company.nit);
        tvMessage = findViewById(R.id.tvMessage);
        tvResult = findViewById(R.id.tvResult);
        dropDown = findViewById(R.id.spinnerOption);

        dropDownAsignation(listEmp);

        btnSend = findViewById(R.id.btnSend);
        tbIdentification = findViewById(R.id.tbIdentificationCard);
        tbName = findViewById(R.id.tbName);
        tbSurname = findViewById(R.id.tbSurname);
        tbAge = findViewById(R.id.tbAge);
        tbEmail = findViewById(R.id.tbEmail);
        tbTWorkPosition = findViewById(R.id.tbWorkposition);
        tbSalary = findViewById(R.id.tbSalary);
        tvResult.setVisibility(View.GONE);

        btnSend.setOnClickListener(this);
        if (listEmp != null){
            setList(listEmp);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSend) {
            int id = (int) dropDown.getSelectedItemId();

            switch (id) {
                case 0:
                    CustomToastView.makeErrorToast(this,"Seleccione alguna acción",R.layout.custom_toast).show();
                    break;
                case 1://Registrar empleado
                    Intent intent = new Intent(this, RegisterEmployee.class);
                    intent.putExtra("listWorkPosition", (Serializable) workPositionList);
                    startActivity(intent);
                    break;
                case 2://Ver Registros
                    tvMessage.setText("Empleados registrados");
                     Operation = company.listEmployees;
                    tvResult.setVisibility(View.GONE);
                    setList(Operation);
                    break;
                case 3://Mas joven
                    result="";
                    Operation = null;
                    tvMessage.setText("El/los empleados mas jovenes son:");
                    Operation = company.youngestEmployes();
                    setList(Operation);
                    tvResult.setVisibility(View.GONE);
                    //tvResult.setText(result);
                    break;
                case 4://Mas Viejo
                    result="";
                    Operation = null;
                    Operation = company.OldestEmployes();
                    setList(Operation);
                    tvMessage.setText("El/los empleados mas Viejos son:");
                    tvResult.setVisibility(View.GONE);
                    //tvResult.setText(result);
                    break;
                case 5:
                    result="";
                    Operation = null;
                    //tvMessage.setText("");
                    Operation = company.salaryMax();
                    setList(Operation);
                    tvResult.setVisibility(View.GONE);
                    tvMessage.setText("El/los empleados con salarios mas altos:");
                    break;
                case 6:
                    result="";
                    Operation = null;
                    //tvMessage.setText("");
                    Operation = company.salaryMin();
                    setList(Operation);
                    tvResult.setVisibility(View.GONE);
                    tvMessage.setText("El/los empleados con salarios mas bajos:");
                    break;
                case 7:
                    tvResult.setVisibility(View.VISIBLE);
                    tvMessage.setText("Salario promedio de toda la compañia:");
                    String result2="";
                    result2= String.valueOf(company.SalaryProm());
                    tvResult.setText(result2);
                    break;
                case 8:
                    tvResult.setVisibility(View.VISIBLE);
                    String result=" ";
                    //tvMessage.setText("");
                    result = company.Count();
                    Operation = company.listEmployees;
                    setList(Operation);
                    tvMessage.setText("Empleados por Cargo:");
                    tvResult.setText(result);
                    break;
            }
        }
    }

    public void OperationSelected(List<Employee> ListResult){
        result="";
        Operation = null;
        tvMessage.setText("");
        setList(ListResult);

    }


    public void dropDownAsignation(List<Employee> List){
        ArrayAdapter<CharSequence> adapter;
        if (company.listEmployees == null){
             adapter = ArrayAdapter.createFromResource(this,R.array.opcion_unique,R.layout.support_simple_spinner_dropdown_item);
        }else {
             adapter = ArrayAdapter.createFromResource(this, R.array.options_array, R.layout.support_simple_spinner_dropdown_item);
        }
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);
    }

    public void setList(List<Employee> employeeList) {

        String identificationCard = "";
        String name = "";
        String surname = "";
        String age = "";
        String email = "";
        String workposition = "";
        String salary = "";
        for (int i = 0; i <= employeeList.size() - 1; i++) {

            identificationCard = identificationCard + "\n" + employeeList.get(i).identificationCard;
            name = name + "\n" + employeeList.get(i).name;
            surname = surname + "\n" + employeeList.get(i).surname;
            age = age + "\n" + employeeList.get(i).age;
            email = email + "\n" + employeeList.get(i).email;
            workposition = workposition + "\n" + employeeList.get(i).workPosition;
            salary = salary + "\n" + employeeList.get(i).salary;

        }
        tbIdentification.setText(identificationCard);
        tbName.setText(name);
        tbSurname.setText(surname);
        tbAge.setText(age);
        tbEmail.setText(email);
        tbTWorkPosition.setText(workposition);
        tbSalary.setText(salary);
    }


}

