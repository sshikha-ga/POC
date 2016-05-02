package com.ga.domain.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class datasource {

    @Autowired
    JdbcTemplate dataSource;

    public JdbcTemplate getDataSource() {
        return dataSource;
    }

    public void setDataSource(JdbcTemplate dataSource) {
        this.dataSource = dataSource;
    }

}
