package geomex.xeus.log.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  IfEvtLogMapper.java
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

@Mapper("ifEvtLogMapper")
public interface IfEvtLogMapper {

    public int add(IfEvtLogVo vo);

    public List<IfEvtLogVo> getList(HashMap<String, String> map);

    public IfEvtLogVo getItem(HashMap<String, String> map);

    public int getCount(HashMap<String, String> map);

    public int del(HashMap<String, String> map);

    public int edit(IfEvtLogVo vo);
}
