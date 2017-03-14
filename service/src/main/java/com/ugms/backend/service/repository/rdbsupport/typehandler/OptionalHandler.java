package com.ugms.backend.service.repository.rdbsupport.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Roy on 2017/3/13.
 */
@MappedTypes({Optional.class})
public class OptionalHandler extends BaseTypeHandler<Optional> {

    @Override
    public Optional getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Optional.ofNullable(callableStatement.getObject(i));
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Optional optional, JdbcType jdbcType)
            throws SQLException {

        if (optional.isPresent()) {
            preparedStatement.setObject(i, optional.get());
        } else {
            preparedStatement.setObject(i, null);
        }

    }

    @Override
    public Optional getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Optional.ofNullable(resultSet.getObject(s));
    }

    @Override
    public Optional getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Optional.ofNullable(resultSet.getObject(i));
    }
}
