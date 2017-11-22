/*
 * Copyright 2017 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.base.settings;

import com.intellij.ide.ui.search.SearchableOptionsRegistrar;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.components.JBLabel;
import java.util.Set;
import javax.swing.JLabel;

/** A helper class to make settings text searchable. */
public final class SearchableOptionsHelper {

  private final SearchableOptionsRegistrar registrar;
  private final String displayName;
  private final String configurableId;

  public SearchableOptionsHelper(SearchableConfigurable configurable) {
    this.registrar = SearchableOptionsRegistrar.getInstance();
    this.displayName = configurable.getDisplayName();
    this.configurableId = configurable.getId();
  }

  /** Makes the given option text searchable. */
  public void registerLabelText(String text, boolean applyStemming) {
    Set<String> words =
        applyStemming
            ? registrar.getProcessedWords(text)
            : registrar.getProcessedWordsWithoutStemming(text);
    words.forEach(
        word -> registrar.addOption(word, displayName, text, configurableId, displayName));
  }

  /** Create a {@link JLabel} from the given text, making that text searchable. */
  public JLabel createSearchableLabel(String text, boolean applyStemming) {
    registerLabelText(text, applyStemming);
    return new JBLabel(text);
  }
}
