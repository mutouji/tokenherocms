package org.delphy.tokenherocms.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author mutouji
 */
@Data
public class OracleResultVo {
    private Long scenarioIndex;
    private List<String> resultValues;
    private Boolean manualChecked;
    private Long disputeNum;
    private Boolean disputePeriodEnded;
}
