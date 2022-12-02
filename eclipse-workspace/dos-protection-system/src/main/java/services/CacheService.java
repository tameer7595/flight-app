package services;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.controllers.WindowController;


public class CacheService {
	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
	
	
	private HashMap<Integer, List<Long>> staticWindow;
	
	private HashMap<Integer, List<Long>> dynamicWindow;
	
	
	
	public CacheService(HashMap<Integer, List<Long>> staticWindow, HashMap<Integer, List<Long>> dynamicWindow) {
		super();
		this.staticWindow = staticWindow;
		this.dynamicWindow = dynamicWindow;
	}

	public List<Long> getStaticWindows(Integer clientId) {
		if(this.staticWindow.isEmpty() || this.staticWindow.get(clientId) == null) {
			return new ArrayList<Long>();
		}
		List<Long> staticsWindow = this.staticWindow.get(clientId);
		return staticsWindow;
	}

	public void checkStaticTimeFrame(Integer clientId) {
		long currentTime =  System.currentTimeMillis();
		if(this.staticWindow.containsKey(clientId) && currentTime - this.staticWindow.get(clientId).get(0) > 5000) {
			logger.info("restarting statics window for client id {} after 5 seconds since the the first window", clientId);
			List<Long> staticsWindows = new ArrayList<>();
			this.staticWindow.replace(clientId, staticsWindows);
		}
	}

	public void setStaticWindow(Integer clientId) {
		List<Long> staticsWindows = new ArrayList<>();
		if(this.staticWindow.containsKey(clientId)) {
			this.staticWindow.get(clientId).add(System.currentTimeMillis());
		}else {
			staticsWindows.add(System.currentTimeMillis());
			this.staticWindow.put(clientId, staticsWindows);
			}

	}

	public List<Long> getDynamicWindows(Integer clientId) {
		if(this.dynamicWindow.isEmpty() || this.dynamicWindow.get(clientId) == null) {
			return new ArrayList<Long>();
		}
		List<Long> dynamicWindow = this.dynamicWindow.get(clientId).stream().filter(time -> (System.currentTimeMillis()-time) <= 5000).collect(Collectors.toList());
		return dynamicWindow;
	}

	public void setDynamicWindow(Integer clientId) {
		if(this.dynamicWindow.containsKey(clientId)) {
			this.dynamicWindow.get(clientId).add(System.currentTimeMillis());
		}else {
			List<Long> dynamicWindows = new ArrayList<>();
			dynamicWindows.add(System.currentTimeMillis());
			this.dynamicWindow.put(clientId, dynamicWindows );
		}
	}



}
