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
package org.apache.axiom.testutils.io;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.input.ProxyReader;

/**
 * {@link Reader} wrapper that implements {@link InstrumentedStream}.
 */
public final class InstrumentedReader extends ProxyReader implements InstrumentedStream {
    private long count;
    private boolean closed;
    
    public InstrumentedReader(Reader parent) {
        super(parent);
    }

    @Override
    protected void afterRead(int n) {
        if (n != -1) {
            count += n;
        }
    }
    
    public long getCount() {
        return count;
    }

    public void close() throws IOException {
        closed = true;
        super.close();
    }

    public boolean isClosed() {
        return closed;
    }
}