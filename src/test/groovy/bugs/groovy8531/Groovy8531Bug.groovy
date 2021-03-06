/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package groovy.bugs.groovy8531

class Groovy8531Bug extends GroovyTestCase {
    void testPublicAndProtectedInnerType() {
        assertScript '''
            package groovy.bugs.groovy8531
            class Example extends Reducer {
                public void reduce(PublicContext context) {}
                public void reduce2(ProtectedContext context) {}
                public boolean isDynamic(Type type) {
                    return Type.DYNAMIC == type
                }
            }
            
            new Example().reduce(null)
            new Example().reduce2(null)
            assert new Example().isDynamic(Reducer.Type.DYNAMIC)
        '''
    }

    void testPrivateInnerType() {
        def errMsg = shouldFail '''
            package groovy.bugs.groovy8531
            class Example extends Reducer {
                public void reduce3(PrivateContext context) {}
            }
        '''
        assert errMsg.contains('unable to resolve class')
    }
}
