package com.codingtest.web.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.codingtest.data.entity.User;

public class LazyUserDataModel extends LazyDataModel<User> {
	
	private List<User> datasource;
    
    public LazyUserDataModel(List<User> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public User getRowData(String rowKey) {
        for(User car : datasource) {
            if(car.getLocation().equals(rowKey))
                return car;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(User car) {
        return car.getLocation();
    }
 
    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<User> data = new ArrayList<User>();
 
        //filter
        for(User car : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(car);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
