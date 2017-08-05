package org.tabchanj.aladdin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tabchanj.aladdin.domain.PicUrl;
import org.tabchanj.aladdin.mapper.PicUrlMapper;
import org.tabchanj.aladdin.service.IPicUrlService;

@Service
public class PicUrlService implements IPicUrlService {
	@Autowired
	private PicUrlMapper picUrlMapper;

	public List<PicUrl> getAll() {

		return picUrlMapper.getAll();
	}

	public PicUrl get(int id) {
		PicUrl picUrl = (PicUrl) picUrlMapper.get(id);
		return picUrl;
	}

	@Override
	public int save(PicUrl picUrl) {
		return picUrlMapper.save(picUrl);
	}

}
