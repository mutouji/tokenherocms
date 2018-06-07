package org.delphy.tokenherocms.pojo;

import lombok.Data;
import org.delphy.tokenherocms.common.Constant;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author mutouji
 */
@Data
public class AdminCache {
    private String account;
    private String token;
}
