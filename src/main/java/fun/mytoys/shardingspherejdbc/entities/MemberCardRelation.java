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
@Table(name = "mmp_member_card_relation")
public class MemberCardRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cardNo;
    private Long memberId;
    private Date createAt;
}
