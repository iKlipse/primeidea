package za.co.idea.ip.orm.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class ScalarNumber extends Number {

	private static final long serialVersionUID = 7414716197256047569L;
	@Id
	@Column(name = "cnt")
	private Long count;

	@Override
	public double doubleValue() {
		return count.doubleValue();
	}

	@Override
	public float floatValue() {
		return count.floatValue();
	}

	@Override
	public int intValue() {
		return count.intValue();
	}

	@Override
	public long longValue() {
		return count.longValue();
	}
}
