package sampleData.sample.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sampleData.sample.dao.SampleDao;

@Service
public class SampleService {
	
	@Autowired
	private SampleDao dao;
	
	public int sampleDateInsert(Map<String, Object> map){
		
		return dao.sampleDateInsert(map);
	}
}
