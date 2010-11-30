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

import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axis.Constants;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.message.MessageElement;
import org.apache.axis.wsdl.fromJava.Types;
import org.globus.util.I18n;
import org.globus.wsrf.utils.Resources;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;

/**
 * Serializer for Any MessageElements.
 */
public class AnySerializer implements Serializer {

    private static I18n i18n = I18n.getI18n(Resources.class.getName());

    public void serialize(QName name,
                          Attributes attributes,
                          Object value,
                          SerializationContext context)
        throws IOException {
        if (!(value instanceof MessageElement)) {
            Object[] args = 
                new Object[] {MessageElement.class,
                              (value == null) ? null : value.getClass()};
            throw new IOException(i18n.getMessage("expectedType", args));
        }

        context.startElement(name, attributes);

        if (value != null) {
            try {
                ((MessageElement) value).output(context);
            } catch (Exception e) {
                throw new IOException(
                             i18n.getMessage("genericSerializationError") + 
                             " " + e.getMessage());
            }
        }

        context.endElement();
    }

    public String getMechanismType() {
        return Constants.AXIS_SAX;
    }

    public Element writeSchema(Class javaclass,
                               Types types) 
        throws Exception {
        return null;
    }
}
