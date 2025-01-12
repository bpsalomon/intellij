/*
 * Copyright 2023 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.qsync.query;

public class QuerySummaryTestUtil {

  private QuerySummaryTestUtil() {}

  public static Query.Summary createProtoForPackages(String... packages) {
    Query.Summary.Builder builder = Query.Summary.newBuilder();
    for (String p : packages) {
      builder.putRules(p, Query.Rule.newBuilder().setRuleClass("java_library").build());
    }
    return builder.build();
  }
}
