package org.yyz.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"/*,"classpath:config/app-mvc.xml"*/})
public class MyTest {
	/*@Autowired
	@Qualifier("searchServiceImpl")
	SearchService searchService;
	@Test
	public void myTest(){
		
	}
	@Test
	public void searchTest(){
		SearchService searchService = new SearchServiceImpl();
		SearchResult<JobItem> js = searchService.search("程序员",1);
		for(JobItem j:js.getItems()){
			System.out.println(j.getCompId()+"-------"+j.getJobName());
		}
	}*/
}
