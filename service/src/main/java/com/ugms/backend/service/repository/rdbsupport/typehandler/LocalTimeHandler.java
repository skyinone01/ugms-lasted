package com.ugms.backend.service.repository.rdbsupport.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalTime;

/**
 * Created by Roy on 2017/3/19.
 */
@MappedTypes({LocalTime.class})
public class LocalTimeHandler extends BaseTypeHandler<LocalTime> {
    public LocalTimeHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, LocalTime parameter, JdbcType jdbcType) throws
            SQLException {
        if (parameter == null) {
            ps.setTime(i, (Time) null);
        } else {
            ps.setTime(i, Time.valueOf(parameter));
        }

    }

    public LocalTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Time time = rs.getTime(columnName);
        return time != null ? time.toLocalTime() : null;
    }

    public LocalTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Time time = rs.getTime(columnIndex);
        return time != null ? time.toLocalTime() : null;
    }

    public LocalTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Time time = cs.getTime(columnIndex);
        return time != null ? time.toLocalTime() : null;
    }
}
