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
package org.apache.axiom.ts.dom.document;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axiom.ts.dom.DOMTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TestDocumentSiblings extends DOMTestCase {
    public TestDocumentSiblings(DocumentBuilderFactory dbf) {
        super(dbf);
    }

    protected void runTest() throws Throwable {
        Document doc = dbf.newDocumentBuilder().newDocument();
        Element elem = doc.createElement("test");
        doc.appendChild(elem);

        Node node = doc.getNextSibling();
        assertNull("Document's next sibling has to be null", node);
        Node node2 = doc.getPreviousSibling();
        assertNull("Document's previous sibling has to be null", node2);
        Node node3 = doc.getParentNode();
        assertNull("Document's parent has to be null", node3);
    }
}
