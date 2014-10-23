package za.co.idea.ip.orm.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import za.co.idea.ip.orm.dao.IpAllocationDAO;

@SuppressWarnings({ "rawtypes", "resource" })
public class IpOrmTest {
	@Test
	public void verifyAllocation() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		IpAllocationDAO dao = (IpAllocationDAO) ctx.getBean("IpAllocationDAO");
		List out = dao.getAllocationByEntity("ip_idea_status");
		System.out.println(out);
	}
}
