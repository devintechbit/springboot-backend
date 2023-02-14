package net.devptl.springboot.repository;

import net.devptl.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
// It is not necessary to annotate because it already predefined by spring boot
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
