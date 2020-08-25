package Settlers;

import java.util.HashMap;
import java.util.Set;

/*
 * A neat structure for mapping keys to values like a dictionary, see how it's used in the kruskal
 * algo
 */
public class Decorator<K, V> {
	
	HashMap<K,V> _hash;
	
	public Decorator() {
		_hash = new HashMap<K,V>();
  }

  public V getDecoration(K key) {
      return _hash.get(key);
  }

  /*
   * Setter of the key and value
   */
  public void setDecoration(K key, V value) {
	  _hash.put(key,value);

  }

  /*
   * Returns true if the key is decorated
   */
  public boolean hasDecoration(K key) {
	  return _hash.containsKey(key);
  }

  /*
   * Removes the decoration and returns the value
   */
  public V removeDecoration(K key) {
    return _hash.remove(key);
  }

  // Getter for the key-set
  public Set<K> getKeys() {
    return _hash.keySet();
  }
}
