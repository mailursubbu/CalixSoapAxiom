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
package org.apache.axiom.buildutils.enforcer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

final class ClassProcessor extends ClassVisitor {
    private final ReferenceCollector referenceCollector;
    private ReferenceProcessor referenceProcessor;
    
    ClassProcessor(ReferenceCollector referenceCollector) {
        super(Opcodes.ASM5);
        this.referenceCollector = referenceCollector;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
            String[] interfaces) {
        referenceProcessor = new ReferenceProcessor(referenceCollector, Type.getObjectType(name).getClassName());
        referenceProcessor.processType(Type.getObjectType(superName));
        for (String iface : interfaces) {
            referenceProcessor.processType(Type.getObjectType(iface));
        }
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature,
            Object value) {
        referenceProcessor.processType(Type.getType(desc));
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
            String[] exceptions) {
        referenceProcessor.processType(Type.getMethodType(desc));
        if (exceptions != null) {
            for (String exception : exceptions) {
                referenceProcessor.processType(Type.getObjectType(exception));
            }
        }
        return new MethodProcessor(referenceProcessor);
    }
}
