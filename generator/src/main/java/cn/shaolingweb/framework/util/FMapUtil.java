package cn.shaolingweb.framework.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public abstract class FMapUtil<K,V> {

	public <K, V> Set<K> mergeKey(Map<K, V>... map ) {
		Set<K> set=new HashSet<K>();
		for (Map<K, V> item : map) {
			 Iterator<K> iter=item.keySet().iterator();
			 if (iter.hasNext()) {
				set.add(iter.next());
			}
		}
		return set;
	}
}
