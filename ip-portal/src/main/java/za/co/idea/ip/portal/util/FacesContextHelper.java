package za.co.idea.ip.portal.util;

import java.util.ArrayList;
import java.util.List;

public class FacesContextHelper {
	public static List<Long> getIdsFromArray(Long[] ae) {
		List<Long> ret = new ArrayList<Long>();
		if (ae != null)
			for (Long id : ae)
				ret.add(id);
		return ret;
	}

}
