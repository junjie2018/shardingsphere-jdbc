package fun.mytoys.shardingspherejdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mmp_card_consume_record")
public class CardConsumeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

	private Long cardConsumeRecordId;
    private Long cardNo;
    private Integer consumeNumber;
    private BigDecimal consumeValue;
    private Integer oldConsumeNumber;
    private BigDecimal oldConsumeValue;
    private String operationType;
    private String operationTypeName;
    private String operationObjectId;
    private Date createAt;
}
