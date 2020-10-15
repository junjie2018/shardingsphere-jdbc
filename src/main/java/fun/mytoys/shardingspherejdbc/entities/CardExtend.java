package fun.mytoys.shardingspherejdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mmp_card_extend")
public class CardExtend implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long cardNo;
    private Long combineCardNo;
    private String cardInitialPassword;
    private String cardPassword;
    private Date validStartDate;
    private Date validEndDate;
    private String employeeNo;
    private Integer employeeInitialPointValue;
    private Date clearDate;
    private Date createAt;
    private String createBy;
    private Date updateAt;
    private String updateBy;
}
