package pratice.mariaAndQuerydsl.dto;

import lombok.Data;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import pratice.mariaAndQuerydsl.entity.SexType;

@Data
public class EmployeeCondition {
    private String name;
    private String department_name;
    private SexType sex;
    private Integer salaryGoe;
    private Integer salaryLoe;

    // 파라미터로 전체 count 받기
    private Long paramCount;

    public void setParamCount(Long paramCount) {
        this.paramCount = isNumeric(String.valueOf(paramCount)) ? paramCount :   0L;
    }

    public Long getParamCount() {
        return isNumeric(String.valueOf(paramCount)) ? paramCount : 0L;
    }

    public boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
