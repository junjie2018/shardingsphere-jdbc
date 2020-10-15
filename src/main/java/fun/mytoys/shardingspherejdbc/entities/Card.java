package fun.mytoys.shardingspherejdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mmp_card")
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long cardNo;
    private Long mobile;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Integer cardType;
    private String isEmployee;
    private Integer isMajor;
    private String joinDate;
    private Integer cardStatus;
    private Integer memberStatus;
    private Integer memberSubStatus;
    private Date updateAt;
}
