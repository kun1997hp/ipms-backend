package com.viettel.demo.model.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class TableSyslogForm {
    @NotBlank(message = "{tableSyslog.notBlank}")
    private String tableSyslog;
    private String whiteList;
    private String blackList;
    @NotNull(message = "{faultLevelId.notNull}")
    private Integer faultLevelId;
    private Date updateTime;
    private Date insertTime;
    private Integer status;
}
