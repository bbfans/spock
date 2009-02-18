/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spockframework.runtime.intercept;

import java.lang.annotation.Annotation;

import org.spockframework.dsl.*;
import org.spockframework.runtime.model.MethodInfo;
import org.spockframework.runtime.model.SpeckInfo;

/**
 * A ...
 *
 * @author Peter Niederwieser
 */
public class FailsWithProcessor extends AbstractDirectiveProcessor {
  public void visitSpeckDirective(Annotation directive, SpeckInfo speck) {
    for (MethodInfo feature : speck.getFeatureMethods())
      if (!feature.getReflection().isAnnotationPresent(FailsWith.class))
        feature.addInterceptor(new FailsWithInterceptor((FailsWith)directive));
  }

  public void visitMethodDirective(Annotation directive, MethodInfo method) {
    method.addInterceptor(new FailsWithInterceptor((FailsWith)directive));
  }
}
