package com.ugms.backend.service.repository.rdbsupport.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Roy on 2017/3/19.
 */
@MappedTypes({ZonedDateTime.class})
public class ZonedDateTimeHandler extends BaseTypeHandler<ZonedDateTime> {
    public ZonedDateTimeHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, ZonedDateTime parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter == null) {
            ps.setTimestamp(i, (Timestamp) null);
        } else {
            ps.setTimestamp(i, Timestamp.from(parameter.toInstant()), GregorianCalendar.from(parameter));
        }

    }

    public ZonedDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName, Calendar.getInstance());
        return ts != null ? ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault()) : null;
    }

    public ZonedDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex, Calendar.getInstance());
        return ts != null ? ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault()) : null;
    }

    public ZonedDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex, Calendar.getInstance());
        return ts != null ? ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.systemDefault()) : null;
    }
}