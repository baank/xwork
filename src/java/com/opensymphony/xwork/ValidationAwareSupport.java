/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.xwork;

import java.io.Serializable;
import java.util.*;

/**
 * Provides a default implementation of ValidationAware. Returns new collections for
 * errors and messages (defensive copy).
 *
 * @author Jason Carreira
 * @author tm_jee
 * @version $Date: 2007-11-30 14:04:10 +0900 (金, 30 11 2007) $ $Id: ValidationAwareSupport.java 1694 2007-11-30 05:04:10Z tm_jee $
 */
public class ValidationAwareSupport implements ValidationAware, Serializable {

    private Collection actionErrors;
    private Collection actionMessages;
    private Map fieldErrors;


    public synchronized void setActionErrors(Collection errorMessages) {
        this.actionErrors = errorMessages;
    }

    public synchronized Collection getActionErrors() {
        return new ArrayList(internalGetActionErrors());
    }

    public synchronized void setActionMessages(Collection messages) {
        this.actionMessages = messages;
    }

    public synchronized Collection getActionMessages() {
        return new ArrayList(internalGetActionMessages());
    }

    public synchronized void setFieldErrors(Map errorMap) {
        this.fieldErrors = errorMap;
    }

    public synchronized Map getFieldErrors() {
        return new LinkedHashMap(internalGetFieldErrors());
    }

    public synchronized void addActionError(String anErrorMessage) {
        internalGetActionErrors().add(anErrorMessage);
    }

    public synchronized void addActionMessage(String aMessage) {
        internalGetActionMessages().add(aMessage);
    }

    public synchronized void addFieldError(String fieldName, String errorMessage) {
        final Map errors = internalGetFieldErrors();
        List thisFieldErrors = (List) errors.get(fieldName);

        if (thisFieldErrors == null) {
            thisFieldErrors = new ArrayList();
            errors.put(fieldName, thisFieldErrors);
        }

        thisFieldErrors.add(errorMessage);
    }

    public synchronized boolean hasActionErrors() {
        return (actionErrors != null) && !actionErrors.isEmpty();
    }

    public synchronized boolean hasActionMessages() {
        return (actionMessages != null) && !actionMessages.isEmpty();
    }

    public synchronized boolean hasErrors() {
        return (hasActionErrors() || hasFieldErrors());
    }

    public synchronized boolean hasFieldErrors() {
        return (fieldErrors != null) && !fieldErrors.isEmpty();
    }

    private Collection internalGetActionErrors() {
        if (actionErrors == null) {
            actionErrors = new ArrayList();
        }

        return actionErrors;
    }

    private Collection internalGetActionMessages() {
        if (actionMessages == null) {
            actionMessages = new ArrayList();
        }

        return actionMessages;
    }

    private Map internalGetFieldErrors() {
        if (fieldErrors == null) {
            fieldErrors = new LinkedHashMap();
        }

        return fieldErrors;
    }

    /**
     * Clears all error and messages list/maps.
     * <p/>
     * Will clear the maps/lists that contain
     * field errors, action errors and action messages.
     */
    public synchronized void clearErrorsAndMessages() {
        internalGetFieldErrors().clear();
        internalGetActionErrors().clear();
        internalGetActionMessages().clear();
    }
}
