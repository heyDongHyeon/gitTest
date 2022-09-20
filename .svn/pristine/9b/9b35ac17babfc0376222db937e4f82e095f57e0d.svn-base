package geomex.xeus.lyrmgr.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import geomex.xeus.equipmgr.service.CctvModelVo;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.smartcity.Utils;
import geomex.xeus.smartcity.service.EventHistService;
import geomex.xeus.smartcity.service.EventHistVo;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.StrUtil;
import geomex.xeus.util.code.SystemParameter;

@Service("lyrMgrService")
public class LyrMgrService {

	@Resource(name = "lyrMgrMapper")
	private LyrMgrMapper mapper;

	public HashMap<String, String> getLyrStyle(HashMap<String, String> map) throws Exception {

		return mapper.getLyrStyle(map);
	}

	public boolean editLyrStyle(HashMap<String, String> map) throws Exception {

		int state = mapper.editLyrStyle(map);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}
}
