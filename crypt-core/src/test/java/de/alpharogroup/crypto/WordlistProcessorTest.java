/**
 * Copyright (C) 2015 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.crypto;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.testng.annotations.Test;

import de.alpharogroup.crypto.processors.wordlist.WordlistsProcessor;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.PathFinder;

public class WordlistProcessorTest
{

	@Test
	public void test() throws IOException
	{
		final File wordlistDir = new File(PathFinder.getSrcTestResourcesDir(), "wordlists");
		final File wordlist_1 = new File(wordlistDir, "default-pw.txt");
		final File wordlist_2 = new File(wordlistDir, "firstnames.txt");
		final File wordlist_3 = new File(wordlistDir, "surnames.txt");		
		final File wordlist_4 = new File(wordlistDir, "top25pw.txt");
		final List<String> lines1 = ReadFileExtensions.readLinesInList(wordlist_1);
		final List<String> lines2 = ReadFileExtensions.readLinesInList(wordlist_2);
		final List<String> lines3 = ReadFileExtensions.readLinesInList(wordlist_3);
		final List<String> lines4 = ReadFileExtensions.readLinesInList(wordlist_4);
		final Set<String> set = new TreeSet<>();
		set.addAll(lines1);
		set.addAll(lines2);
		set.addAll(lines3);		
		set.addAll(lines4);

		final String password = "hash";

		final WordlistsProcessor processor = new WordlistsProcessor(new ArrayList<String>(set),
			password);
		final long start = System.currentTimeMillis();
		final boolean found = processor.process();
		final long end = System.currentTimeMillis();


		System.out.println("Started wordlist attack for the password: " + new Date(start));
		System.out.println("Ended of the wordlist attack for the password: " + new Date(end));
		System.out.println("Password found: " + found);

	}
}
