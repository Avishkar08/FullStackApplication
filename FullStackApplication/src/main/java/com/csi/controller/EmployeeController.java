package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees/fullstack/application")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("Sign Up Done SuccessFully ... ");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));

    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> signIn() {
        return ResponseEntity.ok(employeeServiceImpl.findAll());

    }
    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Employee> findById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }
    @GetMapping("/findbyname/{empName}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpName().equals(empName)).toList());
    }
    @GetMapping("/findbydob/{empDOB}")
    public ResponseEntity<List<Employee>> findByDOB(@PathVariable String empDOB){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->simpleDateFormat.format(emp.getEmpDOB()).equals(empDOB)).toList());
    }
    @GetMapping("/findbycontactnumber/{empContactNumber}")
    public ResponseEntity<List<Employee>> findByContactNumber(@PathVariable long empName){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpContactNumber()== emp.getEmpContactNumber()).toList());
    }

    @GetMapping("/findbyuid/{empUID}")
    public ResponseEntity<List<Employee>> findByUID(@PathVariable long empUID){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpUID()== empUID).toList());
    }
    @GetMapping("/findbypancard/{empPancard}")
    public ResponseEntity<List<Employee>> findByPancard(@PathVariable String empPancard){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpPancard().equals(empPancard)).toList());
    }
    @GetMapping("/findbyemailid/{empEmailId}")
    public ResponseEntity<List<Employee>> findByEmailId(@PathVariable String empEmailId){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpEmailId().equals(empEmailId)).toList());
    }
    @GetMapping("/sortbyname")
    public  ResponseEntity<List<Employee>> sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList());
    }
   @GetMapping("/findbyanyinput/{input}")
   public  ResponseEntity<List<Employee>> findByAnyInput(@PathVariable String input){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return  ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->
                emp.getEmpName().equals(input)
        ||emp.getEmpState().equals(input)
        || emp.getEmpEmailId().equals(input)
        || String.valueOf(emp.getEmpContactNumber()).equals(input)
        || String.valueOf(emp.getEmpUID()).equals(input)
        || String.valueOf(emp.getEmpPancard()).equals(input)
        || simpleDateFormat.format(emp.getEmpDOB()).equals(input)).toList());

   }

    @GetMapping("/sortbysalary")
    public  ResponseEntity<List<Employee>> sortBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpSalary)).toList());
    }
    @GetMapping("/sortbycontactnumber")
    public  ResponseEntity<List<Employee>> sortByContactNumber(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpContactNumber)).toList());
    }
    @GetMapping("/sortbyuid")
    public  ResponseEntity<List<Employee>> sortbyuid(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpUID)).toList());
    }
    @GetMapping("/sortbypancard")
    public  ResponseEntity<List<Employee>> sortByPancard(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpPancard)).toList());
    }
    @GetMapping("/sortbydob")
    public  ResponseEntity<List<Employee>> sortByDOB(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).toList());
    }

    @GetMapping("/salarymorethan50000")
    public ResponseEntity<List<Employee>> salaryMoreThan50000(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpSalary()>=50000.00).toList());
    }
    @GetMapping("/first3employeewhohavemaximumsalary")
    public ResponseEntity<List<Employee>> first3EmployeeHaveMaxSalary(){
        return  ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary).reversed()).limit(3).toList());
    }
    @GetMapping("/checkloaneligibility")
    public ResponseEntity<List<Employee>> checkLoanEligibility(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp->emp.getEmpSalary()>=70000).toList());
    }
    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee){
        employeeServiceImpl.updateData(empId,employee);
        return ResponseEntity.ok("Data Updated SuccessFully");
    }
    @GetMapping("firstlargestsalary")
    public ResponseEntity<List<Employee>> fistLargestSalary(){
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary).reversed()).limit(1).toList());
    }
   /* @GetMapping("/secondlargestsalary")
    public ResponseEntity<List<Employee>> secondLargestSalary(){
        return ResponseEntity.ok();
    }*/


    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("ID Deleted SuccessFullyy ... ");
    }
    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeServiceImpl.deleteAll();
        return  ResponseEntity.ok("All Data Deleted Successfulyyy ...");
    }
}
