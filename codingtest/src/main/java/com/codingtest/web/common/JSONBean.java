package com.codingtest.web.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.codingtest.data.entity.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@ManagedBean(name = "json")
@ViewScoped
public class JSONBean implements Serializable {

	private static final long serialVersionUID = 25924094432794817L;

	private List<User> users;

	private List<User> filterusers;
	
	private String key;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getFilterusers() {
		return filterusers;
	}

	public void setFilterusers(List<User> filterusers) {
		this.filterusers = filterusers;
	}

	public User onRowSelect(SelectEvent event) {
		User obj = (User) event.getObject();
		return obj;
	}

	public void onCellEdit(CellEditEvent event) throws ClientProtocolException,
			IOException {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		int rowIndex = event.getRowIndex();
		User car = null;
		System.out.println("Car Edited" + rowIndex);
		UIColumn col = event.getColumn();
		System.out.println("row key" + col.getHeaderText());
		// LOGGER.info("Car Edited"+rowIndex);
		DataTable carTable = (DataTable) event.getSource();
		String index = carTable.getClientId(FacesContext.getCurrentInstance());
		System.out.println("index----------->" + index);
	
		User entity = (User) ((DataTable) event.getComponent()).getRowData();
		System.out.println("data============" + entity.getName());
		String updateurl = "http://localhost:8080//codingtestrestapi/api/v1/location/updatedata/"
				+ entity.getName() + "/" + newValue + "/" + col.getHeaderText();
		
		URL url = new URL(updateurl);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
		    httpCon.getOutputStream());
		out.write("Resource content");
		out.close();
		httpCon.getInputStream();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	@PostConstruct
	public void init() {

		String url = "http://localhost:8080//codingtestrestapi/api/v1/location";

		String login_url = "http://localhost:8080//codingtestrestapi/login";

		String filterurl = "http://localhost:8080//codingtestrestapi/api/v1/location/"
				+ key + "/department";
		try {

			Response res = Jsoup.connect(login_url).ignoreContentType(true)
					.data("username", "user").data("password", "userPass")
					.method(Method.POST).execute();

			Document doc = res.parse();

			// Keep logged in
			Map<String, String> cookies = res.cookies();

			Document data = Jsoup.connect(url).ignoreContentType(true)
					.cookies(cookies).get();

			String json = data.select("body").text();
			users = new Gson().fromJson(json, new TypeToken<List<User>>() {
			}.getType());

			Document filterdata = Jsoup.connect(filterurl)
					.ignoreContentType(true).get();
			String filterjson = filterdata.select("body").text();
			filterusers = new Gson().fromJson(filterjson,
					new TypeToken<List<User>>() {
					}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void removeItem(User item)  throws ClientProtocolException,
	IOException {
	      //itemList.remove(item);
		String deleteurl = "http://localhost:8080//codingtestrestapi/api/v1/location/delete/"+item.getName();
		System.out.println("row"+item.getName());
		/*URL url = new URL(deleteurl);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("");
		httpCon.connect();*/
		
		URL url = new URL(deleteurl);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("DELETE");
		OutputStreamWriter out = new OutputStreamWriter(
		    httpCon.getOutputStream());
		out.write("Resource content");
		out.close();
		httpCon.getInputStream();
	   }

}
