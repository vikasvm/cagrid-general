/*
 * Portions of this file Copyright 1999-2005 University of Chicago
 * Portions of this file Copyright 1999-2005 The University of Southern California.
 *
 * This file or a portion of this file is licensed under the
 * terms of the Globus Toolkit Public License, found at
 * http://www.globus.org/toolkit/download/license.html.
 * If you redistribute this file, with or without
 * modifications, you must include this notice in the file.
 */
package org.globus.wsrf.encoding;

import org.apache.axis.encoding.ser.BaseDeserializerFactory;

import javax.xml.rpc.encoding.Deserializer;
import javax.xml.rpc.JAXRPCException;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;

/**
 * DeserializerFactory for QueryExpressionType
 */
public class QueryExpressionDeserializerFactory 
    extends BaseDeserializerFactory {
    
    public QueryExpressionDeserializerFactory() {
        super(QueryExpressionDeserializer.class);
    }

    public Deserializer getDeserializerAs(String mechanismType)
        throws JAXRPCException {
        return new QueryExpressionDeserializer();
    }

    public static DeserializerFactory create(Class javaType, 
                                             QName xmlType) {
        return new QueryExpressionDeserializerFactory();
    }
    
}