/*
 *  Copyright © 2017-2019 Cask Data, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 */
package io.cdap.wrangler.utils;

import io.cdap.wrangler.api.TransientStore;
import io.cdap.wrangler.api.TransientVariableScope;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class InMemoryTransientStore implements TransientStore {
  private final Map<String, Object> store = new HashMap<>();

  @Override
  public Set<String> getVariables() {
    return store.keySet();
  }

  @Override
  public <T> T get(String key) {
    return (T) store.get(key);
  }

  @Override
  public void set(TransientVariableScope scope, String key, Object value) {
    store.put(key, value);
  }

  @Override
  public void reset(TransientVariableScope scope) {
  }

  @Override
  public void increment(TransientVariableScope scope, String name, long value) {
  }

}

