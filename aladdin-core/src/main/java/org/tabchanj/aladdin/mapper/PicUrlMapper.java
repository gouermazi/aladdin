package org.tabchanj.aladdin.mapper;

import java.util.List;

import org.tabchanj.aladdin.domain.PicUrl;

public interface PicUrlMapper {
	PicUrl get(int id);

	int save(PicUrl picUrl);

	List<PicUrl> getAll();
}
