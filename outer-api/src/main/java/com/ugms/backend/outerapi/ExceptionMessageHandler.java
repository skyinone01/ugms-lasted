package com.ugms.backend.outerapi;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ugms.backend.bean.exception.UserException;
import com.ugms.backend.bean.base.response.BasicResponse;
import com.ugms.backend.bean.exception.UgmsStatus;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * Created by Roy on 2017/3/8.
 */
@ControllerAdvice
public class ExceptionMessageHandler {

	 private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BasicResponse handleUserException(UserException e) {
        return new BasicResponse(e.getErrorNo(), e.getError());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public BasicResponse handleAccessException(AccessDeniedException e) {
        return new BasicResponse(UgmsStatus.AUTH_FAILED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new BasicResponse(UgmsStatus.FORMAT_ERROR);
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleHttpMessageNotReadableException(MultipartException e) {
        return new BasicResponse(UgmsStatus.FORMAT_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();

        e.getBindingResult().getAllErrors().forEach(v -> {
            if (v instanceof FieldError) {
                sb.append(((FieldError) v).getField());
                sb.append(":");
                sb.append(v.getDefaultMessage());
                sb.append(";");
            } else {
                sb.append(v.getDefaultMessage());
            }
        });

        return new BasicResponse(UgmsStatus.BAD_REQUEST, sb.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleConstraintViolationException(ConstraintViolationException e)
            throws NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException {

        StringBuilder sb = new StringBuilder();

        for (ConstraintViolation c : e.getConstraintViolations()) {
            Object propertyPath = c.getPropertyPath();

            //去@RequestParam.value的值
            if (propertyPath instanceof PathImpl) {
                PathImpl path = (PathImpl) propertyPath;
                NodeImpl currentLeafNode = path.getLeafNode();
                Integer parameterIndex = currentLeafNode.getParameterIndex();
                String methodName = currentLeafNode.getParent().getName();
                List<Class<?>> methodParams = currentLeafNode.getParent().getParameterTypes();
                Object rootBean = c.getRootBean();
                int size = methodParams.size();
                Method targetMethod = rootBean.getClass().getDeclaredMethod(methodName, methodParams.toArray(new Class[size]));
                Parameter targetParameter = targetMethod.getParameters()[parameterIndex];

                String value;
                RequestParam param = targetParameter.getAnnotation(RequestParam.class);
                if (param != null) {
                    value = param.value();
                } else {
                    value = targetParameter.getAnnotation(PathVariable.class).value();
                }

                sb.append(value);
                sb.append(":");
                sb.append(c.getMessage());
                sb.append(";");
            }
        }
        return new BasicResponse(UgmsStatus.BAD_REQUEST, sb.toString());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleMissingPathVariableException(MissingPathVariableException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleServletRequestBindingException(ServletRequestBindingException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleUnsatisfiedServletRequestParameterException(UnsatisfiedServletRequestParameterException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleInvalidFormatException(InvalidFormatException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleConversionFailedException(ConversionFailedException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public BasicResponse handleBadSqlGrammarException(BadSqlGrammarException e) {
    	logger.error(e.getMessage());
        return new BasicResponse(UgmsStatus.INTERNAL_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, "非法参数: " + e.getName());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BasicResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new BasicResponse(UgmsStatus.BAD_REQUEST, e.getMessage());
    }
}
