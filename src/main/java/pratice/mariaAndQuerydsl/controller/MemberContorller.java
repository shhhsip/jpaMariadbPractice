package pratice.mariaAndQuerydsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pratice.mariaAndQuerydsl.dto.EmployeeCondition;
import pratice.mariaAndQuerydsl.dto.EmployeeDepartmentPayDto;
import pratice.mariaAndQuerydsl.entity.PageWrapper;
import pratice.mariaAndQuerydsl.repository.EmployeeRepository;

@Controller
@RequiredArgsConstructor
public class MemberContorller {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String memberList(Model model, EmployeeCondition condition, Pageable pageable) {
        Page<EmployeeDepartmentPayDto> employeeList = employeeRepository.searchPage(condition, pageable);
        PageWrapper<EmployeeDepartmentPayDto> page = new PageWrapper<>(employeeList);

        model.addAttribute("page", page);
        model.addAttribute("pageStartNum", page.getPageStartNum());
        model.addAttribute("boardList", employeeList);

        return "index";
    }

}
