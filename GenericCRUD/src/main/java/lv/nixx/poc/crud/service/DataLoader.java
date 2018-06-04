package lv.nixx.poc.crud.service;

import java.util.Collection;

public abstract class DataLoader<K> {
	public abstract Collection<K> load();
}