package org.delphy.tokenherocms.benchmark.help;

import lombok.Data;

@Data
public class ThreadRecord {
    long st;
    long et;
    public ThreadRecord(long st, long et) {
        this.st = st;
        this.et = et;
    }
}
