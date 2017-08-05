package org.tabchanj.aladdin.service;

import java.util.List;

import org.tabchanj.aladdin.domain.PicUrl;

public interface IPicUrlService {
	List<PicUrl> getAll();

	int save(PicUrl picUrl);

	PicUrl get(int id);
}
