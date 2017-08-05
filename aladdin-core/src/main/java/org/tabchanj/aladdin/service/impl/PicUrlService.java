package org.tabchanj.pangu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tabchanj.pangu.domain.PicUrl;
import org.tabchanj.pangu.domain.User;
import org.tabchanj.pangu.mapper.PicUrlMapper;
import org.tabchanj.pangu.service.IPicUrlService;

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
