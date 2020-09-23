package com.allen.test;

/**
 * @author xuguocai 2020/8/4 16:05
 */

import com.allen.mycat.MycatApp;
import com.allen.mycat.mapper.TravelrecordMapper;
import com.allen.mycat.po.Travelrecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MycatApp.class)
public class MycatTest {

	@Autowired
	private TravelrecordMapper travelrecordMapper;


	@Test
	public void test(){
		Travelrecord travelrecord = new Travelrecord();
		travelrecord.setName("小名");

		travelrecordMapper.insert(travelrecord);

	}

}
