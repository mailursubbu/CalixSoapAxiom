/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axiom.om.impl.dom.jaxp;

import org.apache.axiom.om.OMDocument;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.dom.DOMMetaFactory;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.util.StAXUtils;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @deprecated See {@link DocumentBuilderFactoryImpl} for the reason why this class is deprecated.
 */
public class DocumentBuilderImpl extends DocumentBuilder {

    /** The DocumentBuilderFactory used to create this document builder */
    private DocumentBuilderFactoryImpl factory;

    private final DOMMetaFactory domMetaFactory;
    
    protected DocumentBuilderImpl(DocumentBuilderFactoryImpl fac, DOMMetaFactory domMetaFactory) {
        super();
        this.factory = fac;
        this.domMetaFactory = domMetaFactory;
    }

    /**
     * Returns whether the parser is configured to understand namespaces or not. The StAX parser
     * used by this DOM impl is namespace aware therefore this will always return true.
     *
     * @see javax.xml.parsers.DocumentBuilder#isNamespaceAware()
     */
    public boolean isNamespaceAware() {
        return true;
    }

    /**
     * The StAX builder used is the org.apache.axiom.om.impl.llom.StAXOMBuilder is a validating
     * builder.
     *
     * @see javax.xml.parsers.DocumentBuilder#isValidating()
     */
    public boolean isValidating() {
        return true;
    }

    public DOMImplementation getDOMImplementation() {
        return domMetaFactory.getDOMImplementation();
    }

    /**
     * Returns a new document impl.
     *
     * @see javax.xml.parsers.DocumentBuilder#newDocument()
     */
    public Document newDocument() {
        return (Document)domMetaFactory.getOMFactory().createOMDocument();
    }

    public void setEntityResolver(EntityResolver er) {
        // TODO
    }

    public void setErrorHandler(ErrorHandler eh) {
        // TODO 
    }

    public Document parse(InputSource inputSource) throws SAXException,
            IOException {
        try {
            OMFactory factory = domMetaFactory.getOMFactory();
            // Not really sure whether this will work :-?
            XMLStreamReader reader = StAXUtils
                    .createXMLStreamReader(inputSource.getCharacterStream());
            StAXOMBuilder builder = new StAXOMBuilder(factory, reader);
            OMDocument doc = builder.getDocument();
            doc.build();
            return (Document)doc;
        } catch (XMLStreamException e) {
            throw new SAXException(e);
        }
    }

    /** @see javax.xml.parsers.DocumentBuilder#parse(java.io.InputStream) */
    public Document parse(InputStream is) throws SAXException, IOException {
        try {
            OMFactory factory = domMetaFactory.getOMFactory();
            XMLStreamReader reader = StAXUtils
                    .createXMLStreamReader(is);
            StAXOMBuilder builder = new StAXOMBuilder(factory, reader);
            return (Document) builder.getDocument();
        } catch (XMLStreamException e) {
            throw new SAXException(e);
        }
    }

    /** @see javax.xml.parsers.DocumentBuilder#parse(java.io.File) */
    public Document parse(File file) throws SAXException, IOException {
        try {
            OMFactory factory = domMetaFactory.getOMFactory();
            XMLStreamReader reader = StAXUtils
                    .createXMLStreamReader(new FileInputStream(file));
            StAXOMBuilder builder = new StAXOMBuilder(factory, reader);
            return (Document) builder.getDocument();
        } catch (XMLStreamException e) {
            throw new SAXException(e);
        }
    }

    /** @see javax.xml.parsers.DocumentBuilder#parse(java.io.InputStream, String) */
    public Document parse(InputStream is, String systemId) throws SAXException,
            IOException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /** @see javax.xml.parsers.DocumentBuilder#parse(String) */
    public Document parse(String uri) throws SAXException, IOException {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    /* (non-Javadoc)
     * @see javax.xml.parsers.DocumentBuilder#getSchema()
     */
    public Schema getSchema() {
        //HACK : To get opensaml working 
        return this.factory.schema;
    }


}
