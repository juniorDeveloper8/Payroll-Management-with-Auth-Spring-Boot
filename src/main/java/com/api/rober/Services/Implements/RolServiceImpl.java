package com.api.rober.Services.Implements;

import com.api.rober.Entity.Rol;
import com.api.rober.Persistence.IRolDAO;
import com.api.rober.Services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolDAO rolDAO;

    @Override
    public List<Rol> findAll() {
        return rolDAO.findAll();
    }

    @Override
    public Optional<Rol> findById(Integer id_rol) {
        return rolDAO.findById(id_rol);
    }

    @Override
    public void save(Rol rol) {

        /*
        para guardar toca tomar el ingreso que es el sueldo que paga el empleador
        - menos el prestamo o egreso si es q hay y menos el aporte al iess
        es decir primero multiplicamos ingreso*9.35%. luego el resultado lo restamos del ingreso
        y ese nuebo resultado le restamos los egresos y eso sera el sueldo del usuario
        */

        // Obtener el sueldo base
        BigDecimal subSalary = rol.getSubSalary();

        // Sumar los ingresos adicionales al sueldo base
        BigDecimal totalIncome = subSalary.add(rol.getIncome());

        // Restar los gastos del total
        BigDecimal totalExpenses = rol.getExpenses() != null ? rol.getExpenses() : BigDecimal.ZERO;
        BigDecimal salary = totalIncome.subtract(totalExpenses);

        // Calcular el aporte al IESS (9.35% del totalIncome)
        BigDecimal iessContribution = totalIncome.multiply(new BigDecimal("0.0935"));

        // Restar el aporte al IESS del salario final
        salary = salary.subtract(iessContribution);

        // Asignar el salario calculado al objeto rol
        rol.setSalary(salary);

        // Guardar el rol
        rolDAO.save(rol);
    }

    @Override
    public void deleteById(Integer id_rol) {
        rolDAO.deleteById(id_rol);
    }
}
