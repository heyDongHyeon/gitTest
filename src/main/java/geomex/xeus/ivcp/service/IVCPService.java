package geomex.xeus.ivcp.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  AccessService.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2016-02-01      홍길동          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 6. 22.
 * @version :  1.0
 * @see
 */
@Service("ivcpService")
public class IVCPService extends EgovAbstractServiceImpl {

	@Resource(name = "ivcpMapper")
	private IVCPMapper mapper;

	public ArrayList<IVCPVo> getList(HashMap<String, String> map) throws Exception {

		ArrayList<IVCPVo> list = (ArrayList<IVCPVo>) mapper.getList(map);

		return list;
	}

	public IVCPVo getItem(HashMap<String, String> map) throws Exception {
		IVCPVo vo = null;
		try {
			vo = mapper.getItem(map);

		} catch ( Exception e ) {

		}

		return vo;
	}

	public int getCount(HashMap<String, String> map) throws Exception {

		int count = mapper.getCount(map);

		return count;

	}

	public boolean add(HashMap<String, String> map) throws Exception {

		int state = mapper.add(map);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}



}
