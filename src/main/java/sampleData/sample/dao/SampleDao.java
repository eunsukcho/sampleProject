package sampleData.sample.dao;

import java.util.Map;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSession sqlSession;
	
	static final String NAMESPACE = "sampleMapper";
	
	public int sampleDateInsert(Map<String, Object> map){
		
		return sqlSession.insert(NAMESPACE + ".insertSampleData", map);
	}
}
