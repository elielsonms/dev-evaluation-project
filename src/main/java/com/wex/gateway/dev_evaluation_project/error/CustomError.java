package com.wex.gateway.dev_evaluation_project.error;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties({ "stackTrace","cause","errorMessage","locations","path","extensions","localizedMessage","suppressed"})
public class CustomError extends RuntimeException  implements GraphQLError {
    final String message;
    final ErrorClassification errorType;

    public CustomError(Exception ex){
        this.message = ex.getMessage();
        this.errorType = ErrorType.DataFetchingException;
    }

    public CustomError(GraphQLError error){
        this.message = error.getMessage();
        this.errorType = error.getErrorType();
    }

    public CustomError(String message){
        this.message = message;
        this.errorType = ErrorType.DataFetchingException;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public ErrorClassification getErrorType() {
        return errorType;
    }


}
