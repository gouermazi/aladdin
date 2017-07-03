package org.tabchanj.pangu.mapper;

import java.util.List;

import org.tabchanj.pangu.domain.PicUrl;
import org.tabchanj.pangu.domain.User;

public interface PicUrlMapper {
	PicUrl get(int id);

	int save(PicUrl picUrl);

	List<PicUrl> getAll();
}
