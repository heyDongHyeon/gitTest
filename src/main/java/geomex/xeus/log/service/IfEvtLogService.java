package geomex.xeus.log.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  IfEvtLogService.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 5. 3.
 * 버전   :  1.0
 * 설명   :
 * 클래스 설명을 쓰시오
 *
 * 수정이력
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 *
 * ===========================================================
 * </pre>
 */

@Service("ifEvtLogService")
public class IfEvtLogService extends EgovAbstractServiceImpl {

    @Resource(name = "ifEvtLogMapper")
    private IfEvtLogMapper mapper;

    public boolean add(IfEvtLogVo vo) throws Exception {

        int state = mapper.add(vo);

        if (state == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 이벤트 로그 목록을 조회합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ArrayList<IfEvtLogVo> getList(HashMap<String, String> map) throws Exception {

        return (ArrayList<IfEvtLogVo>) mapper.getList(map);
    }

    /**
     * 이벤트 로그 단건을 조회합니다. (여러가지 조건을 사용합니다.)
     *
     * @param map
     * @return
     * @throws Exception
     */
    public IfEvtLogVo getItem(HashMap<String, String> map) throws Exception {

        return mapper.getItem(map);
    }

    /**
     * 이벤트 로그 수를 조회합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public int getCount(HashMap<String, String> map) throws Exception {

        int count = mapper.getCount(map);

        return count;

    }

    /**
     * 이벤트 로그를 삭제합니다.
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
     * 이벤트 로그를 수정합니다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean edit(IfEvtLogVo vo) throws Exception {

        int state = mapper.edit(vo);

        if(state == 1){
            return true;
        }else{
            return false;
        }

    }
}
