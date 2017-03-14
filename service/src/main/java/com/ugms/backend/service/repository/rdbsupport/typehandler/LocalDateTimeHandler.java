package com.ugms.backend.service.repository.rdbsupport.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/**
 * Created by Roy on 2017/3/19.
 */
@MappedTypes({LocalDateTime.class})
public class LocalDateTimeHandler extends BaseTypeHandler<LocalDateTime> {
    public LocalDateTimeHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws
            SQLException {
        if (parameter == null) {
            ps.setTimestamp(i, (Timestamp) null);
        } else {
            ps.setTimestamp(i, Timestamp.valueOf(parameter), GregorianCalendar.from(ZonedDateTime.of(parameter, ZoneId.systemDefault())));
        }

    }

    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        return ts != null ? LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault()) : null;
    }

    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        return ts != null ? LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault()) : null;
    }

    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        return ts != null ? LocalDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault()) : null;
    }
}
