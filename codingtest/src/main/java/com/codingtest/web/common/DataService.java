package com.codingtest.web.common;

import org.fluttercode.datafactory.impl.DataFactory;

import com.codingtest.data.entity.SKU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataService {
	
	//INSTANCE;
    private List<SKU> skuList = new ArrayList<>();
    List<String> departments = Arrays.asList("HR", "Admin", "IT", "Sales");

    DataService() {
        DataFactory dataFactory = new DataFactory();

        for (int i = 1; i < 50; i++) {
        	SKU sku = new SKU();
            //employee.setId(i);
        	//sku.set
            /*employee.setName(dataFactory.getName());
            employee.setPhoneNumber(String.format("%s-%s-%s", dataFactory.getNumberText(3),
                    dataFactory.getNumberText(3),
                    dataFactory.getNumberText(4)));
            employee.setAddress(dataFactory.getAddress() + "," + dataFactory.getCity());
            employee.setDepartment(dataFactory.getItem(departments));
            skuList.add(employee);*/
        }
    }

    public List<String> getDepartments() {
        return departments;
    }

    public List<SKU> getEmployeeList() {
        return skuList;
    }
}
