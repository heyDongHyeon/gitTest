package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class EventDmtiaUserService extends EgovAbstractServiceImpl {

	@Resource(name = "eventDmtiaUserMapper")
    private EventDmtiaUserMapper mapper;

	/**
	 * 치매어르신 리스트목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EventDmtiaUserVo> getLocationList(HashMap<String, String> map) throws Exception {

		ArrayList<EventDmtiaUserVo> list = (ArrayList<EventDmtiaUserVo>) mapper.getLocationList(map);

		return list;
	}

	/**
	 * 치매어르신 리스트목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EventDmtiaUserVo> getList(HashMap<String, String> map) throws Exception {

		ArrayList<EventDmtiaUserVo> list = (ArrayList<EventDmtiaUserVo>) mapper.getList(map);

		return list;
	}

	/**
	 * 치매어르신 리스트단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public EventDmtiaUserVo getItem(HashMap<String, String> map) throws Exception {

		EventDmtiaUserVo vo = mapper.getItem(map);

		return vo;
	}

	/**
	 * 치매어르신 삭제합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean del(HashMap<String, String> map) throws Exception {

		int state = mapper.del(map);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 치매어르신 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(EventDmtiaUserVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 치매어르신 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(EventDmtiaUserVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 이벤트 리스트수를 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCount(HashMap<String, String> map) throws Exception {

		int count = mapper.getCount(map);

		return count;

	}

}
