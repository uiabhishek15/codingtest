package com.codingtest.web.common;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.codingtest.data.entity.User;

public class LazySorter implements Comparator<User> {
	
	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(User car1, User car2) {
		try {
			Object value1 = User.class.getField(this.sortField).get(car1);
			Object value2 = User.class.getField(this.sortField).get(car2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}