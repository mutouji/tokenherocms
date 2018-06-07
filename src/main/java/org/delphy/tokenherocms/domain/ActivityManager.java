package org.delphy.tokenherocms.domain;

import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mutouji
 */
@Service
public class ActivityManager {
    public RestResult<List<Activity>> getActivities(Long status, Long page, Long size) {
        int state = 0;
        if (status != null) {
            state = status.intValue();
        }
        switch (state) {
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            default:
                break;
        }
        return null;
    }
}
