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
package org.codehaus.groovy.runtime.memoize;

/**
 * Represents a memoize cache with its essential methods
 * @param <K> type of the keys
 * @param <V> type of the values
 *
 * @author Vaclav Pech
 */
public interface MemoizeCache<K, V> {

    /**
     * Associates the specified value with the specified key in the cache.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return null, or the old value if the key associated with the specified key.
     */
    V put(K key, V value);

    /**
     * Gets a value from the cache
     * @param key the key whose associated value is to be returned
     * @return the value, or null, if it does not exist.
     */
    V get(K key);

    /**
     * Invoked when some of the held SoftReferences have been evicted by the garbage collector and so should be removed from the cache.
     * The implementation must ensure that concurrent invocations of all methods on the cache may occur from other threads
     * and thus should protect any shared resources.
     */
    void cleanUpNullReferences();
}