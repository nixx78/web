package lv.nixx.poc.crud.service.internal;

import java.util.Collection;

public abstract class DataLoader<K> {
	public abstract Collection<K> load();
}