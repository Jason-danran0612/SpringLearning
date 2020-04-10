package com.lengdanran.restfulcrud.controller;

import com.lengdanran.restfulcrud.dao.DepartmentDao;
import com.lengdanran.restfulcrud.dao.EmployeeDao;
import com.lengdanran.restfulcrud.entities.Department;
import com.lengdanran.restfulcrud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.Collection;

/**
 * @Classname EmployeeController
 * @Description TODO
 * @Date 2020/4/9 18:24
 * @Created by ASUS
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

//    查询所有员工
    @GetMapping(value = "/emps")
//    @PutMapping(value = "/emps")
    public String List(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        return "/emp/list";
    }
//    转到添加页面
    @GetMapping(value = "/emp")
    public String toAddPage(Model model){
//        添加之前需要进行部门的查询
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departmentnames",departments);
        return "/emp/add";
    }
//    接受POST请求进行员工添加操作
    @PostMapping(value = "/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
//    接受GET请求到达修改页面
    @GetMapping(value = "/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id ,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departmentnames",departments);
        return "/emp/Edit";
    }
//    接受POST请求进行删除员工信息
    @DeleteMapping(value = "/emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        System.out.println("delete a employee ...");
        return "redirect:/emps";
    }


//    接受PUT的请求，更新客户信息
    @PutMapping(value = "/emp")
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
