package pratice.mariaAndQuerydsl.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pratice.mariaAndQuerydsl.dto.EmployeeCondition;
import pratice.mariaAndQuerydsl.dto.EmployeeDepartmentPayDto;
import pratice.mariaAndQuerydsl.repository.EmployeeRepository;

@RestController
@RequiredArgsConstructor
public class MemberApiContorller {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/api/members")
    public Page<EmployeeDepartmentPayDto> memberList(EmployeeCondition condition, Pageable pageable) {
        return employeeRepository.searchPage(condition, pageable);
    }
}
