package org.tabchanj.pangu.service;

import java.util.List;

import org.tabchanj.pangu.domain.PicUrl;

public interface IPicUrlService {
	List<PicUrl> getAll();

	int save(PicUrl picUrl);

	PicUrl get(int id);
}
