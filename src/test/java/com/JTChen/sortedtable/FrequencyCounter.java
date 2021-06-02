package com.JTChen.sortedtable;

import java.io.*;

import com.JTChen.typeofdata.table.SimpleTable;

public class FrequencyCounter {
	private final SimpleTable<String, Integer> st;

	public FrequencyCounter(SimpleTable<String, Integer> st) {
		this.st = st;
	}

	public String calcutate(int minLen, String filePath) {
		try {
			InputStream fis = new FileInputStream(filePath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String word;
			while ((word = reader.readLine()) != null) {
				String[] words = word.split("\\s+");
				for (String w : words) {
					if (w.length() < minLen) continue;
					if (!st.contains(w)) st.put(w, 1);
					else st.put(w, st.get(w) + 1);
				}
			}
			String res = "";
			int max = 0;
			for (String s : st.keys()) {
			    if (st.get(s) > max) {
			        max = st.get(s);
			        res = s;
                }
            }
			return res;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
