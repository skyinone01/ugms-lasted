package com.ug369.backend.service.repository.rdbsupport.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by Roy on 2017/3/19.
 */
@MappedTypes({LocalDate.class})
public class LocalDateHandler extends BaseTypeHandler<LocalDate> {
    public LocalDateHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType) throws
            SQLException {
        if (parameter == null) {
            ps.setDate(i, (Date) null);
        } else {
            ps.setDate(i, Date.valueOf(parameter));
        }

    }

    public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Date date = rs.getDate(columnName);
        return date != null ? date.toLocalDate() : null;
    }

    public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Date date = rs.getDate(columnIndex);
        return date != null ? date.toLocalDate() : null;
    }

    public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Date date = cs.getDate(columnIndex);
        return date != null ? date.toLocalDate() : null;
    }
}