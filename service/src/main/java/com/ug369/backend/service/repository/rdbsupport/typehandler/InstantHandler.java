package com.ug369.backend.service.repository.rdbsupport.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.Instant;

/**
 * Created by Roy on 2017/3/19.
 */
@MappedTypes({Instant.class})
public class InstantHandler extends BaseTypeHandler<Instant> {
    public InstantHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, Instant parameter, JdbcType jdbcType) throws
            SQLException {
        if (parameter == null) {
            ps.setTimestamp(i, (Timestamp) null);
        } else {
            ps.setTimestamp(i, Timestamp.from(parameter));
        }

    }

    public Instant getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        return ts != null ? ts.toInstant() : null;
    }

    public Instant getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        return ts != null ? ts.toInstant() : null;
    }

    public Instant getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        return ts != null ? ts.toInstant() : null;
    }
}
