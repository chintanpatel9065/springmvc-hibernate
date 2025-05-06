package org.chintanpatel.springmvchibernate.controller;

import jakarta.validation.Valid;
import org.chintanpatel.springmvchibernate.model.Employee;
import org.chintanpatel.springmvchibernate.service.DepartmentService;
import org.chintanpatel.springmvchibernate.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/employees/listEmployee")
    public String listEmployee(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departmentList", departmentService.getAllDepartmentList());
        model.addAttribute("employeeList", employeeService.getAllEmployeeList());
        return "employee-form";
    }

    @PostMapping("/employees/insertOrUpdateEmployee")
    public String insertOrUpdateEmployee(@Valid @ModelAttribute("employee")Employee employee, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("departmentList", departmentService.getAllDepartmentList());
            model.addAttribute("employeeList", employeeService.getAllEmployeeList());
            return "employee-form";
        }
        if (employee.getEmployeeId() != null) {
            employee.setEmployeeId(employee.getEmployeeId());
            employeeService.editEmployee(employee);
        } else {
            employeeService.addEmployee(employee);
        }
        return "redirect:/employees/listEmployee";
    }

    @GetMapping("/employees/manageEmployee")
    public String manageEmployee(@RequestParam("employeeId") Long employeeId, Model model){
        if(employeeId != null){
            model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
            model.addAttribute("departmentList", departmentService.getAllDepartmentList());
            model.addAttribute("employeeList", employeeService.getAllEmployeeList());
        }
        return "employee-form";
    }

    @GetMapping("/employees/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") Long employeeId){
        if(employeeId != null){
            employeeService.deleteEmployeeById(employeeId);
        }
        return "redirect:/employees/listEmployee";
    }
}
